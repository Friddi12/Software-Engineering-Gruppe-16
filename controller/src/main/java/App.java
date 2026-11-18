import io.javalin.Javalin;

import controllers.*;
import repository.*;
import service.BrukerService;

// Starter applikasjonen og setter opp API-rutene.
public class App {

    public static void main(String[] args) {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        BrukerService brukerService = new BrukerService(databaseRepository);

        BrukerController brukerController = new BrukerController(brukerService);
        KursController kursController = new KursController(databaseRepository);

        Javalin app = Javalin.create(config -> {
            // Tillater API-kall fra andre domener under utvikling.
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });

            config.staticFiles.add("/public");
            config.spaRoot.addFile("/", "/public/index.html");
        }).start(8080);

        // API-ruter for kurs.
        app.get("/api/kurs", kursController::getAllKurs);
        app.get("/api/kurs/search", kursController::search);
        app.get("/api/kurs/filter", kursController::filtrerKurs);
        app.post("/api/kurs/add", kursController::addKurs);
        app.put("/api/kurs/{id}", kursController::editKurs);
        app.delete("/api/kurs/{id}", kursController::remove);

        // API-ruter for deltakere.
        app.post("/api/deltaker", kursController::addDeltaker);
        app.delete("/api/kurs/{kursId}/deltakere", kursController::removeDeltaker);

        // API-ruter for brukere.
        app.get("/api/bruker/login", brukerController::login);
        app.post("/api/bruker/signup", brukerController::signUp);
    }
}