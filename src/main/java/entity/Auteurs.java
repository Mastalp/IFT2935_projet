package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(AuteursPK.class)
public class Auteurs {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nolivre")
    private int nolivre;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "auteur")
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
        Auteurs auteurs = (Auteurs) o;
        return nolivre == auteurs.nolivre && Objects.equals(auteur, auteurs.auteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nolivre, auteur);
    }
}
