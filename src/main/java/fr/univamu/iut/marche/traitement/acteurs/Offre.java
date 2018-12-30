package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class Offre {
    private Participant.Produits produitOffre;
    private Integer prixOffre;
    private Participant acheteur;
    private Marche marche;
    private Integer quantite;
    private Double prixParU;

    public Offre(Participant.Produits produitOffre, Participant acheteur, Integer prixOffre,Integer quantite, Marche marche) {
        this.produitOffre = produitOffre;
        this.prixOffre = prixOffre;
        this.acheteur = acheteur;
        this.marche = marche;
        this.quantite=quantite;
        this.prixParU= Double.valueOf((prixOffre/quantite));
        marche.addOffre(this);
        marche.updateMarket();
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
    public Integer getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(Integer prixOffre) {
        this.prixOffre = prixOffre;
    }

    public Participant getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Participant acheteur) {
        this.acheteur = acheteur;
    }

    public Double getPrixParU() {
        return prixParU;
    }

    public void setPrixParU(Double prixParU) {
        this.prixParU = prixParU;
    }
    public void refreshPrixParU(){
        prixParU= (double) (prixOffre/quantite);
    }
}
