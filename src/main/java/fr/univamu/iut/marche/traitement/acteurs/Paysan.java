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

    public void show() {
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

    public void acheterProduit(Participant acheteur, Participant vendeur, Produits produitAchete) {
        ProduitFermier aAcheter = null;
        for (ProduitFermier produ : produitsEnStock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(produitAchete.name())) {
                aAcheter = produ;
                break;
            }
        }
        acheteur.produitsEnStock.add(aAcheter);
        vendeur.produitsEnVente.remove(aAcheter);
    }
    public abstract ProduitFermier fabriquerProduit(Produits objetFab, int quantite);
}
