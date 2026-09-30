package repository;

import modules.Kategori;
import modules.Kurs;
import java.util.ArrayList;

public interface KursRepository {
    ArrayList<Kurs> searchKurs(String navn, Kategori kategori);
    void addKurs(Kurs kurs);
    void removeKurs(Kurs kurs);
    void addDeltaker(String navn, String ePost);
}
