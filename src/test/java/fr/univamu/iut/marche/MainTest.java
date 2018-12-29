package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void test_Apiculteur_Stock() {
        Paysan p1 = new Apiculteur("Miceli", "Thomas", 38);
        p1.setSolde(50.1);

        ProduitFermier produit = p1.fabriquerProduit(Participant.Produits.MIEL, 550);

        ArrayList<ProduitFermier> stock = p1.getStock();
        ArrayList<ProduitFermier> stockToTest = new ArrayList<>(); stockToTest.add(produit);

        System.out.println(stock);
        assertEquals(stockToTest, stock);
    }

    @Test
    public void test_Apiculteur_Ventes() {
        Apiculteur p1 = new Apiculteur("Miceli", "Thomas", 38);
        p1.setSolde(50.1);

        ProduitFermier produit = p1.fabriquerProduit(Participant.Produits.MIEL, 550);

        p1.vendreProduit(Participant.Produits.MIEL);

        ArrayList<ProduitFermier> vente = p1.getProduitsAVendre();
        ArrayList<ProduitFermier> venteToTest = new ArrayList<>(); venteToTest.add(produit);

        assertEquals(venteToTest, vente);
    }


}