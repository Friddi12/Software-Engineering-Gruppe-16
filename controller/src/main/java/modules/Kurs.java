package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Kurs {
    private String navn;
    private int id;
    private LocalDateTime startTid;
    private LocalDateTime sluttTid;
    private ArrayList<Deltaker> deltakerListe = new ArrayList<>();
    private String beskrivelse;
    private Kategori kategori;
    private String sted;
    private String adresse;
    private static final DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");


    public Kurs (String navn, LocalDateTime startTid, LocalDateTime sluttTid, String beskrivelse, Kategori kategori, String sted,  String adresse) {
        setNavn(navn);
        setStartTid(startTid);
        setSluttTid(sluttTid);
        setBeskrivelse(beskrivelse);
        setKategori(kategori);
        setSted(sted);
        setAdresse(adresse);
    }

    public Kurs (int id, String navn, LocalDateTime startTid, LocalDateTime sluttTid, String beskrivelse, Kategori kategori, String sted, String adresse) {
        this(navn, startTid, sluttTid, beskrivelse, kategori, sted, adresse);
        setId(id);
    }

    public String getNavn() {return navn;}
    public void setNavn(String navn) {this.navn = navn;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public LocalDateTime getStartTid() {return startTid;}
    public void setStartTid(LocalDateTime startDato) {this.startTid = startDato;}

    public LocalDateTime getSluttTid() {return sluttTid;}
    public void setSluttTid(LocalDateTime sluttTid) {this.sluttTid = sluttTid;}

    public ArrayList<Deltaker> getDeltakerListe() {return new ArrayList<>(deltakerListe);}
    public void addDeltaker(Deltaker deltaker){deltakerListe.add(deltaker);}

    public String getBeskrivelse() {return beskrivelse;}
    public void setBeskrivelse(String beskrivelse) {this.beskrivelse = beskrivelse;}

    public Kategori getKategori() {return kategori;}
    public void setKategori(Kategori kategori) {this.kategori = kategori;}

    public String getSted() {return sted;}
    public void setSted(String sted) {this.sted = sted;}

    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}
}
