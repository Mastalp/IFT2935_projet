package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Commandes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nocommande")
    private int nocommande;
    @Basic
    @Column(name = "nolivre", insertable = false, updatable = false)
    private Integer nolivre;
    @Basic
    @Column(name = "noadherent", insertable = false, updatable = false)
    private Integer noadherent;
    @Basic
    @Column(name = "datecommande")
    private Timestamp datecommande;
    @Basic
    @Column(name = "statut")
    private String statut;
    @ManyToOne
    @JoinColumn(name = "nolivre", referencedColumnName = "nolivre")
    private Livres livresByNolivre;
    @ManyToOne
    @JoinColumn(name = "noadherent", referencedColumnName = "noadherent")
    private Adherents adherentsByNoadherent;

    public int getNocommande() {
        return nocommande;
    }

    public void setNocommande(int nocommande) {
        this.nocommande = nocommande;
    }

    public Integer getNolivre() {
        return nolivre;
    }

    public void setNolivre(Integer nolivre) {
        this.nolivre = nolivre;
    }

    public Integer getNoadherent() {
        return noadherent;
    }

    public void setNoadherent(Integer noadherent) {
        this.noadherent = noadherent;
    }

    public Timestamp getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Timestamp datecommande) {
        this.datecommande = datecommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commandes commandes = (Commandes) o;
        return nocommande == commandes.nocommande && Objects.equals(nolivre, commandes.nolivre) && Objects.equals(noadherent, commandes.noadherent) && Objects.equals(datecommande, commandes.datecommande) && Objects.equals(statut, commandes.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nocommande, nolivre, noadherent, datecommande, statut);
    }

    public Livres getLivresByNolivre() {
        return livresByNolivre;
    }

    public void setLivresByNolivre(Livres livresByNolivre) {
        this.livresByNolivre = livresByNolivre;
    }

    public Adherents getAdherentsByNoadherent() {
        return adherentsByNoadherent;
    }

    public void setAdherentsByNoadherent(Adherents adherentsByNoadherent) {
        this.adherentsByNoadherent = adherentsByNoadherent;
    }
}
