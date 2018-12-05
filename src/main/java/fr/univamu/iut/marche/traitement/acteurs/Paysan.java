package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

public class Paysan extends Participant {

    private List produits = new ArrayList<ProduitFermier>();

    public Paysan(String nom, String prenom, int age, List produits) {
        super(nom, prenom, age);
        this.produits = produits;
    }

    public List getProduits() {
        return produits;
    }

    public void addProduit(ProduitFermier produit) {
        this.produits.add(produit);
    }
}
