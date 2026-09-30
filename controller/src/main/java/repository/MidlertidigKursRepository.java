package repository;

import modules.Kategori;
import modules.Kurs;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MidlertidigKursRepository implements KursRepository {

    private ArrayList<Kurs> kursListe = new ArrayList<>();

    public MidlertidigKursRepository() {

        kursListe.add(new Kurs(
                "Strikkekurs",
                LocalDateTime.of(2026, 10, 10, 17, 0),
                LocalDateTime.of(2026, 10, 10, 20, 0),
                "Strikkekurs for nybegynnere",
                Kategori.STRIKKING
        ));

        kursListe.add(new Kurs(
                "Vevkurs",
                LocalDateTime.of(2026, 10, 15, 17, 0),
                LocalDateTime.of(2026, 10, 15, 20, 0),
                "Introduksjon til veving",
                Kategori.VEVING
        ));

        kursListe.add(new Kurs(
                "Sykurs",
                LocalDateTime.of(2026, 10, 20, 17, 0),
                LocalDateTime.of(2026, 10, 20, 20, 0),
                "Sykurs for nybegynnere",
                Kategori.SYING
        ));
    }

    @Override
    public ArrayList<Kurs> searchKurs(String navn, Kategori kategori) {

        ArrayList<Kurs> resultater = new ArrayList<>();

        for (Kurs kurs : kursListe) {

            if (navn != null && kurs.getNavn().toLowerCase().contains(navn.toLowerCase())) {
                resultater.add(kurs);
            }
            else if (kategori != null && kurs.getKategori() == kategori) {
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
    public void removeKurs(Kurs kurs) {
        kursListe.remove(kurs);
    }

    @Override
    public void addDeltaker(String navn, String ePost) {

    }

    @Override
    public boolean login(String brukernavn, String passord) {
        return false;
    }
}
