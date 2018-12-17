package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/**
 * @author Yann FORNER
 * @author Andrea GARCIA
 * @author Thomas MICELI
 * @author Téo MARTIN
 */
public abstract class Participant {

    public enum Produits {
        COCHON,
        FROMAGE,
        LAIT,
        MIEL,
        ORANGE,
        POMME,
        VACHE
    }

    protected String nom;
    protected String prenom;
    protected int age;

    public Participant(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    protected enum ProduitsFabricables {}

    protected ArrayList<ProduitFermier> produitsEnStock = new ArrayList<>();

    protected ArrayList<ProduitFermier> produitsEnVente = new ArrayList<>();

    public ArrayList<ProduitFermier> getProduits() {
        return produitsEnStock;
    }

    public ArrayList<ProduitFermier> getProduitsAVendre() {
        return produitsEnVente;
    }

    public void setProduits(ArrayList<ProduitFermier> produits) {
        this.produitsEnStock = produits;
    }

    public void addProduit(ProduitFermier produit) {
        this.produitsEnStock.add(produit);
    }

    public void removeProduit(ProduitFermier produit) {
        this.produitsEnStock.remove(produit);
    }

    public void setProduitsAVendre(ArrayList<ProduitFermier> produits) {
        this.produitsEnVente = produits;
    }

    public void addProduitAVendre(ProduitFermier produit) {
        this.produitsEnVente.add(produit);
    }

    public void removeProduitAVendre(ProduitFermier produit) {
        this.produitsEnVente.remove(produit);
    }

    public ProduitFermier getProduit(Produits prod) {
        ProduitFermier produitFermier = null;
        for (ProduitFermier produ : produitsEnStock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(prod.name())) {
                produitFermier = produ;
            }
        }
        return produitFermier;
    }

    public void vendreProduit(Participant participant, Produits produitAVendre) {

        ProduitFermier aVendre = null;
        for (ProduitFermier produ : produitsEnStock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(produitAVendre.name())) {
                aVendre = produ;
                break;
            }
        }

        if (participant.getProduitsAVendre().contains(aVendre)) {
            participant.removeProduit(aVendre);
        } else {
            System.out.println(ANSI_RED + participant.getPrenom() + " " + participant.getNom() + " ne peut pas vendre " + produitAVendre);
        }

    }


}
