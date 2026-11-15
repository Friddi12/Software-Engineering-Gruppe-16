package repository;

// Grensesnitt for håndtering av personer.
public interface PersonRepository {
    int addPerson(String navn, String epost);
}