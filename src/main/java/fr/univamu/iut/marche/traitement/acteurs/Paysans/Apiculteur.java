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


    @Override
    public ProduitFermier fabriquerProduit(Participant.Produits objetFab, int quantite) {
        ProductionDeMiel productionDeLaitage = new ProductionDeMiel();
        ProduitFermier produit =  productionDeLaitage.fabriquer(objetFab.name(), quantite);
        if(produit!= null){
            this.addProduit(produit);
            produitsEnStock= Seeding.compilerProduits(produitsEnStock);
            return produit;
        }
        return null;
    }



}
