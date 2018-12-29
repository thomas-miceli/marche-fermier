package fr.univamu.iut.marche.traitement.acteurs;


import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

public class Controlleur {
    private ArrayList<ProduitFermier> listeProduitsInterdits = new ArrayList<>();

    public void addProduitToList (ProduitFermier p, String label){
        if (validerOffre(p, label) == true)
        listeProduitsInterdits.add(p);
    }

    private boolean isInterdit(ProduitFermier p){
        for (ProduitFermier produit: listeProduitsInterdits) {
            if (produit == p)
                return true;
        }
        return false;
    }

    public boolean validerOffre(ProduitFermier p, String label){
        if (isInterdit(p) || p.valider(label))
            return true;
        return false;
    }

    public void choisirAcheteur() {
        //TODO
    }
}
