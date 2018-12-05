package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.Cochon;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import fr.univamu.iut.marche.traitement.produits.Vache;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class UniteDeProduction {
    public ProduitFermier cree(String type, Paysan P)throws ClassNotFoundException{
        /*switch (type){
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
        }*/
        return null;
    }
    public String calcDatePremption(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.FRENCH);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return sdf.format(c.getTime());

    }
}
