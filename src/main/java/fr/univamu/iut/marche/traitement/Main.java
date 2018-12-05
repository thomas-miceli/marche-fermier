package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.Cochon;
import fr.univamu.iut.marche.traitement.produits.Laitage;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList pro = new ArrayList();

        pro.add(new Cochon(5, "4"));
        pro.add(new Laitage(5, "4"));

        Paysan p1 = new Apiculteur("Oui", "Non", 40, pro);

        p1.addProduit(new Cochon(5, "4"));
    }
}
