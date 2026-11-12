import io.javalin.Javalin;

import controllers.*;
import repository.DatabaseRepository;
import repository.*;
import service.BrukerService;


public class App {
    public static void main(String[] args) {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        BrukerService brukerService = new BrukerService(databaseRepository, databaseRepository);
        BrukerController brukerController = new BrukerController(brukerService);
        KursController kursController = new KursController(databaseRepository);

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
        app.get("/api/kurs/filter", kursController::filtrerKurs);
        app.post("/api/kurs/add", kursController::addKurs);
        app.delete("/api/kurs/delete", kursController::remove);
        app.post("/api/deltaker", kursController::addDeltaker);
        app.get("/api/bruker/login", brukerController::login); // login gjelder både deltakere, kursansvarlig og admin
        app.post("/api/bruker/signup", brukerController::signUp);  // registrering av bruker tenker jeg gjelder bare deltakere, 
                                                                        // mens kursansvarlig og admin kan lages direkte i databasen
    }
}