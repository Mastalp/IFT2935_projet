package entity;

import jakarta.persistence.*;

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
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "codepostal")
    private String codepostal;
    @Basic
    @Column(name = "norue")
    private int norue;
    @Basic
    @Column(name = "rue")
    private String rue;
    @Basic
    @Column(name = "ville")
    private String ville;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public int getNorue() {
        return norue;
    }

    public void setNorue(int norue) {
        this.norue = norue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adherents adherents = (Adherents) o;
        return noadherent == adherents.noadherent && norue == adherents.norue && Objects.equals(nom, adherents.nom) && Objects.equals(prenom, adherents.prenom) && Objects.equals(codepostal, adherents.codepostal) && Objects.equals(rue, adherents.rue) && Objects.equals(ville, adherents.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noadherent, nom, prenom, codepostal, norue, rue, ville);
    }
}
