package modules;

public class Kursansvarlig extends Person{
    private String telefonnummer;

    public Kursansvarlig(String fornavn, String etternavn, String epost, String telefonnummer) {
        super(fornavn, etternavn, epost);
        setTelefonnummer(telefonnummer);
        }

    public Kursansvarlig(int personId, String fornavn, String etternavn, String epost, String telefonnummer) {
        this(fornavn, etternavn, epost, telefonnummer);
        super.setPersonId(personId);
    }

    public String getTelefonnummer() {return telefonnummer;}
    public void setTelefonnummer(String telefonnummer) {this.telefonnummer = telefonnummer;}
}
