package entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class EmpruntsPK implements Serializable {
    @Column(name = "nolivre")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nolivre;
    @Column(name = "noadherent")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noadherent;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpruntsPK that = (EmpruntsPK) o;
        return nolivre == that.nolivre && noadherent == that.noadherent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nolivre, noadherent);
    }
}
