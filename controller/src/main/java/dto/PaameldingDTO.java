package dto;

public class PaameldingDTO {

    private int kursId;
    private String navn;
    private String epost;

    // Tom konstruktør for opprettelse fra JSON.
    public PaameldingDTO() {
    }

    // Oppretter en påmelding med kurs-ID, navn og e-postadresse.
    public PaameldingDTO(int kursId, String navn, String epost) {
        setKursId(kursId);
        setNavn(navn);
        setEpost(epost);
    }

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
}
