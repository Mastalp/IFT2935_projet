package entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Livres {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nolivre")
    private int nolivre;
    @Basic
    @Column(name = "qtity")
    private int qtity;
    @Basic
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "titre")
    private String titre;


    public int getNolivre() {
        return nolivre;
    }

    public void setNolivre(int nolivre) {
        this.nolivre = nolivre;
    }

    public int getQtity() {
        return qtity;
    }

    public void setQtity(int qtity) {
        this.qtity = qtity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livres livres = (Livres) o;
        return nolivre == livres.nolivre && qtity == livres.qtity && Objects.equals(genre, livres.genre) && Objects.equals(titre, livres.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nolivre, qtity, genre, titre);
    }


}
