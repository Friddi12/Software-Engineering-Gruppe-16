package repository;

import modules.Bruker;

public interface BrukerRepository {
    void signUp(Bruker bruker);
    Bruker login(String brukernavn);
}
