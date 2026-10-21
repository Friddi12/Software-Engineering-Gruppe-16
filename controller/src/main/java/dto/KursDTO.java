package dto;

import modules.Kategori;

import java.time.LocalDateTime;

public class KursDTO {
    private String navn;
    private LocalDateTime startTid;
    private LocalDateTime sluttTid;
    private String beskrivelse;
    private Kategori kategori;
    private String sted;
    private String adresse;

    // Nødvendig for at Jackson/Javalin skal kunne opprette DTO-en fra JSON:
    public KursDTO() {}

    public KursDTO(String navn, LocalDateTime startTid, LocalDateTime sluttTid, String beskrivelse, Kategori kategori, String sted, String adresse) {
        setNavn(navn);
        setStartTid(startTid);
        setSluttTid(sluttTid);
        setBeskrivelse(beskrivelse);
        setKategori(kategori);
        setSted(sted);
        setAdresse(adresse);
    }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public LocalDateTime getStartTid() { return startTid; }
    public void setStartTid(LocalDateTime startTid) { this.startTid = startTid; }

    public LocalDateTime getSluttTid() { return sluttTid; }
    public void setSluttTid(LocalDateTime sluttTid) { this.sluttTid = sluttTid; }

    public String getBeskrivelse() { return beskrivelse; }
    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }

    public String getSted() {return sted;}
    public void setSted(String sted) {this.sted = sted;}

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}
