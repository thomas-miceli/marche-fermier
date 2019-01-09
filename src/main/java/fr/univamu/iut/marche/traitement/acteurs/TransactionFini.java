package fr.univamu.iut.marche.traitement.acteurs;

import java.util.ArrayList;

public class TransactionFini {
    private Participant vendeur;
    private Participant acheteur;

    private Double prix;
    private Integer quantite;

    private Participant.Produits produitVendu;
    public TransactionFini(Offre o , Vente v, Integer quantite) {

        this.vendeur = v.getVendeur();
        this.acheteur = o.getAcheteur();
        this.quantite = quantite;
        this.prix = v.getPrixParU();
        this.produitVendu = o.getProduitOffre();
        o.getMarche().addTransactionFinie(this);

    }

    public Participant getVendeur() {
        return vendeur;
    }

    public void setVendeur(Participant vendeur) {
        this.vendeur = vendeur;
    }

    public Participant getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Participant acheteur) {
        this.acheteur = acheteur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Participant.Produits getProduitVendu() {
        return produitVendu;
    }

    public void setProduitVendu(Participant.Produits produitVendu) {
        this.produitVendu = produitVendu;
    }

    @Override
    public String toString() {
        return "Vendeur : " + vendeur.getPrenom() + ' ' + vendeur.getNom() + " vends " + quantite + ' '  + produitVendu + " à " + acheteur.getPrenom() +' '+acheteur.getNom() + " pour " + prix +'€';
        /*return "TransactionFini{" +
                "vendeur=" + vendeur +
                ", acheteur=" + acheteur +
                ", prix/u=" + prix +
                ", quantite=" + quantite +
                ", produitVendu=" + produitVendu +
                '}';*/
    }
}
