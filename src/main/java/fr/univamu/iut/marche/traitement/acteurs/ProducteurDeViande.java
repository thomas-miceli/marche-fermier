package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeViande;

import java.util.List;

public class ProducteurDeViande extends Paysan {
    public ProducteurDeViande(String nom, String prenom, int age, List produits) {
        super(nom, prenom, age);
    }

    @Override
    public void fabriquerProduit(String objetFab) {
        ProductionDeViande productionDeViande = new ProductionDeViande();
        try {
            this.addProduit(productionDeViande.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
