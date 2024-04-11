WITH r1 AS (SELECT * FROM biblio.emprunts WHERE dateemprunt >= '2023-01-01' AND statut != 'En retard'),
            r2 AS (SELECT noadherent,COUNT(nolivre) AS nblivres FROM r1 GROUP BY noadherent HAVING COUNT(nolivre) > 1)
            SELECT nom, prenom FROM r2 NATURAL JOIN biblio.adherents;