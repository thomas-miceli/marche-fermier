package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.fabriques.ProductionDeViande;
import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProducteurDeViande extends Paysan {
    public ProducteurDeViande(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public enum ProduitsFabricables {
        COCHON,
        VACHE
    }

    @Override
    public ProduitFermier fabriquerProduit(Produits objetFab, int quantite) {
        ProductionDeViande productionDeLaitage = new ProductionDeViande();
        ProduitFermier produit =  productionDeLaitage.fabriquer(objetFab.name(), quantite);
        if(produit!= null){
            this.addProduit(produit);
            this.setStock( Seeding.compilerProduits(this.getStock()));
            return produit;
        }
        return null;
    }
}
