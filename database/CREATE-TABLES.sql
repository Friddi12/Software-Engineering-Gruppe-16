CREATE SCHEMA IF NOT EXISTS softwareEngineering CHARSET utf8mb3;
USE softwareEngineering;

CREATE TABLE person (
                        id INT NOT NULL AUTO_INCREMENT,
                        navn VARCHAR(45),
                        epost VARCHAR(45),
                        PRIMARY KEY(id)
);

CREATE TABLE kommune (
    id INT NOT NULL AUTO_INCREMENT,
    navn VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE sted (
                      id INT NOT NULL AUTO_INCREMENT,
                      navn VARCHAR(45),
                      adresse VARCHAR(100),
                      kommune_id INT NOT NULL,
                      PRIMARY KEY(id, kommune_id),
                      FOREIGN KEY (kommune_id) REFERENCES kommune(id)
);

CREATE TABLE kursansvarlig (
                               id INT NOT NULL AUTO_INCREMENT,
                               telefonnr VARCHAR(45),
                               person_id INT NOT NULL,
                               PRIMARY KEY(id, person_id),
                               FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE role (
                      id INT NOT NULL AUTO_INCREMENT,
                      rollenavn VARCHAR(45),
                      PRIMARY KEY(id)
);

CREATE TABLE password (
                          id INT NOT NULL AUTO_INCREMENT,
                          password_hash VARCHAR(255),
                          PRIMARY KEY(id)
);

CREATE TABLE auth (
                      id INT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(45),
                      password_id INT NOT NULL,
                      person_id INT NOT NULL,
                      role_id INT NOT NULL,
                      PRIMARY KEY(id, password_id, person_id, role_id),
                      FOREIGN KEY (password_id) REFERENCES password(id),
                      FOREIGN KEY (person_id) REFERENCES person(id),
                      FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE kurs (
                      id INT NOT NULL AUTO_INCREMENT,
                      kursnavn VARCHAR(45),
                      startTid DATETIME,
                      sluttTid DATETIME,
                      beskrivelse VARCHAR(500),
                      kategori VARCHAR(45),
                      kursansvarlig_id INT NOT NULL,
                      sted_id INT NOT NULL,
                      PRIMARY KEY(id, kursansvarlig_id),
                      FOREIGN KEY (kursansvarlig_id) REFERENCES kursansvarlig(id),
                      FOREIGN KEY (sted_id) REFERENCES sted(id)
);

CREATE TABLE kurs_deltagere (
                                kurs_id INT NOT NULL AUTO_INCREMENT,
                                id INT NOT NULL,
                                registreringsdato DATE,
                                person_id INT NOT NULL,
                                PRIMARY KEY(kurs_id, id, person_id),
                                FOREIGN KEY (kurs_id) REFERENCES kurs(id),
                                FOREIGN KEY (person_id) REFERENCES person(id)
);
