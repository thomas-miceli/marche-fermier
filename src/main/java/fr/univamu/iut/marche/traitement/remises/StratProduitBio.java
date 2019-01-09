package fr.univamu.iut.marche.traitement.remises;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Vente;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Thomas MICELI
 */
public class StratProduitBio implements Strategy {
    @Override
    public double calcRemise(Participant p) {
        double pourcentage = 0;
        for (Vente vente : p.getVentesNonRemisees()) {
            if (vente.getProduitVendu().getType().equals("Miel") || vente.getProduitVendu().getType().equals("Pomme")) {
                pourcentage += 3;
            }
        }
        return pourcentage;
    }
}
