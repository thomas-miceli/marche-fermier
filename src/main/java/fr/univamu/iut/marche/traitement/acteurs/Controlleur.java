package fr.univamu.iut.marche.traitement.acteurs;


import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Téo MARTIN
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public class Controlleur {
    private ArrayList<ProduitFermier> listeProduitsInterdits = new ArrayList<>();

    private HashMap<Participant.Produits, Integer> minPrix = new HashMap<>();
    private HashMap<Participant.Produits, Integer> maxPrix = new HashMap<>();

    public void addProduitToList (ProduitFermier p, String label, int prix){
        if (validerVente(p, label, prix))
            listeProduitsInterdits.add(p);
    }

    private boolean isInterdit(ProduitFermier p){
        for (ProduitFermier produit: listeProduitsInterdits) {
            if (produit == p)
                return true;
        }
        return false;
    }

    public boolean validerVente(ProduitFermier p, String label, double prix){
        Participant.Produits prod = Participant.Produits.valueOf(p.getType().toUpperCase());

        if (isInterdit(p)) {
            System.out.println("Produit interdit");
            return false;
        }
        if (!p.valider(label)) {
            System.out.println("Label invalide");
            return false;
        }

        if (maxPrix.get(prod) != null && getMaxPrix(prod) < prix) {
            System.out.println("Prix trop haut");
            return false;
        }

        if (minPrix.get(prod) != null && getMinPrix(prod) > prix) {
            System.out.println("Prix trop bas");
            return false;
        }

        return true;
    }

    public void setMinPrix(Participant.Produits prod, int prix) {
        if (minPrix.containsKey(prod)) {
            minPrix.replace(prod, prix);
        } else {
            minPrix.put(prod, prix);
        }
    }

    public void setMaxPrix(Participant.Produits prod, int prix) {
        if (maxPrix.containsKey(prod)) {
            maxPrix.replace(prod, prix);
        } else {
            maxPrix.put(prod, prix);
        }
    }

    public int getMinPrix(Participant.Produits prod) {
        return minPrix.get(prod);
    }

    public int getMaxPrix(Participant.Produits prod) {
        return maxPrix.get(prod);
    }

    public void choisirAcheteur() {
        //TODO
    }
}
