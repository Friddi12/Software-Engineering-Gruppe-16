package repository;

import modules.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MidlertidigKursRepository implements KursRepository {

    private ArrayList<Kurs> kursListe = new ArrayList<>();

    public MidlertidigKursRepository() {

        addKurs(new Kurs(
                1,
                "Strikkekurs",
                LocalDateTime.of(2026, 10, 10, 17, 0),
                LocalDateTime.of(2026, 10, 10, 20, 0),
                "Strikkekurs for nybegynnere",
                Kategori.STRIKKING,
                "Halden"
        ));

        addKurs(new Kurs(
                2,
                "Vevkurs",
                LocalDateTime.of(2026, 10, 15, 17, 0),
                LocalDateTime.of(2026, 10, 15, 20, 0),
                "Introduksjon til veving",
                Kategori.VEVING,
                "Fredrikstad"
        ));

        addKurs(new Kurs(
                3,
                "Sykurs",
                LocalDateTime.of(2026, 10, 20, 17, 0),
                LocalDateTime.of(2026, 10, 20, 20, 0),
                "Sykurs for nybegynnere",
                Kategori.SYING,
                "Sarpsborg"
        ));
    }

    @Override
    public ArrayList<Kurs> searchKurs(String navn, Kategori kategori, String sted) {

        ArrayList<Kurs> resultater = new ArrayList<>();

        for (Kurs kurs : kursListe) {

            if (navn != null && kurs.getNavn().toLowerCase().contains(navn.toLowerCase())) {
                resultater.add(kurs);
            } else if (kategori != null && kurs.getKategori() == kategori) {
                resultater.add(kurs);
            } else if (sted != null && kurs.getSted().toLowerCase().contains(sted.toLowerCase())) {
                resultater.add(kurs);
            }
        }

        return resultater;
    }

    @Override
    public void addKurs(Kurs kurs) {
        kursListe.add(kurs);
    }

    @Override
    public void removeKurs(int id) {
        for (Kurs kurs : kursListe) {
            if (kurs.getId() == id) {
                kursListe.remove(kurs);
            }
        }
    }

    @Override
    public void addDeltaker(String navn, String ePost) {

    }

    @Override
    public boolean login(String brukernavn, String passord) {
        return false;
    }

    @Override
    public ArrayList<Kurs> getAllKurs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllKurs'");
    }
}
