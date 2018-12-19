package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;
import static fr.univamu.iut.marche.traitement.Seeding.compilerProduits;

/**
 * @author Téo MARTIN
 */

public class TraderEmploye extends Trader {

    public TraderEmploye(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public void vendreProduit(CentraleAchat centraleAchat, ProduitFermier produitAVendre) {
        ProduitFermier aVendre = null;
        for (ProduitFermier produ : produitsEnStock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(produitAVendre.getClass().getSimpleName())) {
                aVendre = produ;
                break;
            }
        }
            centraleAchat.getStockAVendreCentrale().remove(aVendre);
    }

    public void acheterProduit(CentraleAchat centraleAchat, Participant vendeur, ProduitFermier produitAchete) {
        ProduitFermier aAcheter = null;
        for (ProduitFermier produ : produitsEnStock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(produitAchete.getClass().getSimpleName())) {
                aAcheter = produ;
                break;
            }
        }
        centraleAchat.getStockCentrale().add(aAcheter);
        centraleAchat.setStockCentrale(compilerProduits(centraleAchat.getStockCentrale()));
        vendeur.produitsEnVente.remove(aAcheter);
    }
}
