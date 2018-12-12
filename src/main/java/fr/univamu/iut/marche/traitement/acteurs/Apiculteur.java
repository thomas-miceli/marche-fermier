package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeMiel;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class Apiculteur extends Paysan {

    public enum ProduitsFabricables {
        MIEL
    }

    public Apiculteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }


    public void fabriquerProduit(ProduitsFabricables objetFab)  {
        ProductionDeMiel productionDeMiel = new ProductionDeMiel();

        try {
            this.addProduit(productionDeMiel.fabriquer(objetFab.name(),this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }



}
