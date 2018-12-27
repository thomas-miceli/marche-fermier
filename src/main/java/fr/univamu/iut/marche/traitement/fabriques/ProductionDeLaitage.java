package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
/**
 * @author Yann FORNER
 */
public class ProductionDeLaitage extends UniteDeProduction {

    @Override
    public ProduitFermier fabriquer(String type, int quantite) {
        if (type.equals("LAIT") || type.equals("FROMAGE")){
            return cree(type, quantite);
        }
        return null;
    }
}