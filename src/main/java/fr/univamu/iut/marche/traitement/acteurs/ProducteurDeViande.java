package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeViande;

import java.util.List;

public class ProducteurDeViande extends Paysan {
    public ProducteurDeViande(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public void fabriquerProduit(String objetFab) {
        ProductionDeViande productionDeViande = new ProductionDeViande();
        try {
            this.addProduit(productionDeViande.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
