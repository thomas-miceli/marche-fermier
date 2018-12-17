package fr.univamu.iut.marche.traitement;


import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.produits.*;

import java.sql.Date;
import java.time.LocalDate;
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


        p1.fabriquerProduit(Apiculteur.ProduitsFabricables.MIEL, 550);

        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.COCHON, 30);
        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.VACHE, 40);

        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.POMME, 500);
        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.ORANGE, 700);

        p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.FROMAGE, 40);
        ProduitFermier p =p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.LAIT, 800);
        p1.addProduit(new Miel(500, Date.valueOf(LocalDate.now())));
        if( p.valider("COTON ROUGE"))
            System.out.println("ok");

        Marche marche = new Marche();
        marche.addParticipant(p1);
        marche.addParticipant(p2);
        marche.addParticipant(p3);
        marche.addParticipant(p4);

        try {
            p1.vendreProduit(p1, Participant.Produits.LAIT);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        p1.toString();
        p2.toString();
        p3.toString();
        p4.toString();

    }
}
