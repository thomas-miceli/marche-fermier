package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProduictionDeFruit;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

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

    public ProduitFermier fabriquerProduit(ProduitsFabricables objetFab, int quantite) {
        try {
        ProduictionDeFruit produictionDeFruit= new ProduictionDeFruit();
            ProduitFermier p =produictionDeFruit.fabriquer(objetFab.name(), quantite, this);
            this.addProduit(p);
            return p;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
