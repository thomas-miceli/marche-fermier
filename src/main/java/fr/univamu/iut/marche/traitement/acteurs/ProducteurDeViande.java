package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeViande;

import java.util.List;

public class ProducteurDeViande extends Paysan {
    public ProducteurDeViande(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public enum ProduitsFabricables {
        COCHON,
        VACHE
    }

    public void fabriquerProduit(ProduitsFabricables objetFab) {
        ProductionDeViande productionDeViande = new ProductionDeViande();
        try {
            this.addProduit(productionDeViande.fabriquer(objetFab.name(),this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
