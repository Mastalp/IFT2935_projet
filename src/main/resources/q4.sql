SELECT
    a.NOM, a.PRENOM, l.TITRE
FROM biblio.adherents a
         JOIN biblio.commandes c ON a.NOADHERENT = c.NOADHERENT
         JOIN biblio.livrescommande lc ON c.NOCOMMANDE = lc.NOCOMMANDE
         JOIN biblio.livres l ON lc.NOLIVRE = l.NOLIVRE
WHERE c.STATUT = 'Honoree'
ORDER BY a.NOADHERENT, c.DATECOMMANDE;
