package repository;

import modules.*;

import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseRepository implements KursRepository, BrukerRepository, DeltakerRepository {
    private static final String url="jdbc:mysql://localhost:3306/softwareengineering";
    private static final String user="root";
    private static final String password="";
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
    public void removeKurs(int id) {

    }

    @Override
    public boolean login(String brukernavn, String passord) {
        return false;
    }

    @Override
    public void signUp(Bruker bruker) {

    }

    @Override
    public void createDeltaker(Deltaker deltaker) {

    }

    @Override
    public void addDeltaker(Deltaker deltaker) {

    }
}