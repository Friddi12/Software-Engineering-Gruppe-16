package modules;

import java.time.LocalDate;

public class Deltaker extends Person{
    private LocalDate registreringsdato;

    public Deltaker(int personId, String fornavn, String etternavn, String epost, LocalDate registreringsdato) {
        super(personId, fornavn, etternavn, epost);
        setRegistreringsdato(registreringsdato);
    }

    public LocalDate getRegistreringsdato() {return registreringsdato;}
    public void setRegistreringsdato(LocalDate registreringsdato) {this.registreringsdato = registreringsdato;}
}
