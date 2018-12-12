package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeMiel;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class Apiculteur extends Paysan {

    public enum ProduitsFabricables {
        MIEL
    }

    public Apiculteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }


    public ProduitFermier fabriquerProduit(ProduitsFabricables objetFab)  {
        try {
             ProductionDeMiel productionDeMiel = new ProductionDeMiel();
             ProduitFermier p= productionDeMiel.fabriquer(objetFab.name(),this);
            this.addProduit(p);
             return p;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}
