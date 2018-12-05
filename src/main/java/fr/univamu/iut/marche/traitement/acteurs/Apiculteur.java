package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeMiel;

public class Apiculteur extends Paysan {
    public Apiculteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public void fabriquerProduit(String objetFab)  {
        ProductionDeMiel productionDeMiel = new ProductionDeMiel();
        try {
            this.addProduit(productionDeMiel.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
