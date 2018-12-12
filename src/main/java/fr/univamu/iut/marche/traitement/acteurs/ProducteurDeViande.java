package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeViande;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.List;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProducteurDeViande extends Paysan {
    public ProducteurDeViande(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public enum ProduitsFabricables {
        COCHON,
        VACHE
    }

    public ProduitFermier fabriquerProduit(ProduitsFabricables objetFab) {
        try {
        ProductionDeViande productionDeViande = new ProductionDeViande();
            ProduitFermier p = productionDeViande.fabriquer(objetFab.name(),this);
            this.addProduit(p);
            return p;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
