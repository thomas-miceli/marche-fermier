package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.Vente;


import java.util.*;

/***
 * @author Pierre LEJEUNE
 * @author Téo MARTIN
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */

public class Marche {
    private ArrayList<Vente> compositionMarche = new ArrayList<>();

    public void addVente(Vente vente){
        compositionMarche.add(vente);
        System.out.println("un nouveau produit est disponible");
    }



    public ArrayList<Vente> getCompositionMarche() {
        return compositionMarche;
    }


}
