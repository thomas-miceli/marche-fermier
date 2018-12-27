package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
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

    public void show() {
        System.out.println(ANSI_YELLOW + "(" + this.getClass().getSimpleName() + ") " + ANSI_GREEN + this.prenom + " " + this.nom + " - " + this.age + " ans : \n" +
                ANSI_CYAN + "Produits en stock : " + ANSI_RESET);

        for (ProduitFermier produit : this.getProduits()) {
            System.out.println(produit.getClass().getSimpleName() + " | Quantité: " + produit.getQuantite() + " | Péremption: " + produit.getDateToString());
        }
//        System.out.println(ANSI_CYAN + "Produits en vente :" + ANSI_RESET);
//        for (ProduitFermier produit : produitsEnVente) {
//            System.out.println(produit.getClass().getSimpleName() + " | Quantité: " + produit.getQuantite() + " | Péremption: " + produit.getDateToString());
//        }
        System.out.println("\n");
    }


    public void acheterProduit(Participant vendeur, Produits produitAchete) {
//        ProduitFermier aAcheter = null;
//        for (ProduitFermier produ : produitsEnStock) {
//            if (produ.getClass().getSimpleName().toUpperCase().equals(produitAchete.name())) {
//                aAcheter = produ;
//                break;
//            }
//        }
//        this.produitsEnStock.add(aAcheter);
//        vendeur.getProduitsAVendre().remove(aAcheter);
    }
    public abstract ProduitFermier fabriquerProduit(Produits objetFab, int quantite);
}
