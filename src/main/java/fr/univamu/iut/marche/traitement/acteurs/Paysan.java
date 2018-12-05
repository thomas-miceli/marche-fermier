package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

import static fr.univamu.iut.marche.traitement.Main.*;

public abstract class Paysan extends Participant {

    private ArrayList<ProduitFermier> produitsEnStock = new ArrayList<>();

    private ArrayList<ProduitFermier> produitsEnVente = new ArrayList<>();

    public Paysan(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public ArrayList getProduits() {
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

    public ArrayList<ProduitFermier> getProduitsVendus() {
        return produitsEnVente;
    }

    public String toString() {
        System.out.println(ANSI_GREEN + this.prenom + " " + this.nom + " - " + this.age + " ans : \n" +
                ANSI_CYAN + "Produits en stock : " + ANSI_RESET);

        for (ProduitFermier produit : produitsEnStock) {
            System.out.println(produit);
        }
        System.out.println(ANSI_CYAN + "Produits en vente :" + ANSI_RESET);
        for (ProduitFermier produit : produitsEnVente) {
            System.out.println(produit);
        }
        System.out.println("\n");
        return null;
    }
}
