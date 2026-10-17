package modules;

import java.time.LocalDate;

public abstract class Person {
    private int personId;
    private String fornavn;
    private String etternavn;
    private String epost;

    public Person(int personId, String fornavn, String etternavn, String epost) {
        setPersonId(personId);
        setFornavn(fornavn);
        setEtternavn(etternavn);
        setEpost(epost);
    }

    public int getPersonId() {return personId;}
    public void setPersonId(int personId){this.personId = personId;}
    public String getFornavn() {return fornavn;}
    public void setFornavn(String fornavn) {this.fornavn = fornavn;}
    public String getEtternavn() {return etternavn;}
    public void setEtternavn(String etternavn) {this.etternavn = etternavn;}
    public String getEpost() {return epost;}
    public void setEpost(String epost) {this.epost = epost;}
}
