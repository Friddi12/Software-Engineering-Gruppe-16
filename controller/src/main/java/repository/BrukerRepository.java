package repository;

import modules.Bruker;
import modules.Deltaker;

public interface BrukerRepository {

    // Registrerer en ny bruker og tilhørende deltaker.
    boolean signUp(Bruker bruker, Deltaker deltaker);

    // Henter en bruker basert på brukernavn.
    Bruker login(String brukernavn);
}
