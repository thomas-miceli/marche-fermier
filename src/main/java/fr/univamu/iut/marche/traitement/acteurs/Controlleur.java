package fr.univamu.iut.marche.traitement.acteurs;


import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

public class Controlleur {
    private ArrayList<ProduitFermier> listeProduitsInterdits = new ArrayList<>();

    public void addProduitToList (ProduitFermier p){
        if (validerOffre(p) == true)
        listeProduitsInterdits.add(p);
    }

    public boolean isInterdit (ProduitFermier p){
        for (ProduitFermier produit: listeProduitsInterdits) {
            if (produit == p)
                return true;
        }
        return false;
    }

    public boolean validerOffre(ProduitFermier p){
        if (isInterdit(p) == true || p.valider("Label Rouge"))
            return true;
        return false;
    }
}
