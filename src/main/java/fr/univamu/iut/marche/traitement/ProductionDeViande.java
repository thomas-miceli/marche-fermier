package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
/**
 * @author Yann FORNER
 */
public class ProductionDeViande extends UniteDeProduction {
    @Override
    public ProduitFermier fabriquer(String type, int quantite, Paysan P) throws ClassNotFoundException {
        return cree(type, quantite, P);
    }
}
