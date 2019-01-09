package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 */
public class ProductionDeViande extends UniteDeProduction {
    /**
     * Fabrique dans la production des produits de type Viande
     *
     * @param type
     * @param quantite
     */
    @Override
    public ProduitFermier fabriquer(String type, int quantite) {
        if (type.equals("COCHON") || type.equals("VACHE")) {
            return cree(type, quantite);
        }
        return null;
    }
}
