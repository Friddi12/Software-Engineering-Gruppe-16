package repository;

import modules.Kategori;
import modules.Kurs;
import modules.Kursansvarlig;

import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseRepository implements KursRepository {
    private static final String url="jdbc:mysql://localhost:3306/softwareEngineering";
    private static final String user="admin";
    private static final String password="1234";
    Connection connection;

    public DatabaseRepository() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        }

        catch(Exception e) {
            System.err.println("An error has occured: " + e);
        }

    }
    @Override
    public ArrayList<Kurs> getAllKurs() {
    ArrayList<Kurs> alleKurs = new ArrayList<>();

    String query = "SELECT k.id AS kursId, p.id AS personid, k.kursnavn AS kursnavn, k.starttid AS starttid, "
            + "k.sluttTid AS sluttTid, k.beskrivelse AS beskrivelse, k.kategori AS kategori, "
            + "ka.telefonnr AS telefonnr, p.navn AS kursansvarlig_navn, p.epost AS epost, s.navn AS sted, s.adresse AS adresse "
            + "FROM kurs AS k "
            + "INNER JOIN kursansvarlig AS ka ON k.kursansvarig_id = ka.id "
            + "INNER JOIN person AS p ON ka.person_id = p.id"
            + "INNER JOIN sted AS s ON k.sted_id = s.id";

    // Create statement locally using connection
    try (Statement localStatement = connection.createStatement();
         ResultSet resultSet = localStatement.executeQuery(query)) {
        
        while (resultSet.next()) {
            int kursId = resultSet.getInt("kursId");
            String kursnavn = resultSet.getString("kursnavn");
            LocalDateTime startTid = resultSet.getObject("starttid", LocalDateTime.class);
            LocalDateTime sluttTid = resultSet.getObject("sluttTid", LocalDateTime.class);
            String beskrivelse = resultSet.getString("beskrivelse");
            String databaseResultatKategori = resultSet.getString("kategori");
            Kategori kategori1 = Kategori.valueOf(databaseResultatKategori.toUpperCase());

            String telefonnr = resultSet.getString("telefonnr");
            String kursansvarligNavn = resultSet.getString("kursansvarlig_navn");
            String kursansvarligEpost = resultSet.getString("epost");
            int person_id = resultSet.getInt("personid");
            String sted = resultSet.getString("sted");

            String fornavn = "";
            String etternavn = "";
            if (kursansvarligNavn != null && !kursansvarligNavn.trim().isEmpty()) {
                String[] nameParts = kursansvarligNavn.trim().split("\\s+", 2);
                fornavn = nameParts[0];
                etternavn = nameParts.length > 1 ? nameParts[1] : "";
            }

            Kursansvarlig kursansvarlig = new Kursansvarlig(person_id, fornavn, etternavn, kursansvarligEpost, telefonnr);
            Kurs currentKurs = new Kurs(kursId, kursnavn, startTid, sluttTid, beskrivelse, kategori1, sted);
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
        String query = "SELECT p.id AS personid, k.kursnavn AS kursnavn, k.starttid AS starttid, k.sluttTid AS sluttTid, k.beskrivelse AS beskrivelse, k.kategori AS kategori, ka.telefonnr AS telefonnr, p.navn AS kursansvarig_navn, p.epost AS epost FROM kurs AS k INNER JOIN kursansvarig AS ka ON k.kursansvarig_id = ka.id INNER JOIN person AS p ON ka.person_id = p.id WHERE kursnavn = ? AND kategori = ?";

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

                    Kategori kategori1 = Kategori.valueOf(databaseResultatKategori.toUpperCase());

                    String telefonnr = resultSet.getString("telefonnr");
                    String kursasnvarigNavn = resultSet.getString("kursansvarig_navn");
                    String kursansvarigEpost = resultSet.getString("epost");

                    int person_id = resultSet.getInt("personid");
                    String[] name = kursasnvarigNavn.split(" ");
                    Kursansvarlig kursansvarlig = new Kursansvarlig(person_id, name[0], name[1], kursansvarigEpost, telefonnr);
                    Kurs currentKurs = new Kurs(1, kursnavn, startTid, sluttTid, beskrivelse, kategori1, "Halden");
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
        String query = "INSERT INTO kurs('kursnavn', 'starttid', 'sluttTid', 'beskrivelse', 'kategori') VALUES('" + kurs.getNavn() + "', '" + kurs.getStartTid() + "', '" + kurs.getSluttTid() + "', '" + kurs.getBeskrivelse() + "', '" + kategori + "');";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, kurs.getNavn());
            preparedStatement.setObject(2, kurs.getStartTid());
            preparedStatement.setObject(3, kurs.getSluttTid());
            preparedStatement.setString(4, kurs.getBeskrivelse());
            preparedStatement.setString(5, kategori);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted>0) {
                System.out.println("Rows successfully inserted!");
            }
        }

        catch (Exception e) {
            System.err.println("An error occured: " + e.getMessage());
        }
    }

    @Override
    public void removeKurs(int id) {

    }

    @Override
    public void addDeltaker(String navn, String ePost) {

    }

    @Override
    public boolean login(String brukernavn, String passord) {

        return false;
    }
}
