package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Kurs {
    private String navn;
    private int id;
    private LocalDateTime startDato;
    private LocalDateTime sluttDato;
    private ArrayList<Deltager> deltagerListe = new ArrayList<>();
    private String beskrivelse;
    private Kategori kategori;
    private static final DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");


    public Kurs (String navn, LocalDateTime startDato, LocalDateTime sluttDato,String beskrivelse, Kategori kategori) {
        setNavn(navn);
        setStartDato(startDato);
        setSluttDato(sluttDato);
        setBeskrivelse(beskrivelse);
        setKategori(Kategori.valueOf(kategori));
    }

    public Kurs (int id, /*String navn, LocalDateTime startDato, LocalDateTime sluttDato,*/ ArrayList<Deltager> deltagerListe, /*String beskrivelse,*/ String kategori){
        super();
        setId(id);
        setDeltagerListe(deltagerListe);
    }

    public String getNavn() {return navn;}
    public void setNavn(String navn) {this.navn = navn;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public LocalDateTime getStartDato() {return startDato;}
    public void setStartDato(LocalDateTime startDato) {this.startDato = startDato;}
    public LocalDateTime getSluttDato() {return sluttDato;}
    public void setSluttDato(LocalDateTime sluttDato) {this.sluttDato = sluttDato;}
    public ArrayList<Deltager> getDeltagerListe() {return new ArrayList<>(deltagerListe);}
    public String getBeskrivelse() {return beskrivelse;}
    public void setBeskrivelse(String beskrivelse) {this.beskrivelse = beskrivelse;}
    public String getKategori() {return kategori;}
    public void setKategori(Kategori kategori) {this.kategori = kategori;}
}
