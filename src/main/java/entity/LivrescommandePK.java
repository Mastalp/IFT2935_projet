package entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class LivrescommandePK implements Serializable {
    @Column(name = "nocommande")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nocommande;
    @Column(name = "nolivre")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nolivre;

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
        LivrescommandePK that = (LivrescommandePK) o;
        return nocommande == that.nocommande && nolivre == that.nolivre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nocommande, nolivre);
    }
}
