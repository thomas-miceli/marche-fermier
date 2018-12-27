package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.Vente;


import java.util.*;

/***
 * @author Pierre LEJEUNE
 * @author Téo MARTIN
 * @author Yann FORNER
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */

public class Marche {
    private ArrayList<Vente> compositionMarche = new ArrayList<>();

    public void addVente(Vente vente){
        compositionMarche.add(vente);
        System.out.println("un nouveau produit est disponible");
    }

    public void show(){
        System.out.println("Marché :");
        for (Vente v: compositionMarche) {
            System.out.println("------------");
            System.out.println(v.getProduitVendu().getId()+ "-"+ v.getProduitVendu().getClass().getSimpleName()+" : " + v.getPrix()+" pour "+v.getProduitVendu().getQuantite());
            System.out.println("vendu par "+v.getVendeur().getNom());
        }
        System.out.println("fin Marché");
    }

    public ArrayList<Vente> getCompositionMarche() {
        return compositionMarche;
    }


}
