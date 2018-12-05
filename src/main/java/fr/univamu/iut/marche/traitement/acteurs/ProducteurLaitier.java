package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeLaitage;

import java.util.ArrayList;
import java.util.List;

public class ProducteurLaitier extends Paysan {
    public ProducteurLaitier(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public void fabriquerProduit(String objetFab) {
        ProductionDeLaitage productionDeLaitage = new ProductionDeLaitage();
        try {
            this.addProduit(productionDeLaitage.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
