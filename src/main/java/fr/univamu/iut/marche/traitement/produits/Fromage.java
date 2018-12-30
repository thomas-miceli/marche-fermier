package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.Date;

public class Fromage extends ProduitFermier {

    public Fromage(int quantite, Date dateDePeremption) {
        super(quantite, dateDePeremption);
    }
    public Fromage(ProduitFermier copy){
        this.id=copy.id;
        this.quantite=copy.quantite;
        this.label=copy.label;
        this.dateDePeremption=copy.dateDePeremption;
    }//constructCopieur
    @Override
    public Participant.Produits identifier(ProduitVisiteur v) {
        return v.visiter(this);
    }
}
