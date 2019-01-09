package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProductionDeFruit extends UniteDeProduction {
    /**
     * Fabrique dans la production des produits de type Fruit
     *
     * @param type
     * @param quantite
     */
    @Override
    public ProduitFermier fabriquer(String type, int quantite) {
        if (type.equals("ORANGE") || type.equals("POMME")) {
            return cree(type, quantite);
        }
        return null;
    }
}
