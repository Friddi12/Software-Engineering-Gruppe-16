package repository;

import modules.Bruker;
import modules.Deltaker;

public interface BrukerRepository {
    boolean signUp(Bruker bruker, Deltaker deltaker);
    Bruker login(String brukernavn);
}
