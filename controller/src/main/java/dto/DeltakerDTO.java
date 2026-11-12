package dto;

import java.time.LocalDate;

public class DeltakerDTO {
    private String fornavn;
    private String etternavn;
    private String epost;
    private LocalDate registreringsdato;

    public DeltakerDTO(){}

    public DeltakerDTO(String fornavn, String etternavn, String epost, LocalDate registreringsdato) {
        setFornavn(fornavn);
        setEtternavn(etternavn);
        setRegistreringsdato(registreringsdato);
        setEpost(epost);
    }

    public String getFornavn() {return fornavn;}
    public void setFornavn(String fornavn) {this.fornavn = fornavn;}

    public String getEtternavn() {return etternavn;}
    public void setEtternavn(String etternavn) {this.etternavn = etternavn;}

    public LocalDate getRegistreringsdato() {return registreringsdato;}
    public void setRegistreringsdato(LocalDate registreringsdato) {this.registreringsdato = registreringsdato;}

    public String getEpost() {return epost;}
    public void setEpost(String epost) {this.epost = epost;}
}
