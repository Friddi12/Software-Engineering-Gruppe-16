package repository;

import modules.Kategori;
import modules.Kurs;

import java.util.ArrayList;

public interface KursRepository {
    ArrayList<Kurs> getAllKurs();
    ArrayList<Kurs> searchKurs(String navn, Kategori kategori, String sted);
    void addKurs(Kurs kurs);
    void removeKurs(int id);
}
