package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.*;

import java.util.*;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public abstract class UniteDeProduction {

    /**
     * Crée chaque produit par son type en fonction de sa quantité et date de peremption
     * @param type
     * @param quantite
     */
    public ProduitFermier cree(String type, int quantite){
        switch (type){
            case "MIEL":
                return new Miel(quantite, calcDatePeremption());
            case "ORANGE":
                return new Orange(quantite, calcDatePeremption());
            case "POMME":
                return new Pomme(quantite, calcDatePeremption());
            case "LAIT":
               return new Lait(quantite, calcDatePeremption());
            case "FROMAGE":
               return new Fromage(quantite, calcDatePeremption());
            case "VACHE":
               return new Vache(quantite, calcDatePeremption());
            case "COCHON":
               return new Cochon(quantite, calcDatePeremption());
            default:
                try {
                    throw new ClassNotFoundException();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public abstract ProduitFermier fabriquer(String type, int quantite) throws ClassNotFoundException ;

    /**
     * Calcule la date de péremption d'un produit
     */
    private Date calcDatePeremption(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return (c.getTime());
    }
}