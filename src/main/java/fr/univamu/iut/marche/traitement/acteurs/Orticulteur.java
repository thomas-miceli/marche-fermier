package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProduictionDeFruit;
import fr.univamu.iut.marche.traitement.Seeding;
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

    @Override
    public ProduitFermier fabriquerProduit(Participant.Produits objetFab, double prix, int quantite) {
        ProduictionDeFruit produictionDeFruit = new ProduictionDeFruit();
        ProduitFermier produit =  produictionDeFruit.fabriquer(objetFab.name(), prix, quantite);
        if(produit!= null){
            this.addProduit(produit);
            produitsEnStock= Seeding.compilerProduits(produitsEnStock);
            return produit;
        }
        return null;
    }
}
