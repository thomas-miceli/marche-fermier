package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.fabriques.ProductionDeMiel;
import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class Apiculteur extends Paysan {

    public enum ProduitsFabricables {
        MIEL
    }

    public Apiculteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }


    /**
     * Fabrique un objet de type ProduitFermier (ici du miel) et l'ajoute au stock après l'avoir compilé avec les
     * objets identiques.
     * @param objetFab
     * @param quantite
     * @return ProduitFermier
     */
    @Override
    public ProduitFermier fabriquerProduit(Participant.Produits objetFab, int quantite) {
        ProductionDeMiel productionDeLaitage = new ProductionDeMiel();
        ProduitFermier produit =  productionDeLaitage.fabriquer(objetFab.name(), quantite);
        if(produit!= null){
            this.addProduit(produit);
            this.setStock( Seeding.compilerProduits(this.getStock()));
            return produit;
        }
        return null;
    }



}
