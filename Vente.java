package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class Vente {

    private ProduitFermier produitVendu;
    private Participant vendeur;
    private int prix;

    public Vente(ProduitFermier produitVendu, Participant vendeur, int prix, Marche marche) {
        this.produitVendu = produitVendu;
        this.vendeur = vendeur;
        this.prix = prix;
        this.marche = marche;
        marche.addVente(this);
    }

    private Marche marche;


    public ProduitFermier getProduitVendu() {
        return produitVendu;
    }

    public void setProduitVendu(ProduitFermier produitVendu) {
        this.produitVendu = produitVendu;
    }

    public Participant getVendeur() {
        return vendeur;
    }

    public void setVendeur(Participant vendeur) {
        this.vendeur = vendeur;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }
}
