package controllers;

import dto.BrukerDTO;
import dto.DeltakerDTO;
import io.javalin.http.Context;
import service.BrukerService;

import java.util.Map;

public class BrukerController {

    private BrukerService brukerService;

    public BrukerController(BrukerService brukerService) {
        setBrukerService(brukerService);
    }

    // Logger inn en bruker.
    public void login(Context ctx) {
        try {
            // Henter innloggingsinformasjon trygt fra forespørselens JSON-body.
            BrukerDTO brukerDTO = ctx.bodyAsClass(BrukerDTO.class);

            // Sender informasjonen til service-laget for kontroll.
            String isAuthenticated = brukerService.login(brukerDTO);

            if (isAuthenticated != null) {
                ctx.status(200).json(Map.of(
                        "message", "Innlogging vellykket!",
                        "rolle", isAuthenticated
                ));
            } else {
                ctx.status(401).result("Feil brukernavn eller passord.");
            }

        } catch (Exception e) {
            ctx.status(400).result("Ugyldig forespørsel: " + e.getMessage());
        }
    }

    // Registrerer en ny bruker.
    public void signUp(Context ctx) {
        try {
            // Henter registreringsdata fra JSON-body.
            BrukerDTO brukerDTO = ctx.bodyAsClass(BrukerDTO.class);
            DeltakerDTO deltakerDTO = ctx.bodyAsClass(DeltakerDTO.class);

            // Sender dataene til service-laget, der passordet hashes.
            brukerService.signUp(deltakerDTO, brukerDTO);

            ctx.status(201).result("Bruker opprettet!");

        } catch (Exception e) {
            ctx.status(400).result("Kunne ikke opprette bruker: " + e.getMessage());
        }
    }

    public BrukerService getBrukerService() {
        return brukerService;
    }

    public void setBrukerService(BrukerService brukerService) {
        this.brukerService = brukerService;
    }
}
