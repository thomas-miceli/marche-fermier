package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.List;
/**
 * @author Dylan MARCH
 */
public abstract class Trader extends Participant {

    public Trader(String nom, String prenom, int age)
    {
        super(nom, prenom, age);
    }

    public abstract void Acheter(ProduitFermier produitFermier);

    public abstract void Vendre(ProduitFermier produitFermier);
}
