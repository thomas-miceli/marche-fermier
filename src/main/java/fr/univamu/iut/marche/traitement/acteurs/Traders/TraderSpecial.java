package fr.univamu.iut.marche.traitement.acteurs.Traders;

import fr.univamu.iut.marche.traitement.acteurs.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static fr.univamu.iut.marche.traitement.Seeding.compilerProduits;

/**
 * @author Dylan MARCH
 * @author Téo MARTIN
 */
public class TraderSpecial extends Trader {
    public TraderSpecial(String nom, String prenom, int age) throws ParseException {
        super(nom, prenom, age);
    }
    private ArrayList<ProduitFermier> stockTrader;
    private String dateToConvert="18:30:00";
    private Date finDeService = new SimpleDateFormat("").parse(dateToConvert);
    private Date dateActuelle = new Date();

    public void vendreProduit(CentraleAchat centraleAchat, ProduitFermier produitAVendre) {
        if (finDeService.after(dateActuelle)){
            ProduitFermier aVendre = null;
            for (ProduitFermier produ : produitsEnStock) {
                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAVendre.getClass().getSimpleName())) {
                    aVendre = produ;
                    break;
                }
            }
            this.getProduitsAVendre().remove(aVendre);
        }
        else {
            ProduitFermier aVendre = null;
            for (ProduitFermier produ : produitsEnStock) {
                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAVendre.getClass().getSimpleName())) {
                    aVendre = produ;
                    break;
                }
            }
            centraleAchat.getStockAVendreCentrale().remove(aVendre);
        }
    }

    public void acheterProduit(CentraleAchat centraleAchat, Participant vendeur, Produits produitAchete) {
        if (finDeService.after(dateActuelle)){
            ProduitFermier aAcheter = null;
            for (ProduitFermier produ : produitsEnStock) {
                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAchete.getClass().getSimpleName())) {
                    aAcheter = produ;
                    break;
                }
            }
            this.getProduits().add(aAcheter);
            this.setProduits(compilerProduits(centraleAchat.getStockCentrale()));
            vendeur.getProduitsAVendre().remove(aAcheter);
        }
        else {
            ProduitFermier aAcheter = null;
            for (ProduitFermier produ : produitsEnStock) {
                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAchete.getClass().getSimpleName())) {
                    aAcheter = produ;
                    break;
                }
            }
            centraleAchat.getStockCentrale().add(aAcheter);
            centraleAchat.setStockCentrale(compilerProduits(centraleAchat.getStockCentrale()));
            vendeur.getProduitsAVendre().remove(aAcheter);
        }
    }
}
