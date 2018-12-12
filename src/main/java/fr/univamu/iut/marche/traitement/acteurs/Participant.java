package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yann FORNER
 * @author Andrea GARCIA
 * @author Thomas MICELI
 */
public abstract class Participant {

    protected enum ProduitsFabricables {}

    protected ArrayList<ProduitFermier> produitsEnStock = new ArrayList<>();

    protected ArrayList<ProduitFermier> produitsEnVente = new ArrayList<>();

    public ArrayList getProduits() {
        return produitsEnStock;
    }

    public void setProduits(ArrayList<ProduitFermier> produits) {
        this.produitsEnStock = produits;
    }

    public void addProduit(ProduitFermier produit) {
        this.produitsEnStock.add(produit);
    }

    public void removeProduit(ProduitFermier produit) {
        this.produitsEnStock.remove(produit);
    }

    public ArrayList<ProduitFermier> getProduitsVendus() {
        return produitsEnVente;
    }


}
