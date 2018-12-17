package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Dylan MARCH
 * @author Téo MARTIN
 */
public abstract class Trader extends Participant {

    ArrayList<ProduitFermier> stock = new ArrayList<>();

    public Trader(String nom, String prenom, int age)
    {
        super(nom, prenom, age);
    }

}
