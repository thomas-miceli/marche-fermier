package fr.univamu.iut.marche.traitement.Observer;

import fr.univamu.iut.marche.traitement.acteurs.Marche;

public abstract class Observer {
    Marche marche;
    public abstract void updateO();
}
