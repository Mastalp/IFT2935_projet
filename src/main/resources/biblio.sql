BEGIN TRANSACTION;
DROP SCHEMA IF EXISTS BIBLIO CASCADE;
CREATE SCHEMA BIBLIO;
SET SEARCH_PATH TO BIBLIO;

CREATE TABLE LIVRES
(
    NOLIVRE INT PRIMARY KEY,
    QTITY INT, 					-- ADDED
    AUTEUR VARCHAR(32),
    GENRE VARCHAR(16),
    TITRE VARCHAR(32)
    --ESTEMPRUNTE BOOLEAN
);

CREATE TABLE ADHERENTS
(
    NOADHERENT INT PRIMARY KEY,
    NOM VARCHAR(32),
    ADRESSE VARCHAR(64)
);

CREATE TABLE COMMANDES
(
    NOCOMMANDE INT PRIMARY KEY,
    NOLIVRE INT,
    NOADHERENT INT,
    DATECOMMANDE TIMESTAMP,
    STATUT VARCHAR(10),             -- ADDED
    FOREIGN KEY (NOLIVRE) REFERENCES LIVRES(NOLIVRE),
    FOREIGN KEY (NOADHERENT) REFERENCES ADHERENTS(NOADHERENT)
);

CREATE TABLE EMPRUNTS
(
    NOEMPRUNT INT PRIMARY KEY,
    NOLIVRE INT,
    NOADHERENT INT,
    DATEEMPRUNT TIMESTAMP,
    DATERETOUR TIMESTAMP,           -- ADDED
    FOREIGN KEY (NOLIVRE) REFERENCES LIVRES(NOLIVRE),
    FOREIGN KEY (NOADHERENT) REFERENCES ADHERENTS(NOADHERENT)
);

INSERT INTO LIVRES (NOLIVRE, QTITY, AUTEUR, GENRE, TITRE) VALUES
(1, 3, 'Victor Hugo', 'Roman', 'Les Miserables'),
(2, 2, 'Alexandre Dumas', 'Aventure', 'Le Comte de Monte-Cristo'),
(3, 4, 'Gustave Flaubert', 'Roman', 'Madame Bovary'),
(4, 1, 'Emile Zola', 'Roman', 'Germinal');

INSERT INTO ADHERENTS (NOADHERENT, NOM, ADRESSE) VALUES
(1, 'Jean Dupont', '123 Rue La Boetie, Paris'),
(2, 'Marie Curie', '456 Avenue Montaigne, Paris'),
(3, 'Henri Poincare', '789 Boulevard Saint-Germain, Paris'),
(4, 'Edith Piaf', '321 Rue du Bac, Paris');

INSERT INTO COMMANDES (NOCOMMANDE, NOLIVRE, NOADHERENT, DATECOMMANDE, STATUT) VALUES
(1, 1, 1, '2024-04-01 10:00:00', 'en attente'),
(2, 2, 2, '2024-04-02 11:00:00', 'honoree'),
(3, 3, 3, '2024-04-03 12:00:00', 'annulee'),
(4, 4, 4, '2024-04-04 13:00:00', 'en attente');

INSERT INTO EMPRUNTS (NOEMPRUNT, NOLIVRE, NOADHERENT, DATEEMPRUNT, DATERETOUR) VALUES
(1, 1, 2, '2024-04-05 14:00:00', '2024-04-19 14:00:00'),
(2, 2, 3, '2024-04-06 15:00:00', '2024-04-20 15:00:00'),
(3, 3, 4, '2024-04-07 16:00:00', '2024-04-21 16:00:00'),
(4, 4, 1, '2024-04-08 17:00:00', '2024-04-22 17:00:00');

COMMIT;