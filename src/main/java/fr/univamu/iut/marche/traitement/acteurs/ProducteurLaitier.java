package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeLaitage;

import java.util.List;

public class ProducteurLaitier extends Paysan {
    public ProducteurLaitier(String nom, String prenom, int age, List produits) {
        super(nom, prenom, age);
    }

    @Override
    public void fabriquerProduit(String objetFab) {
        ProductionDeLaitage productionDeLaitage = new ProductionDeLaitage();
        try {
            this.addProduit(productionDeLaitage.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
