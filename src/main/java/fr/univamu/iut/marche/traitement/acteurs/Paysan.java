package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

import static fr.univamu.iut.marche.traitement.Main.*;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public abstract class Paysan extends Participant {

    public Paysan(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public String toString() {
        System.out.println(ANSI_YELLOW + "(" + this.getClass().getSimpleName() + ") " + ANSI_GREEN + this.prenom + " " + this.nom + " - " + this.age + " ans : \n" +
                ANSI_CYAN + "Produits en stock : " + ANSI_RESET);

        for (ProduitFermier produit : produitsEnStock) {
            System.out.println(produit.getClass().getSimpleName() + " | Quantité: " + produit.getQuantite() + " | Péremption: " + produit.getDateToString());
        }
        System.out.println(ANSI_CYAN + "Produits en vente :" + ANSI_RESET);
        for (ProduitFermier produit : produitsEnVente) {
            System.out.println(produit.getClass().getSimpleName() + " | Quantité: " + produit.getQuantite() + " | Péremption: " + produit.getDateToString());
        }
        System.out.println("\n");
        return null;
    }
}
