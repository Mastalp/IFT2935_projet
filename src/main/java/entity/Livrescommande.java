package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(LivrescommandePK.class)
public class Livrescommande {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nocommande")
    private int nocommande;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nolivre")
    private int nolivre;
    @ManyToOne
    @JoinColumn(name = "nocommande", referencedColumnName = "nocommande", nullable = false)
    private Commandes commandesByNocommande;
    @ManyToOne
    @JoinColumn(name = "nolivre", referencedColumnName = "nolivre", nullable = false)
    private Livres livresByNolivre;

    public int getNocommande() {
        return nocommande;
    }

    public void setNocommande(int nocommande) {
        this.nocommande = nocommande;
    }

    public int getNolivre() {
        return nolivre;
    }

    public void setNolivre(int nolivre) {
        this.nolivre = nolivre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livrescommande that = (Livrescommande) o;
        return nocommande == that.nocommande && nolivre == that.nolivre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nocommande, nolivre);
    }

    public Commandes getCommandesByNocommande() {
        return commandesByNocommande;
    }

    public void setCommandesByNocommande(Commandes commandesByNocommande) {
        this.commandesByNocommande = commandesByNocommande;
    }

    public Livres getLivresByNolivre() {
        return livresByNolivre;
    }

    public void setLivresByNolivre(Livres livresByNolivre) {
        this.livresByNolivre = livresByNolivre;
    }
}
