package modules;

import java.time.LocalDate;

public class Deltaker extends Person {

    private LocalDate registreringsdato;

    // Oppretter en deltaker med person-ID og registreringsdato.
    public Deltaker(int personId, String fornavn, String etternavn, String epost, LocalDate registreringsdato) {
        this(fornavn, etternavn, epost, registreringsdato);
        super.setPersonId(personId);
    }

    // Oppretter en deltaker med personopplysninger og registreringsdato.
    public Deltaker(String fornavn, String etternavn, String epost, LocalDate registreringsdato) {
        super(fornavn, etternavn, epost);
        setRegistreringsdato(registreringsdato);
    }

    public LocalDate getRegistreringsdato() {
        return registreringsdato;
    }

    public void setRegistreringsdato(LocalDate registreringsdato) {
        this.registreringsdato = registreringsdato;
    }
}
