package repository;

import modules.Kategori;
import modules.Kurs;

import java.util.ArrayList;

public class DatabaseRepository implements KursRepository {
    @Override
    public ArrayList<Kurs> searchKurs(String navn, Kategori kategori) {
        return null;
    }

    @Override
    public void addKurs(Kurs kurs) {

    }

    @Override
    public void removeKurs(Kurs kurs) {

    }

    @Override
    public void addDeltaker(String navn, String ePost) {

    }

    @Override
    public boolean login(String brukernavn, String passord) {

        return false;
    }
}
