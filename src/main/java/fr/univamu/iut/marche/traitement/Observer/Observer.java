package fr.univamu.iut.marche.traitement.Observer;

import fr.univamu.iut.marche.traitement.acteurs.Marche;

/**
 * @author Yann FORNER
 */
public abstract class Observer {
    Marche marche;

    public abstract void updateO();
}
