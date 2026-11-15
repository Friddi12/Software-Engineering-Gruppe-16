package service;

import dto.DeltakerDTO;
import dto.BrukerDTO;
import modules.Deltaker;
import modules.Bruker;
import java.time.LocalDate;

import at.favre.lib.crypto.bcrypt.BCrypt;
import repository.BrukerRepository;
import repository.DeltakerRepository;

// Håndterer innlogging og registrering av brukere.
public class BrukerService {

    private final BrukerRepository brukerRepository;
    private final DeltakerRepository deltakerRepository;

    // Tar imot repository-grensesnittene.
    public BrukerService(BrukerRepository brukerRepository, DeltakerRepository deltakerRepository) {
        this.brukerRepository = brukerRepository;
        this.deltakerRepository = deltakerRepository;
    }

    // Logger inn en bruker og returnerer rollen.
    public String login(BrukerDTO brukerDTO) {
        Bruker bruker = brukerRepository.login(brukerDTO.getEpost());

        if (bruker == null) {
            return null;
        }

        BCrypt.Result result = BCrypt.verifyer().verify(
                brukerDTO.getPassord().toCharArray(),
                bruker.getHashPassord()
        );

        if (result.verified) {
            return bruker.getRolle();
        }

        return null;
    }

    // Registrerer en ny deltaker og bruker.
    public void signUp(DeltakerDTO deltakerDTO, BrukerDTO brukerDTO) {
        String hashedPassword = BCrypt.withDefaults().hashToString(
                12,
                brukerDTO.getPassord().toCharArray()
        );

        Deltaker nyDeltaker = new Deltaker(
                deltakerDTO.getFornavn(),
                deltakerDTO.getEtternavn(),
                deltakerDTO.getEpost(),
                LocalDate.now()
        );

        Bruker nyBruker = new Bruker(
                brukerDTO.getEpost(),
                hashedPassword,
                "deltaker",
                nyDeltaker
        );

        brukerRepository.signUp(nyBruker, nyDeltaker);
    }
}