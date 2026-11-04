package service;

import dto.DeltakerDTO;
import dto.BrukerDTO;
import modules.Deltaker;
import modules.Bruker;
import java.time.LocalDate;
import at.favre.lib.crypto.bcrypt.BCrypt;
import repository.BrukerRepository;
import repository.DeltakerRepository; // Importer grensesnittet her

public class BrukerService {
    private final BrukerRepository brukerRepository;
    private final DeltakerRepository deltakerRepository; // Legg til denne

    // Tar imot begge grensesnittene i konstruktøren (eller én felles, men dette er veldig ryddig)
    public BrukerService(BrukerRepository brukerRepository, DeltakerRepository deltakerRepository) {
        this.brukerRepository = brukerRepository;
        this.deltakerRepository = deltakerRepository;
    }

    public boolean login(BrukerDTO brukerDTO) {
        Bruker bruker = brukerRepository.login(brukerDTO.getEpost());
        if (bruker == null) {
            return false;
        }

        BCrypt.Result result = BCrypt.verifyer().verify(
                brukerDTO.getPassord().toCharArray(),
                bruker.getHashPassord()
        );
        return result.verified;
    }

    public void signUp(DeltakerDTO deltakerDTO, BrukerDTO brukerDTO) {
        String hashedPassword = BCrypt.withDefaults().hashToString(12, brukerDTO.getPassord().toCharArray());

        Deltaker nyDeltaker = new Deltaker(
                deltakerDTO.getFornavn(),
                deltakerDTO.getEtternavn(),
                deltakerDTO.getEpost(),
                LocalDate.now()
        );

        Bruker nyBruker = new Bruker(
                brukerDTO.getEpost(),
                hashedPassword,
                nyDeltaker
        );

        // Bruker DeltakerRepository her for å lagre deltakeren
        deltakerRepository.createDeltaker(nyDeltaker);

        // Bruker BrukerRepository her for å lagre brukeren
        brukerRepository.signUp(nyBruker);
    }
}