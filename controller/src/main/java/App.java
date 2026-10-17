import io.javalin.Javalin;

import controllers.*;
import repository.*;


public class App {
    public static void main(String[] args) {
        KursRepository kursRepository = new DatabaseRepository();
        KursController kursController = new KursController(kursRepository);

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
    }
}