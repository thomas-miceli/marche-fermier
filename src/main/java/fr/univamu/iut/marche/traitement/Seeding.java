package fr.univamu.iut.marche.traitement;


import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.produits.*;
import java.util.ArrayList;

/***
 * @author Pierre Lejeune
 * Ceci est une classe de seeding, qui nous permet de créer des valeurs factices pour faire des tests et démontrer le
 * bon fonctionnement de l'application
 */
public class Seeding {
    Seeding(){
        Paysan p1 = new Apiculteur("Claude", "Jean", 50);
        Paysan p2 = new Orticulteur("Claudette", "Jean", 45);
        Paysan p3 = new Orticulteur("Claudinette", "Jean", 40);

        Cochon cochon1 = new Cochon(5, "3/10/2018");
        Fruit fruit1 = new Orange(8, "4/10/2018");
        Miel miel1 = new Miel(3, "5/10/2018");

        Fromage fromage1 = new Fromage(9, "3/11/2018");
        Fruit fruit2 = new Pomme(80, "4/11/2018");
        Fruit fruit3 = new Orange(31, "5/11/2018");

        Fromage fromage2 = new Fromage(1, "3/12/2018");
        Fruit fruit4 = new Orange(1, "4/12/2018");
        Miel miel2 = new Miel(1, "5/12/2018");

        ArrayList list1 = new ArrayList<ProduitFermier>();
        ArrayList list2 = new ArrayList<ProduitFermier>();
        ArrayList list3 = new ArrayList<ProduitFermier>();

        list1.add(cochon1); list1.add(fruit1); list1.add(miel1);
        list2.add(fromage1); list2.add(fruit2); list2.add(fruit3);
        list3.add(fromage2); list3.add(fruit4); list3.add(miel2);

        p1.setProduits(list1);
        p2.setProduits(list2);
        p3.setProduits(list3);

        Marche marche = new Marche();
        marche.vendreProduit(p1, cochon1);
        marche.vendreProduit(p1, fruit1);

        marche.vendreProduit(p2, fromage1);

        marche.vendreProduit(p3, cochon1); // <- Rend une erreur

        p1.toString();
        p2.toString();
        p3.toString();
    }
}
