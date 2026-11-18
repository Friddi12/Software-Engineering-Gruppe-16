package repository;

import modules.Deltaker;
import modules.Kategori;
import modules.Kurs;

import java.util.ArrayList;

// Grensesnitt for kurs og deltakere.
public interface KursRepository {

    ArrayList<Kurs> getAllKurs();

    // Søker etter kurs.
    ArrayList<Kurs> searchKurs(String navn, Kategori kategori, String sted);

    void addKurs(Kurs kurs);

    void editKurs(int id, Kurs kurs) throws Exception;

    void removeKurs(int id) throws Exception;

    // Håndterer påmelding og fjerning av deltakere.
    boolean addDeltaker(int kursId, String navn, String ePost) throws Exception;

    boolean removeDeltaker(int kursId, String epost) throws Exception;

    // Filtrerer kurs etter sted og kategori.
    ArrayList<Kurs> filtrerKurs(String sted, Kategori kategori);
}