package repository;

import modules.*;

import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseRepository implements KursRepository, BrukerRepository, DeltakerRepository{
    private static final String url="jdbc:mysql://localhost:3306/softwareengineering";
    private static final String user="root";
    private static final String password="1234";
    Connection connection;

    public DatabaseRepository() {
        try {
            // Force driver registration explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        }

        catch(Exception e) {
            System.err.println("An error has occured: " + e);
        }

    }

    @Override
    public ArrayList<Kurs> getAllKurs() {
        ArrayList<Kurs> alleKurs = new ArrayList<>();
        String query = "SELECT p.id AS personid, k.kursnavn AS kursnavn, k.starttid AS starttid, "
                + "k.sluttTid AS sluttTid, k.beskrivelse AS beskrivelse, k.kategori AS kategori, "
                + "s.adresse AS adresse, "
                + "ka.telefonnr AS telefonnr, p.navn AS kursansvarig_navn, p.epost AS epost "
                + "FROM kurs AS k "
                + "INNER JOIN sted AS s ON k.sted_id = s.id "
                + "INNER JOIN kursansvarlig AS ka ON k.kursansvarlig_id = ka.id "
                + "INNER JOIN person AS p ON ka.person_id = p.id";

        // Create statement locally using connection
        try (Statement localStatement = connection.createStatement();
             ResultSet resultSet = localStatement.executeQuery(query)) {

            while (resultSet.next()) {
                String kursnavn = resultSet.getString("kursnavn");
                LocalDateTime startTid = resultSet.getObject("starttid", LocalDateTime.class);
                LocalDateTime sluttTid = resultSet.getObject("sluttTid", LocalDateTime.class);
                String beskrivelse = resultSet.getString("beskrivelse");
                String databaseResultatKategori = resultSet.getString("kategori");
                Kategori kategori1 = Kategori.valueOf(databaseResultatKategori.toUpperCase());
                String adresse =  resultSet.getString("adresse");

                String telefonnr = resultSet.getString("telefonnr");
                String kursansvarligNavn = resultSet.getString("kursansvarig_navn");
                String kursansvarligEpost = resultSet.getString("epost");
                int person_id = resultSet.getInt("personid");

                String fornavn = "";
                String etternavn = "";
                if (kursansvarligNavn != null && !kursansvarligNavn.trim().isEmpty()) {
                    String[] nameParts = kursansvarligNavn.trim().split("\\s+", 2);
                    fornavn = nameParts[0];
                    etternavn = nameParts.length > 1 ? nameParts[1] : "";
                }

                Kursansvarlig kursansvarlig = new Kursansvarlig(person_id, fornavn, etternavn, kursansvarligEpost, telefonnr);
                Kurs currentKurs = new Kurs(1, kursnavn, startTid, sluttTid, beskrivelse, kategori1, "Halden",adresse);
                alleKurs.add(currentKurs);
            }
        } catch (Exception e) {
            System.err.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }

        return alleKurs;
    }

    @Override
    public ArrayList<Kurs> searchKurs(String navn, Kategori kategori, String sted) {
        ArrayList<Kurs> alleKurs = new ArrayList<>();
        String kategoriString = kategori.toString();
        String query = "SELECT p.id AS personid, k.kursnavn AS kursnavn, k.starttid AS starttid, k.sluttTid AS sluttTid, " +
                "k.beskrivelse AS beskrivelse, k.kategori AS kategori, ka.telefonnr AS telefonnr, p.navn AS kursansvarig_navn, " +
                "p.epost AS epost FROM kurs AS k INNER JOIN kursansvarlig AS ka ON k.kursansvarlig_id = ka.id INNER JOIN person AS p " +
                "ON ka.person_id = p.id WHERE kursnavn = ? AND kategori = ?";

        // Use PreparedStatement to prevent SQL injection and avoid null statement errors
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, navn);
            pstmt.setString(2, kategoriString);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while(resultSet.next()) {
                    String kursnavn = resultSet.getString("kursnavn");
                    LocalDateTime startTid = resultSet.getObject("starttid", LocalDateTime.class);
                    LocalDateTime sluttTid = resultSet.getObject("sluttTid", LocalDateTime.class);
                    String beskrivelse = resultSet.getString("beskrivelse");
                    String databaseResultatKategori = resultSet.getString("kategori");
                    String adresse =  resultSet.getString("adresse");
                    Kategori kategori1 = Kategori.valueOf(databaseResultatKategori.toUpperCase());

                    String telefonnr = resultSet.getString("telefonnr");
                    String kursasnvarligNavn = resultSet.getString("kursansvarig_navn");
                    String kursansvarligEpost = resultSet.getString("epost");

                    int person_id = resultSet.getInt("personid");
                    String[] name = kursasnvarligNavn.split(" ");
                    Kursansvarlig kursansvarlig = new Kursansvarlig(person_id, name[0], name[1], kursansvarligEpost, telefonnr);
                    Kurs currentKurs = new Kurs(1, kursnavn, startTid, sluttTid, beskrivelse, kategori1, "Halden", adresse);
                    alleKurs.add(currentKurs);
                }
            }
        } catch (Exception e) {
            System.err.println("Something went wrong in searchKurs: " + e.getMessage());
            e.printStackTrace();
        }
        return alleKurs;
    }

    @Override
    public ArrayList<Kurs> filtrerKurs(String sted, Kategori kategori) {
        ArrayList<Kurs> alleKurs = new ArrayList<>();

        String query = "SELECT k.id AS kursId, k.kursnavn AS kursnavn, " +
                "k.starttid AS starttid, k.sluttTid AS sluttTid, " +
                "k.beskrivelse AS beskrivelse, k.kategori AS kategori, " +
                "s.navn AS sted, s.adresse AS adresse " +
                "FROM kurs AS k " +
                "INNER JOIN sted AS s ON k.sted_id = s.id " +
                "WHERE s.navn = ? AND k.kategori = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, sted);
            pstmt.setString(2, kategori.toString());

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int kursId = resultSet.getInt("kursId");
                    String kursnavn = resultSet.getString("kursnavn");
                    LocalDateTime startTid = resultSet.getObject("starttid", LocalDateTime.class);
                    LocalDateTime sluttTid = resultSet.getObject("sluttTid", LocalDateTime.class);
                    String beskrivelse = resultSet.getString("beskrivelse");

                    String databaseKategori = resultSet.getString("kategori");
                    Kategori kursKategori = Kategori.valueOf(databaseKategori.toUpperCase());

                    String kursSted = resultSet.getString("sted");
                    String adresse = resultSet.getString("adresse");

                    Kurs kurs = new Kurs(kursId, kursnavn, startTid, sluttTid, beskrivelse, kursKategori, kursSted, adresse);

                    alleKurs.add(kurs);
                }
            }
        } catch (Exception e) {
            System.err.println("Noe gikk galt med filtreringen: " + e.getMessage());
            e.printStackTrace();
        }

        return alleKurs;
    }

    @Override
    public void addKurs(Kurs kurs) {
        String kategori = kurs.getKategori().toString();
        String query = "INSERT INTO kurs (kursnavn, starttid, sluttTid, beskrivelse, kategori) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, kurs.getNavn());
            preparedStatement.setObject(2, kurs.getStartTid());
            preparedStatement.setObject(3, kurs.getSluttTid());
            preparedStatement.setString(4, kurs.getBeskrivelse());
            preparedStatement.setString(5, kategori);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Rows successfully inserted!");
            }
        } catch (Exception e) {
            System.err.println("An error occured: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void removeKurs(int id) throws Exception {
        String sql = "SELECT p.epost FROM kurs_deltagere kd " + "INNER JOIN person p ON kd.person_id = p.id " + "WHERE kd.kurs_id = ?";
        if (id <= 0) {
            throw new Exception("Ugyldig kurs-ID.");
        }

        try {
            ArrayList<String> eposter = new ArrayList<>();

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        eposter.add(rs.getString("epost"));
                    }
                }
            }

            for (String epost : eposter) {
                removeDeltaker(id, epost);
            }

            String slettKurs = "DELETE FROM kurs WHERE id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(slettKurs)) {
                pstmt.setInt(1, id);

                int rowsDeleted = pstmt.executeUpdate();

                if (rowsDeleted == 0) {
                    throw new SQLException("Fant ikke kurset.");
                }
            }

        } catch (Exception e) {
            throw new Exception("Kunne ikke slette kurset.", e);
        }
    }

    // Denne koden kjøres bare gjennom metoden under.
    private int createPassord(String passord) {
        String query = "INSERT INTO password(password_hash) VALUES(?)";
        ArrayList<Integer> results = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, passord);

            pstmt.executeUpdate();

            try (ResultSet resultSet = pstmt.getGeneratedKeys()) {
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

        } catch (Exception e) {
            System.err.println("Noe gikk galt med filtreringen: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean signUp(Bruker bruker, Deltaker deltaker) {
        int passordId = createPassord(bruker.getHashPassord());
        if(passordId==0) {
            return false;
        }
        int roleId = 3;
        String query2 = "INSERT INTO auth(username, password_id, person_id, role_id) VALUES(?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query2)) {
            preparedStatement.setString(1, bruker.getBrukernavn());
            preparedStatement.setInt(2, passordId);
            preparedStatement.setInt(3, deltaker.getPersonId());
            preparedStatement.setInt(4, roleId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("An error occured: " + e.getMessage());
            e.printStackTrace();
        }
        // Hvis den ikke klarte å legge til radene i try blokken
        return false;
    }

    @Override
    public Bruker login(String brukernavn) {
        String query = "SELECT a.username, p.password_hash, r.rollenavn FROM auth AS a INNER JOIN password AS p ON a.password_id=p.id INNER JOIN rolle AS r ON a.rolle_id=r.id WHERE a.username='" + brukernavn + "';";
        Bruker bruker = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, brukernavn);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("a.username");
                    String password_hash = resultSet.getString("p.password_hash");
                    String rolle = resultSet.getString("r.rollenavn");
                    bruker = new Bruker(username, password_hash, rolle);
                }
            }
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return bruker;
    }

    /*@Override
    public boolean addDeltaker(Kurs kurs, Deltaker deltaker) {
        int personId = addPerson(deltaker.getFornavn() + " " + deltaker.getEtternavn(), deltaker.getEpost());
        if (personId == -1){
            return false;
        }
        else {
            String query = "INSERT INTO kurs_deltagere(id, registreringsdato, person_id) VALUES(?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, kurs.getId());
                preparedStatement.setObject(2, deltaker.getRegistreringsdato());
                preparedStatement.setInt(3, personId);

                int insertedRows = preparedStatement.executeUpdate();

                if(insertedRows>0) {
                    System.out.println("Added row/s successfully!");
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }*/

    @Override
    public boolean addDeltaker(int kursId, String navn, String epost) throws Exception {

        if (kursId <= 0) {
            throw new Exception("Ugyldig kurs-ID.");
        }
        if (navn == null || navn.isBlank()) {
            throw new Exception("Navn kan ikke være tomt.");
        }
        if (epost == null || !epost.contains("@") || !epost.contains(".")) {
            throw new Exception("Ugyldig e-postadresse.");
        }

        navn = navn.trim();
        epost = epost.trim().toLowerCase();

        try {
            if (!finnesKurs(kursId)) {
                throw new Exception("Kurset finnes ikke.");
            }
            int personId = finnPersonId(epost);

            if (personId == 0) {
                String sql = "INSERT INTO person (navn, epost) VALUES (?, ?)";

                try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                    pstmt.setString(1, navn);
                    pstmt.setString(2, epost);
                    pstmt.executeUpdate();

                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            personId = rs.getInt(1);
                        } else {
                            throw new SQLException("Kunne ikke opprette person.");
                        }
                    }
                }
            }

            if (erAlleredePaameldt(kursId, personId)) {
                throw new Exception("Du er allerede påmeldt dette kurset.");
            }

            String finnId = "SELECT COALESCE(MAX(id), 0) + 1 FROM kurs_deltagere";
            int nesteId;

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(finnId)) {

                rs.next();
                nesteId = rs.getInt(1);
            }

            String sql = "INSERT INTO kurs_deltagere " + "(kurs_id, id, registreringsdato, person_id) " + "VALUES (?, ?, CURDATE(), ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, kursId);
                pstmt.setInt(2, nesteId);
                pstmt.setInt(3, personId);
                pstmt.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            throw new Exception("Databasefeil ved påmelding.", e);
        }
    }

    @Override
    public boolean removeDeltaker(int kursId, String epost) throws Exception {
        if (kursId <= 0) {
            throw new Exception("Ugyldig kurs-ID.");
        }

        if (epost == null || epost.isBlank()) {
            throw new Exception("E-post kan ikke være tom.");
        }

        String sql = "DELETE kd FROM kurs_deltagere kd " + "INNER JOIN person p ON kd.person_id = p.id " + "WHERE kd.kurs_id = ? AND LOWER(p.epost) = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, kursId);
            pstmt.setString(2, epost.trim().toLowerCase());

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted == 0) {
                throw new Exception("Fant ingen påmelding for denne deltakeren.");
            }

            return true;

        } catch (SQLException e) {
            throw new Exception("Databasefeil ved avmelding.", e);
        }
    }

    private boolean finnesKurs(int kursId) throws SQLException {
        String sql = "SELECT * FROM kurs WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, kursId);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private int finnPersonId(String epost) throws SQLException {
        String sql = "SELECT id FROM person WHERE epost = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, epost);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

            return 0;
        }
    }

    private boolean erAlleredePaameldt(int kursId, int personId) throws SQLException {
        String sql = "SELECT * FROM kurs_deltagere WHERE kurs_id = ? AND person_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, kursId);
            pstmt.setInt(2, personId);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }
}