package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 */
public class ProductionDeLaitage extends UniteDeProduction {
    /**
     * Fabrique dans la production des produits de type Laitage
     *
     * @param type
     * @param quantite
     */
    @Override
    public ProduitFermier fabriquer(String type, int quantite) {
        if (type.equals("LAIT") || type.equals("FROMAGE")) {
            return cree(type, quantite);
        }
        return null;
    }
}
