package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProductionDeLaitage;
import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProducteurLaitier extends Paysan {
    public ProducteurLaitier(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }



    public enum ProduitsFabricables {
        LAIT,
        FROMAGE
    }

    @Override
    public ProduitFermier fabriquerProduit(Produits objetFab, double prix, int quantite) {
        ProductionDeLaitage productionDeLaitage = new ProductionDeLaitage();
        ProduitFermier produit =  productionDeLaitage.fabriquer(objetFab.name(), prix, quantite);
        if(produit!= null){
            this.addProduit(produit);
            produitsEnStock= Seeding.compilerProduits(produitsEnStock);
            return produit;
        }
        return null;
    }
}


