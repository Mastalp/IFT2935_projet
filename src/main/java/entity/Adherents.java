package entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Adherents {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "noadherent")
    private int noadherent;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "adresse")
    private String adresse;
    @OneToMany(mappedBy = "adherentsByNoadherent")
    private Collection<Commandes> commandesByNoadherent;
    @OneToMany(mappedBy = "adherentsByNoadherent")
    private Collection<Emprunts> empruntsByNoadherent;

    public int getNoadherent() {
        return noadherent;
    }

    public void setNoadherent(int noadherent) {
        this.noadherent = noadherent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adherents adherents = (Adherents) o;
        return noadherent == adherents.noadherent && Objects.equals(nom, adherents.nom) && Objects.equals(adresse, adherents.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noadherent, nom, adresse);
    }

    public Collection<Commandes> getCommandesByNoadherent() {
        return commandesByNoadherent;
    }

    public void setCommandesByNoadherent(Collection<Commandes> commandesByNoadherent) {
        this.commandesByNoadherent = commandesByNoadherent;
    }

    public Collection<Emprunts> getEmpruntsByNoadherent() {
        return empruntsByNoadherent;
    }

    public void setEmpruntsByNoadherent(Collection<Emprunts> empruntsByNoadherent) {
        this.empruntsByNoadherent = empruntsByNoadherent;
    }
}
