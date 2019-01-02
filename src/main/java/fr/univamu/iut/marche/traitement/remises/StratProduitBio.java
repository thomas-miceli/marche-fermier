package fr.univamu.iut.marche.traitement.remises;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class StratProduitBio implements Strategy {
    @Override
    public double calcRemise(Participant p) {
        return 3;
    }
}
