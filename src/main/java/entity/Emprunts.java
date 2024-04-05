package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Emprunts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "noemprunt")
    private int noemprunt;
    @Basic
    @Column(name = "nolivre", insertable = false, updatable = false)
    private Integer nolivre;
    @Basic
    @Column(name = "noadherent", insertable = false, updatable = false)
    private Integer noadherent;
    @Basic
    @Column(name = "dateemprunt")
    private Timestamp dateemprunt;
    @Basic
    @Column(name = "dateretour")
    private Timestamp dateretour;
    @ManyToOne
    @JoinColumn(name = "nolivre", referencedColumnName = "nolivre")
    private Livres livresByNolivre;
    @ManyToOne
    @JoinColumn(name = "noadherent", referencedColumnName = "noadherent")
    private Adherents adherentsByNoadherent;

    public int getNoemprunt() {
        return noemprunt;
    }

    public void setNoemprunt(int noemprunt) {
        this.noemprunt = noemprunt;
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

    public Timestamp getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(Timestamp dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public Timestamp getDateretour() {
        return dateretour;
    }

    public void setDateretour(Timestamp dateretour) {
        this.dateretour = dateretour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprunts emprunts = (Emprunts) o;
        return noemprunt == emprunts.noemprunt && Objects.equals(nolivre, emprunts.nolivre) && Objects.equals(noadherent, emprunts.noadherent) && Objects.equals(dateemprunt, emprunts.dateemprunt) && Objects.equals(dateretour, emprunts.dateretour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noemprunt, nolivre, noadherent, dateemprunt, dateretour);
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
