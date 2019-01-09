package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.fabriques.ProductionDeViande;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class ProducteurDeViande extends Paysan {
    public ProducteurDeViande(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    /**
     * Fabrique un objet de type ProduitFermier (ici du cochon ou de la vache)
     * et l'ajoute au stock du paysan l'ayant produit
     *
     * @param objetFab
     * @param quantite
     * @return ProduitFermier
     */
    @Override
    public ProduitFermier fabriquerProduit(Produits objetFab, int quantite) {
        ProductionDeViande productionDeViande = new ProductionDeViande();
        ProduitFermier produit = productionDeViande.fabriquer(objetFab.name(), quantite);
        if (produit != null) {
            this.addProduit(produit);
            this.setStock(Seeding.compilerProduits(this.getStock()));
            return produit;
        }
        return null;
    }
}
