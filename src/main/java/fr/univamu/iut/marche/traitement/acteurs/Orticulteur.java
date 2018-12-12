package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProduictionDeFruit;

import java.util.List;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class Orticulteur extends Paysan {

    public Orticulteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public enum ProduitsFabricables {
        POMME,
        ORANGE
    }

    public void fabriquerProduit(ProduitsFabricables objetFab) {
        ProduictionDeFruit produictionDeFruit= new ProduictionDeFruit();
        try {
            this.addProduit(produictionDeFruit.fabriquer(objetFab.name(),this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
