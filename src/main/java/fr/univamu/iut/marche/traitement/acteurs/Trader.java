package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.List;
/**
 * @author Yann FORNER
 */
public class Trader extends Participant {
    private CentraleAchat centrale;

    public Trader(String nom, String prenom, int age, CentraleAchat centrale)
    {
        //super(nom, prenom, age);
        this.centrale = centrale;
    }

    public void Acheter(ProduitFermier produitFermier) {

    }

    public void Vendre(ProduitFermier produitFermier) {

    }
}
