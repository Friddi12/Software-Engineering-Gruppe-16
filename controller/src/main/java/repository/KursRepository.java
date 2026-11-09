package repository;

import modules.Kategori;
import modules.Kurs;

import java.util.ArrayList;

public interface KursRepository {
    ArrayList<Kurs> searchKurs(String navn, Kategori kategori, String sted);
    void addKurs(Kurs kurs);
    void removeKurs(int id);
    boolean addDeltaker(int kursId, String navn, String ePost) throws Exception;
    boolean login(String brukernavn, String passord);
    ArrayList<Kurs> getAllKurs();
    ArrayList<Kurs> filtrerKurs(String sted, Kategori kategori);
}
