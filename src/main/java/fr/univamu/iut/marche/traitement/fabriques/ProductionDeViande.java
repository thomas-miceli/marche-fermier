package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/**
 * @author Yann FORNER
 * @author Thomas MICELI
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
        System.out.println(this + ANSI_RED + " ne peut pas produire : " + type);
        return null;
    }
}
