package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(EmpruntsPK.class)
public class Emprunts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nolivre")
    private int nolivre;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "noadherent")
    private int noadherent;
    @Basic
    @Column(name = "dateemprunt")
    private Date dateemprunt;
    @Basic
    @Column(name = "dateretour")
    private Date dateretour;
    @Basic
    @Column(name = "statut")
    private String statut;
    @ManyToOne
    @JoinColumn(name = "noadherent", referencedColumnName = "noadherent", nullable = false)
    private Adherents adherentsByNoadherent;

    public int getNolivre() {
        return nolivre;
    }

    public void setNolivre(int nolivre) {
        this.nolivre = nolivre;
    }

    public int getNoadherent() {
        return noadherent;
    }

    public void setNoadherent(int noadherent) {
        this.noadherent = noadherent;
    }

    public Date getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(Date dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public Date getDateretour() {
        return dateretour;
    }

    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
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
        Emprunts emprunts = (Emprunts) o;
        return nolivre == emprunts.nolivre && noadherent == emprunts.noadherent && Objects.equals(dateemprunt, emprunts.dateemprunt) && Objects.equals(dateretour, emprunts.dateretour) && Objects.equals(statut, emprunts.statut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nolivre, noadherent, dateemprunt, dateretour, statut);
    }

    public Adherents getAdherentsByNoadherent() {
        return adherentsByNoadherent;
    }

    public void setAdherentsByNoadherent(Adherents adherentsByNoadherent) {
        this.adherentsByNoadherent = adherentsByNoadherent;
    }
}
