package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

/**
 * @author Dylan MARCH
 * @author Téo MARTIN
 */
public abstract class Trader extends Participant {

    public Trader(String nom, String prenom, int age)
    {
        super(nom, prenom,age);
    }

}
