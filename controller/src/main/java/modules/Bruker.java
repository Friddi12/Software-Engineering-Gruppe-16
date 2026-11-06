package modules;

public class Bruker {
    private String brukernavn;
    private String hashPassord;
    private Deltaker deltaker;
    private String rolle;

    public Bruker() {}

    public Bruker(String brukernavn, String hashPassord,String rolle, Deltaker deltaker) {
        this(brukernavn, hashPassord, rolle);
        setDeltaker(deltaker);
    }

    public Bruker(String brukernavn, String hashPassord, String rolle) {
        setBrukernavn(brukernavn);
        setHashPassord(hashPassord);
        setRolle(rolle);
    }

    public String getBrukernavn() {return brukernavn;}
    public void setBrukernavn(String brukernavn) {this.brukernavn = brukernavn;}

    public String getHashPassord() {return hashPassord;}
    public void setHashPassord(String hashPassord) {this.hashPassord = hashPassord;}

    public Deltaker getDeltaker() {return deltaker;}
    public void setDeltaker(Deltaker deltaker) {this.deltaker = deltaker;}

    public String getRolle() {return rolle;}
    public void setRolle(String rolle) {this.rolle = rolle;}
}
