USE softwareEngineering;

-- ============================================================
-- 1. Kommuner (Municipalities in Østfold)
-- ============================================================
INSERT INTO kommune (id, navn) VALUES
(1, 'Halden'),
(2, 'Fredrikstad'),
(3, 'Sarpsborg'),
(4, 'Moss'),
(5, 'Indre Østfold');

-- ============================================================
-- 2. Steder (Locations in Østfold linked to Kommuner)
-- ============================================================
INSERT INTO sted (id, navn, adresse, kommune_id) VALUES
(1, 'Halden Husflidslag, Rød Herregård', 'Herregårdsveien 10, 1771 Halden', 1),
(2, 'Plus-skolen / Gamlebyen Tekstilverksted', 'Kirkegaten 28, 1632 Gamle Fredrikstad', 2),
(3, 'Sarpsborg Husflidslag', 'St. Marie gate 105, 1706 Sarpsborg', 3),
(4, 'Møllebyen Tekstilstudio', 'Fossen 20, 1530 Moss', 4),
(5, 'Kulturtorget Askim', 'Skolegata 12, 1830 Askim', 5);

-- ============================================================
-- 3. Personer (Instructors and Attendees)
-- ============================================================
INSERT INTO person (id, navn, epost) VALUES
-- Instructors (person_id 1 - 5)
(1, 'Gudrun Solberg', 'gudrun.solberg@husflid.no'),
(2, 'Astrid Nygård', 'astrid.nygard@tekstilkunst.no'),
(3, 'Marit Johansen', 'marit.johansen@sarpsborghusflid.no'),
(4, 'Solveig Holm', 'solveig.holm@mollebyen.no'),
(5, 'Berit Lind', 'berit.lind@askimtekstil.no'),
-- Attendees (person_id 6 - 10)
(6, 'Sofie Lie', 'sofie.lie@example.com'),
(7, 'Henrik Dahl', 'henrik.dahl@example.com'),
(8, 'Emilie Moen', 'emilie.moen@example.com'),
(9, 'Jonas Bakke', 'jonas.bakke@example.com'),
(10, 'Nora Kristiansen', 'nora.kristiansen@example.com');

-- ============================================================
-- 4. Kursansvarlige (Instructors linked to Persons)
-- ============================================================
INSERT INTO kursansvarlig (id, telefonnr, person_id) VALUES
(1, '+47 912 34 567', 1),
(2, '+47 923 45 678', 2),
(3, '+47 934 56 789', 3),
(4, '+47 945 67 890', 4),
(5, '+47 956 78 901', 5);

-- ============================================================
-- 5. Roles & Passwords & Auth (Account Setup)
-- ============================================================
INSERT INTO role (id, rollenavn) VALUES
(1, 'Admin'),
(2, 'Instruktør'),
(3, 'Deltager');

INSERT INTO password (id, password_hash) VALUES
(1, '$2b$12$e6fK1hB9e3uF08Q6bIqO3OvhYt7PjO/Gf1NlmQ2KzQf8pXg5m5t8O'),
(2, '$2b$12$d4vM2kL1x8zC92P4aUrK1OyePt5QkP/He2OmkP1JzPf7oWe4l4s7P');

INSERT INTO auth (id, username, password_id, person_id, role_id) VALUES
(1, 'gudrun.solberg', 1, 1, 2),
(2, 'astrid.nygard', 1, 2, 2),
(3, 'marit.johansen', 1, 3, 2),
(4, 'solveig.holm', 1, 4, 2),
(5, 'berit.lind', 1, 5, 2),
(6, 'sofie.lie', 2, 6, 3);

-- ============================================================
-- 6. Kurs (5 Courses using only: STRIKKING, VEVING, SYING)
-- ============================================================
INSERT INTO kurs (id, kursnavn, startTid, sluttTid, beskrivelse, kategori, kursansvarlig_id, sted_id) VALUES
(1, 'Klassisk Marius- og Mønsterstrikk', '2026-11-05 10:00:00', '2026-11-05 16:00:00', 'Lær flerfargestrikk, diagramlesing og montering av tradisjonsstrikk på historiske Rød Herregård i Halden.', 'STRIKKING', 1, 1),
(2, 'Billedvev og Grindvev for Nybegynnere', '2026-11-12 17:00:00', '2026-11-12 21:00:00', 'Innføring i renning, innslag og mønsterdannelse med tradisjonelle teknikker i Gamlebyen i Fredrikstad.', 'VEVING', 2, 2),
(3, 'Søm av Festdrakter og Bunadskjorter', '2026-11-18 09:00:00', '2026-11-18 15:30:00', 'Lær nøyaktig håndsøm, legg, hullsøm og maskinsøm tilpasset tradisjonelle tekstiler i Sarpsborg.', 'SYING', 3, 3),
(4, 'Kloster- og Skaftvev i Praksis', '2026-11-25 09:30:00', '2026-11-25 15:30:00', 'Helgekurs i oppsetting av vevstol, trøing og veving av løpere og brukstekstiler ved Møllebyen i Moss.', 'VEVING', 4, 4),
(5, 'Redesign og Reparasjonssøm', '2026-12-02 11:00:00', '2026-12-02 16:00:00', 'Praktisk kurs i tilpassing, omsøm av vintageklær og usynlig lappeteknikk på Kulturtorget i Askim.', 'SYING', 5, 5);

-- ============================================================
-- 7. Kursdeltagere (Attendees Enrolled in Courses)
-- ============================================================
INSERT INTO kurs_deltagere (kurs_id, id, registreringsdato, person_id) VALUES
(1, 1, '2026-10-15', 6),
(1, 2, '2026-10-16', 7),
(2, 3, '2026-10-18', 8),
(3, 4, '2026-10-19', 9),
(4, 5, '2026-10-20', 10),
(5, 6, '2026-10-21', 6);
