package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

public abstract class Paysan extends Participant {



    private List produits = new ArrayList<ProduitFermier>();

    public Paysan(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public List getProduits() {
        return produits;
    }
    public void setProduits(List produits) {
        this.produits = produits;
    }
    public void addProduit(ProduitFermier produit) {
        this.produits.add(produit);
    }
    public void removeProduit(ProduitFermier produit) {
        this.produits.remove(produit);
    }
    public abstract void fabriquerProduit(String objetFab) throws ClassNotFoundException;

}
