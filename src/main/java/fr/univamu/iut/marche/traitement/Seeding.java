package fr.univamu.iut.marche.traitement;


import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Orticulteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.ProducteurDeViande;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.ProducteurLaitier;
import fr.univamu.iut.marche.traitement.produits.*;
import java.util.ArrayList;

/***
 * @author Pierre LEJEUNE
 * @author Thomas MICELI
 * Ceci est une classe de seeding, qui nous permet de créer des valeurs factices pour faire des tests et démontrer le
 * bon fonctionnement de l'application
 */
public class Seeding {
    private static ArrayList<Marche> listeMarche = new ArrayList<Marche>();
    private static Participant FxmlUser = new Orticulteur("Fxml", "User", 18);

    Seeding(){
        FxmlUser = new Orticulteur("Utilisateur", "FXML", 18);
        FxmlUser.setSolde(50000);
        Marche marche = new Marche("PACA",new Controlleur());
        listeMarche.add(marche);
        Apiculteur p1 = new Apiculteur("Claude", "Jean", 50);
        ProducteurDeViande p2 = new ProducteurDeViande("Claudette", "Jean", 45);
        Orticulteur p3 = new Orticulteur("Claudinette", "Jean", 40);
        ProducteurLaitier p4 = new ProducteurLaitier("Claudasse", "Jean", 45);
        CentraleAchat centraleAchat = new CentraleAchat("corp","Marché",0);

        centraleAchat.addMembre(p1);
        centraleAchat.addMembre(p2);


        p1.setSolde( 1000.0);
        p2.setSolde(100.2);
        p3.setSolde(1500.3);
        p4.setSolde(200.4);

        p1.fabriquerProduit(Participant.Produits.MIEL ,550);
        p1.fabriquerProduit(Participant.Produits.MIEL,1000);

        p1.vendreProduit(Participant.Produits.MIEL,100,11.0,marche);
        p3.proposerOffre(Participant.Produits.MIEL,100,11.0,marche);
        p2.fabriquerProduit(Participant.Produits.COCHON,  30);
        p2.fabriquerProduit(Participant.Produits.VACHE, 40);

        p3.fabriquerProduit(Participant.Produits.POMME, 500);
        p3.fabriquerProduit(Participant.Produits.ORANGE,  700);
        p3.fabriquerProduit(Participant.Produits.ORANGE, 1000);
        p4.fabriquerProduit(Participant.Produits.FROMAGE, 40);
        ProduitFermier p =p4.fabriquerProduit(Participant.Produits.LAIT,  800);

        p1.show();
        p2.show();
        p3.show();


        p2.vendreProduit(Participant.Produits.COCHON, 15, 150.0, marche);

        marche.show();

        centraleAchat.showCentralArray();
        p1.show();
        p2.show();
        p3.show();

    }

    public static ArrayList<ProduitFermier> compilerProduits(ArrayList<ProduitFermier> listProd){
        Identificateur identificateur = new Identificateur();
        for (int i = 0; i <listProd.size() ; i++) {
            for (int j = 0; j <listProd.size() ; j++) {
                if(listProd.get(i).identifier(identificateur)==listProd.get(j).identifier(identificateur)
                        && i != j
                        && listProd.get(i).getStringDate() .equals(listProd.get(j).getStringDate())) {
                    listProd.get(i).fusionnerObjet(listProd.get(j));
                    listProd.remove(j);
                }
            }
        }
        return listProd;
    }

    public static ArrayList<Marche> getListeMarche(){
        return listeMarche;
    }
    public static Participant getFxmlUser(){
        return FxmlUser;
    }
}
