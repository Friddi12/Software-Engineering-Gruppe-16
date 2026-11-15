package dto;

public class BrukerDTO {

    // E-post brukes som brukernavn for å identifisere kunden.
    private String epost;
    private String passord;

    public BrukerDTO() {
    }

    public BrukerDTO(String epost, String passord) {
        setEpost(epost);
        setPassord(passord);
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
