package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class Offre {
    private Participant.Produits produitOffre;
    private double prixOffre;
    private Participant acheteur;
    private Marche marche;
    private Integer quantite;

    public Offre(Participant.Produits produitOffre, Participant acheteur, double prixOffre,Integer quantite, Marche marche) {
        this.produitOffre = produitOffre;
        this.prixOffre = prixOffre;
        this.acheteur = acheteur;
        this.marche = marche;
        this.quantite=quantite;
        marche.addOffre(this);
    }

    public Participant.Produits getProduitOffre() {
        return produitOffre;
    }

    public void setProduitOffre(Participant.Produits produitOffre) {
        this.produitOffre = produitOffre;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public double getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(double prixOffre) {
        this.prixOffre = prixOffre;
    }

    public Participant getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Participant acheteur) {
        this.acheteur = acheteur;
    }
}
