package dto;

import java.time.LocalDate;

public class DeltakerDTO {
    private String fornavn;
    private String etternavn;
    private LocalDate dato;

    public DeltakerDTO(String fornavn, String etternavn, LocalDate dato) {
        setFornavn(fornavn);
        setEtternavn(etternavn);
        setDato(dato);
    }

    public String getFornavn() {return fornavn;}
    public void setFornavn(String fornavn) {this.fornavn = fornavn;}

    public String getEtternavn() {return etternavn;}
    public void setEtternavn(String etternavn) {this.etternavn = etternavn;}

    public LocalDate getDato() {return dato;}
    public void setDato(LocalDate dato) {this.dato = dato;}
}
