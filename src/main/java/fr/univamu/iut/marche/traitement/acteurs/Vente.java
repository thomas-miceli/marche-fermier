package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

/**
 * @author Yann FORNER
 */
public class Vente {

    private static int id = 0;
    private static ArrayList<Vente> listeVentes = new ArrayList<Vente>();
    private ProduitFermier produitVendu;
    private Participant vendeur;
    private Double prix;
    private Double prixParU;
    private Marche marche;

    public Vente(ProduitFermier produitVendu, Participant vendeur, Double prix, Marche marche) {
        this.produitVendu = produitVendu;
        this.vendeur = vendeur;
        this.prix = prix;
        this.marche = marche;
        this.prixParU = Double.valueOf((prix / produitVendu.getQuantite()));
        this.id = listeVentes.size();
        listeVentes.add(this);
        marche.addVente(this);
        marche.updateMarket();
    }


    /**
     * revoie le prixParU d'une Vente
     *
     * @return prixParU
     */
    public Double getPrixParU() {
        return prixParU;
    }

    /**
     * modifie le prixParU d'une Vente
     *
     * @param prixParU
     */
    public void setPrixParU(Double prixParU) {
        this.prixParU = prixParU;
    }

    /**
     * renvoie le produitVendu d'une Vente
     *
     * @return produitVendu
     */
    public ProduitFermier getProduitVendu() {
        return produitVendu;
    }

    /**
     * modifie le produitVendu d'une Vente
     *
     * @param produitVendu
     */
    public void setProduitVendu(ProduitFermier produitVendu) {
        this.produitVendu = produitVendu;
    }

    /**
     * revoie le vendeur d'une Offre
     *
     * @return vendeur
     */
    public Participant getVendeur() {
        return vendeur;
    }

    /**
     * modifie le vendeur d'une Offre
     *
     * @param vendeur
     */
    public void setVendeur(Participant vendeur) {
        this.vendeur = vendeur;
    }

    /**
     * renvoie le prix d'une Vente
     *
     * @return prix
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * modifie le prix d'une Vente
     *
     * @param prix
     */
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    /**
     * renvoie le marche d'une Vente
     *
     * @return marche
     */
    public Marche getMarche() {
        return marche;
    }

    /**
     * modifie le marche d'une Vente
     *
     * @param marche
     */
    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    /**
     * revoie les valeurs des variables d'une Vente
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Vente{" +
                "produitVendu=" + produitVendu.getQuantite() + " " + produitVendu.getClass().getSimpleName() +
                ", prix=" + prix +
                '}';
    }

    /**
     * revoie la listeVentes d'une Vente
     *
     * @return listeVentes
     */
    public static ArrayList<Vente> getAllVente() {
        return listeVentes;
    }

}
