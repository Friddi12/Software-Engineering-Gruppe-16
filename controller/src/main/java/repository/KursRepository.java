package repository;

import modules.Deltaker;
import modules.Kategori;
import modules.Kurs;

import java.util.ArrayList;

public interface KursRepository {
    ArrayList<Kurs> getAllKurs();
    ArrayList<Kurs> searchKurs(String navn, Kategori kategori, String sted);
    void addKurs(Kurs kurs);
    void editKurs(int id, Kurs kurs) throws Exception;
    void removeKurs(int id) throws Exception;
    boolean addDeltaker(int kursId, String navn, String ePost) throws Exception;
    boolean removeDeltaker(int kursId, String epost) throws Exception;
    ArrayList<Kurs> filtrerKurs(String sted, Kategori kategori);
}
