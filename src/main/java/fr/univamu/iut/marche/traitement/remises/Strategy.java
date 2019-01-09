package fr.univamu.iut.marche.traitement.remises;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

/**
 * @author Thomas MICELI
 */
public interface Strategy {

    public double calcRemise(Participant p);
}
