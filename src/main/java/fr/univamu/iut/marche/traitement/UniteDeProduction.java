package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.ProducteurLaitier;
import fr.univamu.iut.marche.traitement.produits.*;

import java.text.SimpleDateFormat;
import java.util.*;

public  class UniteDeProduction {
    public ProduitFermier cree(String type, Paysan P)throws ClassNotFoundException{
        String prof = P.getClass().getSimpleName();
        if ("orange".equals(type)) {
            if (prof.equals("Orticulteur")) return new Lait(5, calcDatePremption());

            if (prof.equals("Orticulteur")) return new Lait(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurLaitier")) return new Lait(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurLaitier")) return new Fromage(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurDeViande")) return new Vache(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

        } else if ("pomme".equals(type)) {
            if (prof.equals("Orticulteur")) return new Lait(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurLaitier")) return new Lait(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurLaitier")) return new Fromage(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurDeViande")) return new Vache(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

        } else if ("lait".equals(type)) {
            if (prof.equals("ProducteurLaitier")) return new Lait(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurLaitier")) return new Fromage(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurDeViande")) return new Vache(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

        } else if ("fromage".equals(type)) {
            if (prof.equals("ProducteurLaitier")) return new Fromage(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

            if (prof.equals("ProducteurDeViande")) return new Vache(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

        } else if ("vache".equals(type)) {
            if (prof.equals("ProducteurDeViande")) return new Vache(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

        } else if ("cochon".equals(type)) {
            if (prof.equals("ProducteurDeViande")) return new Cochon(5, calcDatePremption());
            else {
                System.out.println("Vous ne pouvez pas produire ceci");
            }

        } else {
            throw new ClassNotFoundException();
        }
        return null;
    }

    public ProduitFermier fabriquer(String type,Paysan P) throws ClassNotFoundException {
        ProduitFermier prod = cree(type,P);
       return prod;
    }

    private String calcDatePremption(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.FRENCH);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return sdf.format(c.getTime());
    }
}