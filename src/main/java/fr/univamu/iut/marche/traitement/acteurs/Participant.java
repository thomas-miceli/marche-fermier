package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yann FORNER
 * @author Andrea GARCIA
 * @author Thomas MICELI
 */
public abstract class Participant {

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

    public void setProduits(ArrayList<ProduitFermier> produits) {
        this.produitsEnStock = produits;
    }

    public void addProduit(ProduitFermier produit) {
        this.produitsEnStock.add(produit);
    }

    public void removeProduit(ProduitFermier produit) {
        this.produitsEnStock.remove(produit);
    }

    public ArrayList<ProduitFermier> getProduitsAVendre() {
        return produitsEnVente;
    }


}
