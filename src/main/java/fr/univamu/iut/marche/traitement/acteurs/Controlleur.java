package fr.univamu.iut.marche.traitement.acteurs;


import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Téo MARTIN
 * @author Yann FORNER
 */
public class Controlleur {
    private ArrayList<ProduitFermier> listeProduitsInterdits = new ArrayList<>();

    private HashMap<Participant.Produits, Integer> minPrix = new HashMap<>();
    private HashMap<Participant.Produits, Integer> maxPrix = new HashMap<>();

    /**
     * Ajoute un objet de type ProduitFermier à la listeProduitsInterdits
     * @param p
     * @param label
     * @param prix
     * @param paysan
     */
    public void addProduitToList (ProduitFermier p, String label, int prix, Participant paysan){
        if (validerVente(p, label, prix, paysan))
            listeProduitsInterdits.add(p);
    }

    /**
     * renvoie vrai si le ProduitFermier défini se trouve dans listeProduitsInterdits, faux sinon
     * @param p
     * @return boolean
     */
    private boolean isInterdit(ProduitFermier p){
        for (ProduitFermier produit: listeProduitsInterdits) {
            if (produit == p)
                return true;
        }
        return false;
    }

    /**
     * Renvoie vrai si le produit prédéfini n'est pas interdit, si son label n'est pas invalide et si son prix
     * se trouve entre les bornes maxPrix et minPrix. Faux sinon.
     * @param p
     * @param label
     * @param prix
     * @param paysan
     * @return boolean
     */
    public boolean validerVente(ProduitFermier p, String label, double prix, Participant paysan){
        Participant.Produits prod = Participant.Produits.valueOf(p.getType().toUpperCase());

        if (isInterdit(p)) {
            System.out.println("Produit interdit");
            sanctionner(paysan);
            return false;
        }
        if (!p.valider(label)) {
            System.out.println("Label invalide");
            sanctionner(paysan);
            return false;
        }

        if (maxPrix.get(prod) != null && getMaxPrix(prod) < prix) {
            System.out.println("Prix trop haut");
            sanctionner(paysan);
            return false;
        }

        if (minPrix.get(prod) != null && getMinPrix(prod) > prix) {
            System.out.println("Prix trop bas");
            sanctionner(paysan);
            return false;
        }

        return true;
    }

    /**
     * modifie la valeur de la variable minPrix.
     * @param prod
     * @param prix
     */
    public void setMinPrix(Participant.Produits prod, int prix) {
        if (minPrix.containsKey(prod)) {
            minPrix.replace(prod, prix);
        } else {
            minPrix.put(prod, prix);
        }
    }

    /**
     * modifie la valeur de la variable maxPrix.
     * @param prod
     * @param prix
     */
    public void setMaxPrix(Participant.Produits prod, int prix) {
        if (maxPrix.containsKey(prod)) {
            maxPrix.replace(prod, prix);
        } else {
            maxPrix.put(prod, prix);
        }
    }

    /**
     * renvoie la valeur de la variable minPrix.
     * @param prod
     * @return minPrix
     */
    public int getMinPrix(Participant.Produits prod) {
        return minPrix.get(prod);
    }

    /**
     * renvoie la valeur de la variable maxPrix
     * @param prod
     * @return minPrix
     */
    public int getMaxPrix(Participant.Produits prod) {
        return maxPrix.get(prod);
    }

    /**
     * sanctionne un utilisateur en lui soustrayant une amende de son solde
     * @param vendeur
     */
    public void sanctionner (Participant vendeur){
        vendeur.subSolde(150.0, (Vente) null);
    }
}
