package repository;

import modules.Bruker;

public interface BrukerRepository {
    boolean login(String brukernavn, String passord);
    void signUp(Bruker bruker);
    Bruker getBruker(String brukernavn);
}
