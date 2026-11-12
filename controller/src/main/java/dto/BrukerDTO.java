package dto;

public class BrukerDTO {
    private String epost; //gir mer mening at eposten brukes som brukernavnet med tanke på hvem kunden er
    private String passord;

    public BrukerDTO(){};

    public BrukerDTO(String epost, String passord) {
        setEpost(epost);
        setPassord(passord);
    }

    public String getEpost() {return epost;}
    public void setEpost(String epost) {this.epost = epost;}
    public String getPassord() {return passord;}
    public void setPassord(String passord) {this.passord = passord;}
}
