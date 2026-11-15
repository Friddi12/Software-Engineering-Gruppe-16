package controllers;

import dto.DeltakerDTO;
import dto.KursDTO;
import dto.PaameldingDTO;
import io.javalin.http.Context;
import modules.*;
import repository.KursRepository;

public class KursController {

    private KursRepository kursRepository;

    public KursController(KursRepository kursRepository) {
        setKursRepository(kursRepository);
    }

    // Søker etter kurs basert på navn, kategori og sted.
    // GET /api/kurs/search?navn=...&kategori=...
    public void search(Context ctx) {
        String navn = ctx.queryParam("navn");
        String kategoriParam = ctx.queryParam("kategori");
        Kategori kategori = null;
        String sted = ctx.queryParam("sted");

        try {
            if (kategoriParam != null && !kategoriParam.isBlank()) {
                kategori = Kategori.valueOf(kategoriParam.trim().toUpperCase());
            }

            ctx.json(kursRepository.searchKurs(navn, kategori, sted));

        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Ugyldig kategori: " + kategoriParam);

        } catch (Exception e) {
            ctx.status(400).result("Ugyldige søkedata: " + e.getMessage());
        }
    }

    // Henter alle tilgjengelige kurs.
    public void getAllKurs(Context ctx) {
        try {
            ctx.json(kursRepository.getAllKurs());

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Kunne ikke hente kurs: " + e.getMessage());
        }
    }

    // Filtrerer kurs basert på sted og kategori.
    public void filtrerKurs(Context ctx) {
        String sted = ctx.queryParam("sted");
        String kategoriParam = ctx.queryParam("kategori");

        try {
            if (sted == null || sted.isBlank()) {
                throw new Exception("Sted må oppgis.");
            }

            if (kategoriParam == null || kategoriParam.isBlank()) {
                throw new Exception("Kategori må oppgis.");
            }

            Kategori kategori = Kategori.valueOf(kategoriParam.trim().toUpperCase());

            ctx.json(kursRepository.filtrerKurs(sted.trim(), kategori));

        } catch (IllegalArgumentException e) {
            ctx.status(400).result("Ugyldig kategori: " + kategoriParam);

        } catch (Exception e) {
            ctx.status(400).result(e.getMessage());
        }
    }

    // Oppretter et nytt kurs.
    // POST /api/kurs (mottar JSON i forespørselen)
    public void addKurs(Context ctx) {
        try {
            KursDTO dto = ctx.bodyAsClass(KursDTO.class);

            Kurs kurs = new Kurs(
                    dto.getNavn(),
                    dto.getStartTid(),
                    dto.getSluttTid(),
                    dto.getBeskrivelse(),
                    dto.getKategori(),
                    dto.getSted(),
                    dto.getAdresse()
            );

            kursRepository.addKurs(kurs);
            ctx.status(201).json(kurs);

        } catch (Exception e) {
            ctx.status(400).result("Ugyldige inndata: " + e.getMessage());
        }
    }

    // Oppdaterer et eksisterende kurs.
    // PUT /api/kurs/{id}
    public void editKurs(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));

            KursDTO dto = ctx.bodyAsClass(KursDTO.class);

            Kurs kurs = new Kurs(
                    dto.getNavn(),
                    dto.getStartTid(),
                    dto.getSluttTid(),
                    dto.getBeskrivelse(),
                    dto.getKategori(),
                    dto.getSted(),
                    dto.getAdresse()
            );

            kursRepository.editKurs(id, kurs);

            ctx.status(204);

        } catch (Exception e) {
            ctx.status(400).result(e.getMessage());
        }
    }

    // Sletter et kurs.
    // DELETE /api/kurs/{id}
    public void remove(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));

            kursRepository.removeKurs(id);

            ctx.status(204);

        } catch (Exception e) {
            ctx.status(400).result(e.getMessage());
        }
    }

    // Fjerner en deltaker fra et kurs.
    // DELETE /api/kurs/{kursId}/deltakere
    public void removeDeltaker(Context ctx) {
        try {
            int kursId = Integer.parseInt(ctx.pathParam("kursId"));
            String epost = ctx.queryParam("epost");

            kursRepository.removeDeltaker(kursId, epost);

            ctx.status(204);

        } catch (Exception e) {
            ctx.status(400).result(e.getMessage());
        }
    }

    // Registrerer en deltaker på et kurs.
    public void addDeltaker(Context ctx) {
        try {
            PaameldingDTO dto = ctx.bodyAsClass(PaameldingDTO.class);

            if (dto == null) {
                throw new Exception("Påmeldingsdata mangler.");
            }

            kursRepository.addDeltaker(dto.getKursId(), dto.getNavn(), dto.getEpost());

            ctx.status(201).result("Påmelding registrert!");

        } catch (Exception e) {
            ctx.status(400).result(e.getMessage());
        }
    }

    public KursRepository getKursRepository() {
        return kursRepository;
    }

    public void setKursRepository(KursRepository kursRepository) {
        this.kursRepository = kursRepository;
    }
}
