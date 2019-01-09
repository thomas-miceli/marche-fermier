package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.Date;

/**
 * @author Yann FORNER
 */
public class Pomme extends ProduitFermier {
    public Pomme(int quantite, Date dateDePeremption) {
        super(quantite, dateDePeremption);
    }
    public Pomme(ProduitFermier copy){
        this.quantite=copy.quantite;
        this.label=copy.label;
        this.dateDePeremption=copy.dateDePeremption;
    }//constructCopieur

    /**
     * Visiteur qui permet de retrouver le produit dans la classe où il est appelé
     * @param v
     * @return Participant.Produits
     */
    @Override
    public Participant.Produits identifier(ProduitVisiteur v) {
        return v.visiter(this);
    }
}
