package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.ProducteurLaitier;
import fr.univamu.iut.marche.traitement.produits.*;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class UniteDeProduction {
    public ProduitFermier cree(String type, Paysan P)throws ClassNotFoundException{
        switch (type){
            case "orange":
                if(P.getProfession().equals("Orticulteur"))return new Lait(5,calcDatePremption());
            case "pomme":
                if(P.getProfession().equals("Orticulteur"))return new Lait(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
            case "lait":
                if(P.getProfession().equals("ProducteurLaitier"))return new Lait(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
            case "fromage":
                if(P.getProfession().equals("ProducteurLaitier"))return new Fromage(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
            case "vache":
                if(P.getProfession().equals("ProducteurDeViande"))return new Vache(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
                break;
            case "cochon":
                if(P.getProfession().equals("ProducteurDeViande"))return new Cochon(5,calcDatePremption());
                else {
                    System.out.println("Vous ne pouvez pas produire ceci");
                }
                break;
            default:
                throw new ClassNotFoundException();
        }
        return null;
    }
    public abstract ProduitFermier fabriquer(String type);

    private String calcDatePremption(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.FRENCH);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return sdf.format(c.getTime());
    }
}
