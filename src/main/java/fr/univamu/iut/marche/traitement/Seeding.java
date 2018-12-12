package fr.univamu.iut.marche.traitement;


import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.produits.*;
import java.util.ArrayList;

/***
 * @author Pierre LEJEUNE
 * @author Thomas MICELI
 * Ceci est une classe de seeding, qui nous permet de créer des valeurs factices pour faire des tests et démontrer le
 * bon fonctionnement de l'application
 */
public class Seeding {
    Seeding(){
        Apiculteur p1 = new Apiculteur("Claude", "Jean", 50);
        ProducteurDeViande p2 = new ProducteurDeViande("Claudette", "Jean", 45);
        Orticulteur p3 = new Orticulteur("Claudinette", "Jean", 40);
        ProducteurLaitier p4 = new ProducteurLaitier("Claudasse", "Jean", 45);


        p1.fabriquerProduit(Apiculteur.ProduitsFabricables.MIEL);

        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.COCHON);
        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.VACHE);

        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.POMME);
        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.ORANGE);

        p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.FROMAGE);
        ProduitFermier p =p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.LAIT);
        if( p.valider("COTON ROUGE"))
            System.out.println("ok");


    }
}
