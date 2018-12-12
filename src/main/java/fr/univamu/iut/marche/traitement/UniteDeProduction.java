package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.ProducteurLaitier;
import fr.univamu.iut.marche.traitement.produits.*;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Yann FORNER
 */
public abstract class UniteDeProduction {

    public ProduitFermier cree(String type, Paysan P)throws ClassNotFoundException{
        String prof = P.getClass().getSimpleName();
        switch (type){
            case "MIEL":
                if(prof.equals("Apiculteur"))return new Miel(5,calcDatePremption());
            case "ORANGE":
                if(prof.equals("Orticulteur"))return new Orange(5,calcDatePremption());
            case "POMME":
                if(prof.equals("Orticulteur"))return new Pomme(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
            case "LAIT":
                if(prof.equals("ProducteurLaitier"))return new Lait(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
            case "FROMAGE":
                if(prof.equals("ProducteurLaitier"))return new Fromage(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
            case "VACHE":
                if(prof.equals("ProducteurDeViande"))return new Vache(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
                break;
            case "COCHON":
                if(prof.equals("ProducteurDeViande"))return new Cochon(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
                break;
            default:
                throw new ClassNotFoundException();
        }
        return null;
    }

    public abstract ProduitFermier fabriquer(String type,Paysan P) throws ClassNotFoundException ;

    private Date calcDatePremption(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        Date format= (c.getTime());
        return format;
    }
}