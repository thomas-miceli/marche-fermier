package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

public class Paysan extends Participant {

    private List produitsEnStock = new ArrayList<ProduitFermier>();

    private ArrayList<ProduitFermier> produitsVendus = new ArrayList<ProduitFermier>();

    public void setProduits(List produits) {
        this.produitsEnStock = produits;
    }

    public ArrayList<ProduitFermier> getProduitsVendus() {
        return produitsVendus;
    }

    public void setProduitsVendus(ArrayList produitsVendus) {
        this.produitsVendus = produitsVendus;
    }

    public Paysan(String nom, String prenom, int age, List produits) {
        super(nom, prenom, age);
        this.produitsEnStock = produits;
    }

    public List getProduits() {
        return produitsEnStock;
    }

    public void addProduit(ProduitFermier produit) {
        this.produitsEnStock.add(produit);
    }

    public void removeProduit(ProduitFermier produit) {
        this.produitsEnStock.remove(produit);
    }
}
