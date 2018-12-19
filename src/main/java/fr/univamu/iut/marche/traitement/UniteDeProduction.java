package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.ProducteurLaitier;
import fr.univamu.iut.marche.traitement.produits.*;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public abstract class UniteDeProduction {

    public ProduitFermier cree(String type, int quantite, double prix){
        switch (type){
            case "MIEL":
                return new Miel(quantite, prix, calcDatePremption());
            case "ORANGE":
                return new Orange(quantite, prix, calcDatePremption());
            case "POMME":
                return new Pomme(quantite, prix,calcDatePremption());
            case "LAIT":
               return new Lait(quantite, prix,calcDatePremption());
            case "FROMAGE":
               return new Fromage(quantite, prix,calcDatePremption());
            case "VACHE":
               return new Vache(quantite, prix, calcDatePremption());
            case "COCHON":
               return new Cochon(quantite, prix, calcDatePremption());
            default:
                try {
                    throw new ClassNotFoundException();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public abstract ProduitFermier fabriquer(String type, double prix, int quantite) throws ClassNotFoundException ;

    private Date calcDatePremption(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return (c.getTime());
    }
}