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
    public void vendreProduit(Participant participant, ProduitFermier produitAVendre, int Quantite) {

        try{
            produitAVendre.setQuantite(produitAVendre.getQuantite()-Quantite);
            for (ProduitFermier produit:produitsEnStock) {
                if(produit.getId()==produitAVendre.getId()) produit = produitAVendre;
            }
        }catch (Exception e){
            System.out.println("un probleme est survenus");
        }
    }

}

