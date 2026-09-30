package modules;

public class Kursansvarlig extends Person{
    private int telefonnummer;

    public Kursansvarlig(int personId, String fornavn, String etternavn, String epost, int telefonnummer) {
        super(personId, fornavn, etternavn, epost);
        setTelefonnummer(telefonnummer);
    }

    public int getTelefonnummer() {return telefonnummer;}
    public void setTelefonnummer(int telefonnummer) {this.telefonnummer = telefonnummer;}
}
