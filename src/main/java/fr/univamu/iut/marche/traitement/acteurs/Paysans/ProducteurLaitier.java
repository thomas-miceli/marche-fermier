package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.fabriques.ProductionDeLaitage;
import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

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
    public ProduitFermier fabriquerProduit(Produits objetFab, int quantite) {
        ProductionDeLaitage productionDeLaitage = new ProductionDeLaitage();
        ProduitFermier produit =  productionDeLaitage.fabriquer(objetFab.name(), quantite);
        if(produit!= null){
            this.addProduit(produit);
            this.setStock( Seeding.compilerProduits(this.getStock()));
            return produit;
        }
        return null;
    }
}


