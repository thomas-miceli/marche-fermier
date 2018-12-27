package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.fabriques.ProduictionDeFruit;
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

    public enum ProduitsFabricables {
        POMME,
        ORANGE
    }

    @Override
    public ProduitFermier fabriquerProduit(Produits objetFab, int quantite) {
        ProduictionDeFruit produictionDeFruit = new ProduictionDeFruit();
        ProduitFermier produit =  produictionDeFruit.fabriquer(objetFab.name(), quantite);
        if(produit!= null){
            this.addProduit(produit);
            this.setProduits( Seeding.compilerProduits(this.getProduits()));
            return produit;
        }
        return null;
    }
}
