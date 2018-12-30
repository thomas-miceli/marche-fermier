package fr.univamu.iut.marche.traitement.remises;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class StratProduitBio implements Strategy {
    @Override
    public double calcRemise(Participant p) {
        // TODO Temporaire, a modifier : les remises sont calculées selon le stock et non sur ce qui peut être produit.
        double remise = 0.0; //%
        for(ProduitFermier pf : p.getStock()) {
            if (pf.getClass().getSimpleName().equals("Miel") ||
                pf.getClass().getSimpleName().equals("Pomme"))
                remise += 3;
        }
        return remise;
    }
}
