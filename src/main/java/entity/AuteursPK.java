package entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class AuteursPK implements Serializable {
    @Column(name = "nolivre")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nolivre;
    @Column(name = "auteur")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String auteur;

    public int getNolivre() {
        return nolivre;
    }

    public void setNolivre(int nolivre) {
        this.nolivre = nolivre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuteursPK auteursPK = (AuteursPK) o;
        return nolivre == auteursPK.nolivre && Objects.equals(auteur, auteursPK.auteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nolivre, auteur);
    }
}
