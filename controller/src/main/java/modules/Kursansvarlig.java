package modules;

public class Kursansvarlig extends Person {

    private String telefonnummer;

    // Oppretter en kursansvarlig med personopplysninger og telefonnummer.
    public Kursansvarlig(String fornavn, String etternavn, String epost, String telefonnummer) {
        super(fornavn, etternavn, epost);
        setTelefonnummer(telefonnummer);
    }

    // Oppretter en kursansvarlig med en eksisterende person-ID.
    public Kursansvarlig(int personId, String fornavn, String etternavn, String epost, String telefonnummer) {
        this(fornavn, etternavn, epost, telefonnummer);
        super.setPersonId(personId);
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
