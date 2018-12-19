package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.Visiteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.Date;

public class Fromage extends ProduitFermier {

    public Fromage(int quantite, double prix, Date dateDePeremption) {
        super(quantite, prix, dateDePeremption);
    }

    @Override
    public Participant.Produits identifier(Visiteur v) {
        return v.visiter(this);
    }
}
