package controllers;

import io.javalin.http.Context;
import service.BrukerService;

public class BrukerController {
    private final BrukerService brukerService;

    public BrukerController(BrukerService brukerService) {
        this.brukerService = brukerService;
    }

    public void login(Context ctx) {
        try {
            // Henter innloggingsinfo trygt fra request body (JSON)
            LoginDto loginDto = ctx.bodyAsClass(LoginDto.class);

            // Sender til service-laget for sjekk
            boolean isAuthenticated = brukerService.login(loginDto);

            if (isAuthenticated) {
                ctx.status(200).result("Innlogging vellykket!");
            } else {
                ctx.status(401).result("Feil brukernavn eller passord.");
            }

        } catch (Exception e) {
            ctx.status(400).result("Ugyldig forespørsel: " + e.getMessage());
        }
    }

    public void signUp(Context ctx) {
        try {
            // Henter registreringsdata fra JSON-body
            BrukerRegistreringDto registreringDto = ctx.bodyAsClass(BrukerRegistreringDto.class);

            // Sender til service-laget (her skjer hashingen!)
            brukerService.createBruker(registreringDto);

            ctx.status(201).result("Bruker opprettet!");

        } catch (Exception e) {
            ctx.status(400).result("Kunne ikke opprette bruker: " + e.getMessage());
        }
    }
}
