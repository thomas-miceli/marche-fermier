package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;
import static fr.univamu.iut.marche.traitement.Seeding.compilerProduits;

/**
 * @author Dylan MARCH
 * @author Téo MARTIN
 */
public class TraderIndependant extends Trader {

    private ArrayList<ProduitFermier> stockPersonnel = new ArrayList<>();
    boolean connexion = true;


    public ArrayList<ProduitFermier> getStockPersonnel() {
        return stockPersonnel;
    }

    public void setStockPersonnel(ArrayList<ProduitFermier> stockPersonnel) {
        this.stockPersonnel = stockPersonnel;
    }

    public TraderIndependant(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public boolean hasConnexion() {
        return connexion;
    }

    public void setConnexion(boolean connexion) {
        this.connexion = connexion;
    }

    public void vendreProduit(CentraleAchat centraleAchat, ProduitFermier produitAVendre) {
        ProduitFermier aVendre = null;
        if (connexion) {
            for (ProduitFermier produ : produitsEnStock) {
                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAVendre.getClass().getSimpleName())) {
                    aVendre = produ;
                    break;
                }
            }
            centraleAchat.getStockAVendreCentrale().remove(aVendre);
        }
        System.out.println("Pas connecté, impossible de vendre le produit");
    }

    public void acheterProduit(TraderIndependant independant, CentraleAchat centraleAchat, Participant vendeur, ProduitFermier produitAchete) {
        ProduitFermier aAcheter = null;
        if (connexion) {
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
        System.out.println("Pas connecté, impossible d'acheter le produit");
    }
}

