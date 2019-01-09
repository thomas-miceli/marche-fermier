package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.fabriques.ProductionDeFruit;
import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class Orticulteur extends Paysan {

    public Orticulteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    /**
     * Fabrique un objet de type ProduitFermier (ici des pommes ou des oranges)
     * et l'ajoute au stock du paysan l'ayant produit après l'avoir compilé
     * @param objetFab
     * @param quantite
     * @return ProduitFermier
     */
    @Override
    public ProduitFermier fabriquerProduit(Produits objetFab, int quantite) {
        ProductionDeFruit produictionDeFruit = new ProductionDeFruit();
        ProduitFermier produit =  produictionDeFruit.fabriquer(objetFab.name(), quantite);
        if(produit!= null){
            this.addProduit(produit);
            this.setStock( Seeding.compilerProduits(this.getStock()));
            return produit;
        }
        return null;
    }
}
