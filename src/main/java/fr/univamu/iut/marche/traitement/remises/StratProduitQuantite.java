package fr.univamu.iut.marche.traitement.remises;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Vente;

/**
 * @author Thomas MICELI
 */
public class StratProduitQuantite implements Strategy {


    @Override
    public double calcRemise(Participant p) {
        double pourcentage = 0;
        for (Vente vente : p.getVentesNonRemisees()) {
            if (vente.getProduitVendu().getQuantite() > 199) {
                pourcentage += 5;
            }
        }
        return pourcentage;
    }
}
