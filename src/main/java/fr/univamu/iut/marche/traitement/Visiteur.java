package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.*;

public abstract class Visiteur {

    public Visiteur() {
    }
    public abstract Participant.Produits visiter(Miel m);
    public abstract Participant.Produits visiter(Cochon c);
    public abstract Participant.Produits visiter(Fromage f);
    public abstract Participant.Produits visiter(Lait l);
    public abstract Participant.Produits visiter(Orange o);
    public abstract Participant.Produits visiter(Pomme p);
    public abstract Participant.Produits visiter(Vache v);
}
