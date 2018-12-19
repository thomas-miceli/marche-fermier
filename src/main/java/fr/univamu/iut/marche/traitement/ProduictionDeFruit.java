package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProduictionDeFruit extends UniteDeProduction {
    @Override
    public ProduitFermier fabriquer(String type, double prix, int quantite) {
        if (type.equals("ORANGE") || type.equals("POMME")){
            return cree(type, quantite, prix);
        }
        return null;
    }
}
