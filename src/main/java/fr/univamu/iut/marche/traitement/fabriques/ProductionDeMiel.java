package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 */
public class ProductionDeMiel extends UniteDeProduction {
    @Override
    public ProduitFermier fabriquer(String type, int quantite) {
        if (type.equals("MIEL")){
            return cree(type, quantite);
        }
        return null;
    }
}
