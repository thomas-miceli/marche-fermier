package fr.univamu.iut.marche.traitement.fabriques;

import fr.univamu.iut.marche.traitement.produits.*;

import java.util.*;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public abstract class UniteDeProduction {

    public ProduitFermier cree(String type, int quantite){
        switch (type){
            case "MIEL":
                return new Miel(quantite, calcDatePremption());
            case "ORANGE":
                return new Orange(quantite, calcDatePremption());
            case "POMME":
                return new Pomme(quantite,calcDatePremption());
            case "LAIT":
               return new Lait(quantite,calcDatePremption());
            case "FROMAGE":
               return new Fromage(quantite,calcDatePremption());
            case "VACHE":
               return new Vache(quantite, calcDatePremption());
            case "COCHON":
               return new Cochon(quantite, calcDatePremption());
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

    private Date calcDatePremption(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return (c.getTime());
    }
}