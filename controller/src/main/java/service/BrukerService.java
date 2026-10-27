package service;

import dto.DeltakerDTO;
import dto.BrukerDTO;
import modules.Deltaker;
import modules.Bruker;
import java.time.LocalDate;
import at.favre.lib.crypto.bcrypt.BCrypt;
import repository.DatabaseRepository;

public class BrukerService {
    private final DatabaseRepository databaseRepository;

    public BrukerService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public void createBruker(DeltakerDTO deltakerDTO, BrukerDTO brukerDTO) {
        // 1. Hashe passordet fra BrukerDTO med BCrypt
        String hashedPassword = BCrypt.withDefaults().hashToString(12, brukerDTO.getPassord().toCharArray());

        // 2. Opprett Deltaker ved hjelp av konstruktøren
        Deltaker nyDeltaker = new Deltaker(
                deltakerDTO.getFornavn(),
                deltakerDTO.getEtternavn(),
                deltakerDTO.getEpost(),
                LocalDate.now() // setter registreringsdato til idag
        );

        // 3. Opprett Bruker med den hashede versjonen av passordet
        Bruker nyBruker = new Bruker(brukerDTO.getBrukernavn(),  hashedPassword, nyDeltaker /*Bruker har en relasjon til Deltaker*/);

        // 4. Lagre i databasen via repository
        databaseRepository.createDeltaker(nyDeltaker);
        databaseRepository.signUp(nyBruker);
    }
}