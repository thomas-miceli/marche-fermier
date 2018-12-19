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

        p1.setArgent( 50.1);
        p2.setArgent(100.2);
        p3.setArgent(150.3);
        p4.setArgent(200.4);

        p1.fabriquerProduit(Participant.Produits.MIEL, 3.5,550);
        p1.fabriquerProduit(Participant.Produits.MIEL, 7,1000);

        p2.fabriquerProduit(Participant.Produits.COCHON, 15, 30);
        p2.fabriquerProduit(Participant.Produits.VACHE, 20, 40);

        p3.fabriquerProduit(Participant.Produits.POMME, 5.4,500);
        p3.fabriquerProduit(Participant.Produits.ORANGE, 1.2, 700);
        p3.fabriquerProduit(Participant.Produits.ORANGE,1.5, 1000);
        p4.fabriquerProduit(Participant.Produits.FROMAGE, 5.4, 40);
        ProduitFermier p =p4.fabriquerProduit(Participant.Produits.LAIT, 2.1, 800);

        if( p.valider("COTON ROUGE")) System.out.println("ok");
        Marche marche = new Marche();
        for (Participant participants:Participant.getAllParticipants()) {
            marche.addParticipant(participants);
        }
        p1.vendreProduit(p1, Participant.Produits.LAIT);

        p3.show();
    }

    public static ArrayList<ProduitFermier> compilerProduits(ArrayList<ProduitFermier> listProd){
        Identificateur identificateur = new Identificateur();
        for (int i = 0; i <listProd.size() ; i++) {
            for (int j = 0; j <listProd.size() ; j++) {
                if(listProd.get(i).identifier(identificateur)==listProd.get(j).identifier(identificateur) && i != j && listProd.get(i).getDateDePeremption() == listProd.get(j).getDateDePeremption()){
                    listProd.get(i).fusionnerObjet(listProd.get(j));
                    listProd.remove(j);
                }
            }
        }

        return listProd;
    }
}
