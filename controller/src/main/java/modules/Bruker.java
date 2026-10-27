package modules;

public class Bruker {
    private String brukernavn;
    private String hashPassord;
    private Deltaker deltaker;

    public Bruker() {}

    public Bruker(String brukernavn, String hashPassord, Deltaker deltaker) {
        setBrukernavn(brukernavn);
        setHashPassord(hashPassord);
        setDeltaker(deltaker);
    }

    public String getBrukernavn() {return brukernavn;}
    public void setBrukernavn(String brukernavn) {this.brukernavn = brukernavn;}

    public String getHashPassord() {return hashPassord;}
    public void setHashPassord(String hashPassord) {this.hashPassord = hashPassord;}

    public Deltaker getDeltaker() {return deltaker;}
    public void setDeltaker(Deltaker deltaker) {this.deltaker = deltaker;}
}
