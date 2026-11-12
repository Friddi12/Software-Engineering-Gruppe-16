USE softwareEngineering;

-- 1. Persons
INSERT INTO person (id, navn, epost) VALUES
(1, 'Kari Nordmann', 'kari.nordmann@example.com'),
(2, 'Ola Hansen', 'ola.hansen@example.com'),
(3, 'Astrid Lind', 'astrid.lind@example.com'),
(4, 'Lars Berg', 'lars.berg@example.com'),
(5, 'Ingrid Solberg', 'ingrid.solberg@example.com');

-- 2. Locations (sted)
INSERT INTO sted (id, navn, adresse) VALUES
(1, 'Kulturhuset Sentrum', 'Storgata 12, 0182 Oslo'),
(2, 'Håndverksloftet', 'Fjordveien 45, 5003 Bergen'),
(3, 'Grünerløkka Fritidsklubb', 'Thorvald Meyers gate 33, 0555 Oslo');

-- 3. Course Coordinators (kursansvarlig)
-- Linked to Kari (1) and Ola (2)
INSERT INTO kursansvarlig (id, telefonnr, person_id) VALUES
(1, '+47 98765432', 1),
(2, '+47 41234567', 2);

-- 4. Roles
INSERT INTO role (id, rollenavn) VALUES
(1, 'ADMIN'),
(2, 'INSTRUCTOR'),
(3, 'STUDENT');

-- 5. Passwords (dummy BCrypt hashes for testing, plaintext: "password123")
INSERT INTO password (id, password_hash) VALUES
(1, '$2a$12$e8Y5t1R1bovrK6H2p8aDxe8UoFj2V/2J4K4aU6XlJ1l4xH7GvhO8e'),
(2, '$2a$12$e8Y5t1R1bovrK6H2p8aDxe8UoFj2V/2J4K4aU6XlJ1l4xH7GvhO8e'),
(3, '$2a$12$e8Y5t1R1bovrK6H2p8aDxe8UoFj2V/2J4K4aU6XlJ1l4xH7GvhO8e');

-- 6. Authentication records
INSERT INTO auth (id, username, password_id, person_id, role_id) VALUES
(1, 'kari_admin', 1, 1, 1),
(2, 'ola_inst', 2, 2, 2),
(3, 'astrid_student', 3, 3, 3);

-- 7. Courses (kurs)
-- kategori uses STRIKKING, VEVING, SYING
INSERT INTO kurs (id, kursnavn, startTid, sluttTid, beskrivelse, kategori, kursansvarlig_id, sted_id) VALUES
(1, 'Introduksjon til Strikking', '2026-11-01 10:00:00', '2026-11-01 14:00:00', 'Lær grunnleggende masker, rett og vrang.', 'STRIKKING', 1, 1),
(2, 'Tradisjonell Billedveving', '2026-11-05 17:30:00', '2026-11-05 20:30:00', 'Oppsett av vev og enkle geometriske mønstre.', 'VEVING', 1, 2),
(3, 'Søm av Festdrakter og Reparasjon', '2026-11-10 12:00:00', '2026-11-10 16:00:00', 'Videregående kurs i håndsøm og maskinbruk.', 'SYING', 2, 3);

-- 8. Course Participants (kurs_deltagere)
-- Note: id is explicitly assigned; kurs_id links to kurs, person_id links to person
INSERT INTO kurs_deltagere (kurs_id, id, registreringsdato, person_id) VALUES
(1, 1, '2026-10-15', 3),
(1, 2, '2026-10-16', 4),
(2, 3, '2026-10-16', 3),
(3, 4, '2026-10-17', 5);