BEGIN TRANSACTION;
DROP SCHEMA IF EXISTS BIBLIO CASCADE;
CREATE SCHEMA BIBLIO;
SET SEARCH_PATH TO BIBLIO;


-- Contraintes 
-- Commander 3 livres max par commande
-- Durée emprunt 14 jours max sinon retard
-- Livre ne peut etre rendu le jour même de l'emprunt
-- Si livre commandée n'est pas disponible, tous emprunté = commande annulée sinon commande honorée

CREATE TABLE LIVRES
(
    NOLIVRE INT PRIMARY KEY,
    QTITY INT NOT NULL, 		-- Quantite encore disponible	
    GENRE VARCHAR(16) NOT NULL,
    TITRE VARCHAR(32) NOT NULL
);

CREATE TABLE AUTEURS
(
	NOLIVRE INT,
	AUTEUR VARCHAR(32),
	FOREIGN KEY (NOLIVRE) REFERENCES LIVRES(NOLIVRE),
	PRIMARY KEY (NOLIVRE, AUTEUR)
);

CREATE TABLE ADHERENTS
(
    NOADHERENT INT PRIMARY KEY,
    NOM VARCHAR(32) NOT NULL,
	PRENOM VARCHAR(32) NOT NULL,
    CODEPOSTAL VARCHAR(10) NOT NULL,
	NORUE INT NOT NULL,
	RUE VARCHAR(32) NOT NULL,
	VILLE VARCHAR(16) NOT NULL
);

CREATE TABLE COMMANDES
(
    NOCOMMANDE INT PRIMARY KEY,
    NOADHERENT INT,
    DATECOMMANDE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    STATUT VARCHAR(10) DEFAULT 'En attente',
    FOREIGN KEY (NOADHERENT) REFERENCES ADHERENTS(NOADHERENT)
);

CREATE TABLE LIVRESCOMMANDE
(
	NOCOMMANDE INT,
	NOLIVRE INT,
	FOREIGN KEY (NOCOMMANDE) REFERENCES COMMANDES(NOCOMMANDE),
	FOREIGN KEY (NOLIVRE) REFERENCES LIVRES(NOLIVRE),
	PRIMARY KEY (NOCOMMANDE, NOLIVRE)
);

CREATE TABLE EMPRUNTS
(
    NOLIVRE INT,
    NOADHERENT INT,
    DATEEMPRUNT DATE DEFAULT CURRENT_DATE,
    DATERETOUR DATE,
	STATUT VARCHAR(16) DEFAULT 'En cours',
    FOREIGN KEY (NOLIVRE) REFERENCES LIVRES(NOLIVRE),
    FOREIGN KEY (NOADHERENT) REFERENCES ADHERENTS(NOADHERENT),
	PRIMARY KEY (NOADHERENT, NOLIVRE)
);

	INSERT INTO LIVRES (NOLIVRE, QTITY, GENRE, TITRE) VALUES
	(1, 2, 'Science-fiction', '1984'),
	(10, 1, 'Roman', 'L''alchimiste'),
	(11, 3, 'Livre jeunesse', 'Le petit prince'),
	(100, 1, 'Aventure', 'Moby Dick'),
	(101, 0, 'Roman', 'L''etranger'),
	(110, 0, 'Aventure', 'Le comte de Monte-Cristo'),
	(111, 2, 'Roman', 'La chute'),
	(1000, 1, 'Roman', 'Les Miserables'),
	(1001, 4, 'Sciences', 'Operating system concepts'),
	(1010, 3, 'Sciences', 'Fundamentals of algorithmics');
	
	INSERT INTO AUTEURS (NOLIVRE, AUTEUR) VALUES
	(1, 'George Orwell'),
	(10, 'Paulo Coelho'),
	(11, 'Antoine de Saint-Exupery'),
	(100, 'Herman Melville'),
	(101, 'Albert Camus'),
	(110, 'Alexandre Dumas'),
	(111, 'Albert Camus'),
	(1000, 'Victor Hugo'),
	(1001, 'Abraham Silberschatz'),
	(1001, 'Greg Gagne'),
	(1001, 'Peter Baer Galvin'),
	(1010, 'Gilles Brassard'),
	(1010, 'Paul Bratley');
	
	INSERT INTO ADHERENTS (NOADHERENT, NOM, PRENOM, CODEPOSTAL, NORUE, RUE, VILLE) VALUES
	(90001, 'Dupont', 'Jean', '111324', 123, 'Sauve', 'Montreal'),
	(90010, 'Curie', 'Marie', '132456', 456, 'Curotte', 'Montreal'),
	(90011, 'Poincare', 'Henri', '425831', 789, 'Prieur', 'Montreal'),
	(90100, 'Piaf', 'Edith', '532111', 10321, 'Perdu', 'Laval'),
	(90101, 'Anderson', 'Marc', '132391', 1, 'Milieu', 'Laval'),
	(90110, 'Gaudet', 'Seb', '825111', 32, '3e rang', 'Brossard'),
	(90111, 'Kirouak', 'Frank', '142311', 1000, '8e rang', 'Brossard'),
	(91000, 'Dit', 'Jean', '381246', 8, 'Montagne', 'Saint-Sauveur'),
	(91001, 'Lalonde', 'Samuel', '452111', 125, 'Montagne', 'Saint-Sauveur'),
	(91010, 'Dob', 'Bob', '783121', 42, 'Fleury', 'Montreal');
	
	INSERT INTO COMMANDES (NOCOMMANDE, NOADHERENT, DATECOMMANDE, STATUT) VALUES
	(50001, 90001, DEFAULT, DEFAULT),
	(50010, 90001, '2024-04-01 12:00:00', 'Honoree'),
	(50011, 90011, '2024-02-10 13:00:00', 'Anulee'),
	(50100, 90101, '2024-01-01 9:00:00', 'Honoree'),
	(50101, 91001, DEFAULT, DEFAULT),
	(50110, 91010, '2023-09-10 14:00:00', 'Anulee'),
	(50111, 90010, '2023-09-09 11:30:15', 'Honoree'),
	(51000, 90010, '2024-03-20 14:30:48', 'Honoree'),
	(51001, 90100, '2023-12-01 9:33:53', 'Honoree'),
	(51010, 90111, DEFAULT, DEFAULT);
	
	INSERT INTO LIVRESCOMMANDE (NOCOMMANDE, NOLIVRE) VALUES
	(50001, 1),
	(50001, 11),
	(50001, 1001),
	(50010, 110),
	(50011, 1000),
	(50011, 100),
	(50100, 1010),
	(50101, 10),
	(50101, 101),
	(50110, 111),
	(50111, 111),
	(51000, 1000),
	(51001, 100),
	(51010, 1010);
	
	INSERT INTO EMPRUNTS (NOLIVRE, NOADHERENT, DATEEMPRUNT, DATERETOUR, STATUT) VALUES
	(110, 90001, '2024-04-03', NULL, DEFAULT),
	(1010, 90101, '2024-01-01', '2024-01-10', 'Remis'),
	(111, 90010, '2023-09-09', '2023-09-11', 'Remis'),
	(1000, 90010, '2024-03-20', NULL, 'En retard'),
	(100, 90100, '2023-12-01', '2024-01-10', 'Remis'),
	(101, 91001, '2024-04-01', NULL, DEFAULT),
	(1001, 90011, '2023-03-10', '2023-04-01', 'Remis'),
	(1000, 90111, '2023-12-31', NULL, 'En retard'),
	(10, 90101, DEFAULT, NULL, DEFAULT),
	(11, 90101, DEFAULT, NULL, DEFAULT);

COMMIT;