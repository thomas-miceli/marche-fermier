package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Dylan MARCH
 */
public class TraderIndependant extends Trader {
    public TraderIndependant(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    @Override
    public void Acheter(ProduitFermier produitFermier) {

    }

    @Override
    public void Vendre(ProduitFermier produitFermier) {

    }

    public void intervient() {

    }
}
