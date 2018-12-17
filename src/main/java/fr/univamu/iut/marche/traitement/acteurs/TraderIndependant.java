package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/**
 * @author Dylan MARCH
 * @author Téo MARTIN
 */
public class TraderIndependant extends Trader {
    public TraderIndependant(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    @Override
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

    @Override
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
}

