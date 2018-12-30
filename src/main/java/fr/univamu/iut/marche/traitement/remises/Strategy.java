package fr.univamu.iut.marche.traitement.remises;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

public interface Strategy {

    public double calcRemise(Participant p);
}
