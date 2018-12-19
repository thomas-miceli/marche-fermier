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
    public void vendreProduit(Participant participant, ProduitFermier produitAVendre) {
        try{
            produitsEnStock.remove(ProduitFermier.getProduitbyId(produitAVendre.getId()));
        }catch (Exception e){
            System.out.println("un probleme est survenus");
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

