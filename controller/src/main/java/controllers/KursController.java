package controllers;

import modules.*;
import DTO.KursDTO;

import io.javalin.http.Context;
import repository.KursRepository;

public class KursController {
    private KursRepository kursRepository;
    public KursController(KursRepository kursRepository) {setKursRepository(kursRepository);}

    // GET /api/kurs/search?navn=...&kategori=...
    public void search(Context ctx) {
        try {
            String navn = ctx.queryParam("navn");
            String kategori = ctx.queryParam("kategori");

            ctx.json(searchKurs(navn, kategori));
        } catch (Exception e) {
            ctx.status(400).result("Invalid search input: " + e.getMessage());
        }
    }
    // POST /api/kurs (mottar JSON i body)
    public void addKurs(Context ctx) {
        try {
            // Javalin mapper JSON fra React direkte til et DTO/Objekt
            KursDTO dto = ctx.bodyAsClass(KursDTO.class);

            Kurs kurs = new Kurs(
                    dto.navn,
                    dto.startTid,
                    dto.sluttTid,
                    dto.beskrivelse,
                    dto.kategori
            );

            addkurs(kurs);
            ctx.status(201).json(kurs); // 201 Created
        } catch (Exception e) {
            ctx.status(400).result("Invalid input: " + e.getMessage());
        }
    }
    // DELETE /api/kurs/{id}
    public void remove(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            removeKurs(id);
            ctx.status(204); // 204 No Content (vellykket sletting uten returdata)
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid ID format");
        } catch (Exception e) {
            ctx.status(400).result("Could not remove course: " + e.getMessage());
        }
    }

    public KursRepository getKursRepository() {return kursRepository;}
    public void setKursRepository(KursRepository kursRepository) {this.kursRepository = kursRepository;}
}
