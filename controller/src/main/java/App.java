import io.javalin.Javalin;

import controllers.*;
import repository.DatabaseRepository;
import repository.*;


public class App {
    public static void main(String[] args) {
        KursRepository kursRepository = new DatabaseRepository();
        KursController kursController = new KursController(kursRepository);
        BrukerController brukerController = new BrukerController(BrukerRepository);

        Javalin app = Javalin.create(config -> {
            // Tillater at kilde-apper (som React på port 5173/3000) får kalle API-et under utvikling
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {it.anyHost(); // Tillater alle domener/porter under lokal utvikling
                });
            });

            config.staticFiles.add("/public");
            config.spaRoot.addFile("/", "/public/index.html");
        }).start(8080);

        // --- API-ruter
        app.get("/api/kurs", kursController::getAllKurs);
        app.get("/api/kurs/search", kursController::search);
        app.post("/api/kurs/add", kursController::addKurs);
        app.delete("/api/kurs/{id}", kursController::remove);
        app.post("/api/deltaker", kursController::addDeltaker);
        app.get("/api/bruker/login", brukerController::login); // login gjelder både deltakere og kursansvarlig
        app.post("/api/bruker/signup", brukerController::signUp); // registrering av bruker tenker jeg gjelder bare deltakere, mens kursansvarlig kan lages direkte i databasen
    }
}