package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.*;

public class Identificateur extends Visiteur {
    @Override
    public Participant.Produits visiter(Miel m) {
        return Participant.Produits.MIEL;
    }

    @Override
    public Participant.Produits visiter(Cochon c) {
        return Participant.Produits.COCHON;
    }

    @Override
    public Participant.Produits visiter(Fromage f) {
        return Participant.Produits.FROMAGE;
    }

    @Override
    public Participant.Produits visiter(Lait l) {
        return Participant.Produits.LAIT;
    }

    @Override
    public Participant.Produits visiter(Orange o) {
        return Participant.Produits.ORANGE;
    }

    @Override
    public Participant.Produits visiter(Pomme p) {
        return Participant.Produits.POMME;
    }

    @Override
    public Participant.Produits visiter(Vache v) {
        return Participant.Produits.VACHE;
    }
}
