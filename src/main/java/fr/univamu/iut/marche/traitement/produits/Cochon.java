package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.Visiteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.Date;

public class Cochon extends ProduitFermier {

    public Cochon(int quantite, Date dateDePeremption) {
        super(quantite, dateDePeremption);
    }
    public Cochon(ProduitFermier copy){
        this.id=copy.id;
        this.quantite=copy.quantite;
        this.label=copy.label;
        this.dateDePeremption=copy.dateDePeremption;
    }//constructCopieur
    @Override
    public Participant.Produits identifier(Visiteur v) {
        return v.visiter(this);
    }
}
