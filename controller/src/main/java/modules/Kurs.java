package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Kurs {
    private String navn;
    private int id;
    private LocalDateTime startDato;
    private LocalDateTime sluttDato;
    private ArrayList<Deltaker> deltakerListe = new ArrayList<>();
    private String beskrivelse;
    private Kategori kategori;
    private static final DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");


    public Kurs (String navn, LocalDateTime startDato, LocalDateTime sluttDato,String beskrivelse, Kategori kategori) {
        setNavn(navn);
        setStartDato(startDato);
        setSluttDato(sluttDato);
        setBeskrivelse(beskrivelse);
        setKategori(kategori);
    }

    public Kurs (int id, String navn, LocalDateTime startDato, LocalDateTime sluttDato, Deltaker deltaker, String beskrivelse, Kategori kategori){
        this(navn, startDato, sluttDato, beskrivelse, kategori);
        setId(id);
        addDeltaker(deltaker);
    }

    public String getNavn() {return navn;}
    public void setNavn(String navn) {this.navn = navn;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public LocalDateTime getStartDato() {return startDato;}
    public void setStartDato(LocalDateTime startDato) {this.startDato = startDato;}

    public LocalDateTime getSluttDato() {return sluttDato;}
    public void setSluttDato(LocalDateTime sluttDato) {this.sluttDato = sluttDato;}

    public ArrayList<Deltaker> getDeltakerListe() {return new ArrayList<>(deltakerListe);}
    public void addDeltaker(Deltaker deltaker){deltakerListe.add(deltaker);}

    public String getBeskrivelse() {return beskrivelse;}
    public void setBeskrivelse(String beskrivelse) {this.beskrivelse = beskrivelse;}

    public Kategori getKategori() {return kategori;}
    public void setKategori(Kategori kategori) {this.kategori = kategori;}
}
