SELECT a.NOADHERENT, a.NOM, a.PRENOM, COUNT(EMPRUNTS.NOLIVRE) AS TOTAL_LIVRES_EMPRUNTES
                FROM biblio.adherents a
                         JOIN biblio.emprunts ON a.NOADHERENT = EMPRUNTS.NOADHERENT
                GROUP BY a.NOADHERENT
                HAVING COUNT(EMPRUNTS.NOLIVRE) > 0;