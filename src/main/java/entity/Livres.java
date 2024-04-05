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
    private Integer qtity;
    @Basic
    @Column(name = "auteur")
    private String auteur;
    @Basic
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "titre")
    private String titre;
    @OneToMany(mappedBy = "livresByNolivre")
    private Collection<Commandes> commandesByNolivre;
    @OneToMany(mappedBy = "livresByNolivre")
    private Collection<Emprunts> empruntsByNolivre;

    public int getNolivre() {
        return nolivre;
    }

    public void setNolivre(int nolivre) {
        this.nolivre = nolivre;
    }

    public Integer getQtity() {
        return qtity;
    }

    public void setQtity(Integer qtity) {
        this.qtity = qtity;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
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
        return nolivre == livres.nolivre && Objects.equals(qtity, livres.qtity) && Objects.equals(auteur, livres.auteur) && Objects.equals(genre, livres.genre) && Objects.equals(titre, livres.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nolivre, qtity, auteur, genre, titre);
    }

    public Collection<Commandes> getCommandesByNolivre() {
        return commandesByNolivre;
    }

    public void setCommandesByNolivre(Collection<Commandes> commandesByNolivre) {
        this.commandesByNolivre = commandesByNolivre;
    }

    public Collection<Emprunts> getEmpruntsByNolivre() {
        return empruntsByNolivre;
    }

    public void setEmpruntsByNolivre(Collection<Emprunts> empruntsByNolivre) {
        this.empruntsByNolivre = empruntsByNolivre;
    }
}
