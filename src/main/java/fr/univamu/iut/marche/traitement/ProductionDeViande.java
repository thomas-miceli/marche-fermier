package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
/**
 * @author Yann FORNER
 */
public class ProductionDeViande extends UniteDeProduction {
    @Override
    public ProduitFermier fabriquer(String type, int quantite){
        if(type.equals("COCHON")|| type.equals("VACHE")){
            return cree(type,quantite);
        }
        return null;
    }
}
