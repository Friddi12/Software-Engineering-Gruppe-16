package modules;

public class Kursansvarlig extends Person{
    private String telefonnummer;

    public Kursansvarlig(int personId, String fornavn, String etternavn, String epost, String telefonnummer) {
        super(personId, fornavn, etternavn, epost);
        setTelefonnummer(telefonnummer);
    }

    public String getTelefonnummer() {return telefonnummer;}
    public void setTelefonnummer(String telefonnummer) {this.telefonnummer = telefonnummer;}
}
