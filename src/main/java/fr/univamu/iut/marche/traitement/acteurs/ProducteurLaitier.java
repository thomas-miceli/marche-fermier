package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeLaitage;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProducteurLaitier extends Paysan {
    public ProducteurLaitier(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public enum ProduitsFabricables {
        LAIT,
        FROMAGE
    }

    public ProduitFermier fabriquerProduit(ProduitsFabricables objetFab) {
        try {
        ProductionDeLaitage productionDeLaitage = new ProductionDeLaitage();
        ProduitFermier p = productionDeLaitage.fabriquer(objetFab.name(),this);

            this.addProduit(p);
            return p;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
