package controllers;

import modules.*;
import dto.KursDTO;

import io.javalin.http.Context;
import repository.KursRepository;

public class KursController {
    private KursRepository kursRepository;
    public KursController(KursRepository kursRepository) {setKursRepository(kursRepository);}

    // GET /api/kurs/search?navn=...&kategori=...
    public void search(Context ctx) {
        String navn = ctx.queryParam("navn");
        String kategoriParam = ctx.queryParam("kategori");
        Kategori kategori = null;
        String sted = ctx.queryParam("sted");

        try {
            // Sjekk om det er sendt med en kategori (ikke null og ikke tom)
            if (kategoriParam != null && !kategoriParam.isBlank()) {
                // Konverterer f.eks. "strikking" eller "STRIKKING" trygt til Enum
                kategori = Kategori.valueOf(kategoriParam.trim().toUpperCase());
            }

            ctx.json(kursRepository.searchKurs(navn, kategori, sted));
        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Ugyldig kategori: " + kategoriParam);
        }
        catch (Exception e) {
            ctx.status(400).result("Invalid search input: " + e.getMessage());
        }
    }

    public void getAllKurs(Context ctx) {
        try {
            // Assumes your repository has a method to get all courses.
            // (If your repository method is named differently, like findAll() or getAll(), adjust it here)
            ctx.json(kursRepository.getAllKurs());
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Could not fetch courses: " + e.getMessage());
        }
    }

    // POST /api/kurs (mottar JSON i body)
    public void addKurs(Context ctx) {
        try {
            // Javalin mapper JSON fra React direkte til et DTO/Objekt
            KursDTO dto = ctx.bodyAsClass(KursDTO.class);

            Kurs kurs = new Kurs(dto.getNavn(),dto.getStartTid(), dto.getSluttTid(), dto.getBeskrivelse(),
                    dto.getKategori(), dto.getSted(), dto.getAdresse());

            kursRepository.addKurs(kurs);
            ctx.status(201).json(kurs); // 201 Created
        } catch (Exception e) {
            ctx.status(400).result("Invalid input: " + e.getMessage());
        }
    }

    // DELETE /api/kurs/{id}
    public void remove(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            kursRepository.removeKurs(id); //?
            ctx.status(204); // 204 No Content (vellykket sletting uten returdata)
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid ID format");
        } catch (Exception e) {
            ctx.status(400).result("Could not remove course: " + e.getMessage());
        }
    }

    public void addDeltaker(Context ctx) {

    }

    public KursRepository getKursRepository() {return kursRepository;}
    public void setKursRepository(KursRepository kursRepository) {this.kursRepository = kursRepository;}
}
