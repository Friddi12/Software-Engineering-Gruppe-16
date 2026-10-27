package dto;

public class BrukerDTO {
    private String brukernavn;
    private String passord;

    public BrukerDTO(){};

    public BrukerDTO(String brukernavn, String passord) {
        setBrukernavn(brukernavn);
        setPassord(passord);
    }

    public String getBrukernavn() {return brukernavn;}
    public void setBrukernavn(String brukernavn) {this.brukernavn = brukernavn;}
    public String getPassord() {return passord;}
    public void setPassord(String passord) {this.passord = passord;}
}
