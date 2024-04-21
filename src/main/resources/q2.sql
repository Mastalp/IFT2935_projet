WITH FilteredEmprunts AS (
    SELECT NOADHERENT, NOLIVRE, DATEEMPRUNT
    FROM biblio.emprunts
    WHERE DATEEMPRUNT > '2023-12-31'
),
     FilteredAdherents AS (
         SELECT NOADHERENT, NOM, PRENOM
         FROM biblio.adherents
         WHERE PRENOM LIKE 'M%'
     )
SELECT L.TITRE, A.NOM, A.PRENOM, E.DATEEMPRUNT
FROM FilteredEmprunts E
         JOIN biblio.livres L ON E.NOLIVRE = L.NOLIVRE
         JOIN FilteredAdherents A ON E.NOADHERENT = A.NOADHERENT;