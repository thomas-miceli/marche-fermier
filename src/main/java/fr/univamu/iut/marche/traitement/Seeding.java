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


        p1.fabriquerProduit(Apiculteur.ProduitsFabricables.MIEL, 550);
        p1.fabriquerProduit(Apiculteur.ProduitsFabricables.MIEL,1000);

        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.COCHON, 30);
        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.VACHE, 40);

        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.POMME, 500);
        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.ORANGE, 700);
        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.ORANGE,1000);
        p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.FROMAGE, 40);
        ProduitFermier p =p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.LAIT, 800);
        if( p.valider("COTON ROUGE")) System.out.println("ok");
        Marche marche = new Marche();
        p1.vendreProduit(p1, Participant.Produits.LAIT);

        p3.show();
        p3.setProduits(compilerProduits(p3.getProduits()));
        p3.show();
        marche.addParticipant(p1);
        marche.addParticipant(p2);
        marche.addParticipant(p3);
        marche.addParticipant(p4);

    }

    public static ArrayList<ProduitFermier> compilerProduits(ArrayList<ProduitFermier> listProd){
        Identificateur identificateur = new Identificateur();
        for (int i = 0; i <listProd.size() ; i++) {
            for (int j = 0; j <listProd.size() ; j++) {
                if(listProd.get(i).identifier(identificateur)==listProd.get(j).identifier(identificateur) && i != j){
                    listProd.get(i).fusionnerObjet(listProd.get(j));
                    listProd.remove(j);
                }
            }
        }

        return listProd;
    }
}
