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
    @Column(name = "datecommande")
    private Timestamp datecommande;
    @Basic
    @Column(name = "statut")
    private String statut;
    @ManyToOne
    @JoinColumn(name = "noadherent", referencedColumnName = "noadherent")
    private Adherents adherentsByNoadherent;

    public int getNocommande() {
        return nocommande;
    }

    public void setNocommande(int nocommande) {
        this.nocommande = nocommande;
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
        return nocommande == commandes.nocommande && Objects.equals(adherentsByNoadherent, commandes.adherentsByNoadherent) && Objects.equals(datecommande, commandes.datecommande) && Objects.equals(statut, commandes.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nocommande, adherentsByNoadherent, datecommande, statut);
    }

}
