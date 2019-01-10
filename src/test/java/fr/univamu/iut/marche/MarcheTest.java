package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Orticulteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.ProducteurDeViande;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Thomas MICELI
 */
public class MarcheTest {

    private Marche marche;
    private Apiculteur p1;
    private Orticulteur p2;
    private ProducteurDeViande p3;

    @Before
    public void setUp() {
        marche = new Marche("PACA", new Controlleur());
        p1 = new Apiculteur("Miceli", "Thomas", 38);
        p2 = new Orticulteur("Forner", "Yann", 39);
        p3 = new ProducteurDeViande("March", "Dylan", 40);
        p1.setSolde(500);
        p2.setSolde(700);
        p3.setSolde(900);
    }

    @After
    public void tearDown() {
        marche = null;
        p1 = null;
        p2 = null;
        p3 = null;
        System.out.println("-------------------");
    }

    /**
     * Teste si les données de l'offre sont respectées
     */
    @Test
    public void test_Offre_Donnees() {
        p1.proposerOffre(Participant.Produits.COCHON, 20, 50.0, marche);

        Offre offre = marche.getListOffreClient(p1).get(0);

        assertEquals(p1, offre.getAcheteur());
        assertEquals(50, offre.getPrixOffre(), 0.001);
        assertEquals(2.5, offre.getPrixParU(), 0.001);
        assertEquals(marche, offre.getMarche());
        assertEquals(Participant.Produits.COCHON, offre.getProduitOffre());
    }

    /**
     * Teste si les données de la vente sont respectées
     */
    @Test
    public void test_Vente_Donnees() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 100.0, marche);

        Vente vente = marche.getListVenteClient(p1).get(0);

        assertEquals(p1, vente.getVendeur());
        assertEquals(100, vente.getPrix(), 0.001);
        assertEquals(1, vente.getPrixParU(), 0.001);
        assertEquals(marche, vente.getMarche());
        assertEquals(100, vente.getProduitVendu().getQuantite());
        assertEquals("Miel", vente.getProduitVendu().getType());
    }



    /**
     * Teste si les données d'une vente sont respectées avec plusieurs vente dans le marché
     */
    @Test
    public void test_Vente_Donnees_Multiple() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 100.0, marche);

        p2.fabriquerProduit(Participant.Produits.POMME, 50);
        p2.vendreProduit(Participant.Produits.POMME, 25, 10.0, marche);

        p3.fabriquerProduit(Participant.Produits.COCHON, 5);
        p3.vendreProduit(Participant.Produits.COCHON, 5, 20.0, marche);

        Vente vente1 = marche.getListVenteClient(p1).get(0);
        Vente vente2 = marche.getListVenteClient(p2).get(0);
        Vente vente3 = marche.getListVenteClient(p3).get(0);

        assertEquals(p1, vente1.getVendeur());
        assertEquals(100, vente1.getPrix(), 0.001);
        assertEquals(p2, vente2.getVendeur());
        assertEquals(10, vente2.getPrix(), 0.001);
        assertEquals(p3, vente3.getVendeur());
        assertEquals(20, vente3.getPrix(), 0.001);

    }

    /**
     * Teste si les données d'une offre sont respectées avec plusieurs offres dans le marché
     */
    @Test
    public void test_Offre_Donnees_Multiple() {
        p1.proposerOffre(Participant.Produits.COCHON, 20, 50.0, marche);
        p2.proposerOffre(Participant.Produits.POMME, 10, 150.0, marche);
        p3.proposerOffre(Participant.Produits.FROMAGE, 30, 150.0, marche);

        Offre offre1 = marche.getListOffreClient(p1).get(0);
        Offre offre2 = marche.getListOffreClient(p2).get(0);
        Offre offre3 = marche.getListOffreClient(p3).get(0);

        assertEquals(p1, offre1.getAcheteur());
        assertEquals(50, offre1.getPrixOffre(), 0.001);
        assertEquals(p2, offre2.getAcheteur());
        assertEquals(150, offre2.getPrixOffre(), 0.001);
        assertEquals(p3, offre3.getAcheteur());
        assertEquals(150, offre3.getPrixOffre(), 0.001);
    }

    /**
     * Teste si les données d'une vente sont respectées avec plusieurs ventes dans le marché dont parfois par le même utilisateur
     */
    @Test
    public void test_Vente_Donnees_Multiple_Meme_Paysan() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 100.0, marche);
        p1.vendreProduit(Participant.Produits.MIEL, 50, 60.0, marche);

        p2.fabriquerProduit(Participant.Produits.POMME, 50);
        p2.fabriquerProduit(Participant.Produits.ORANGE, 30);
        p2.vendreProduit(Participant.Produits.POMME, 25, 10.0, marche);
        p2.vendreProduit(Participant.Produits.ORANGE, 15, 5.0, marche);

        p3.fabriquerProduit(Participant.Produits.COCHON, 5);
        p3.vendreProduit(Participant.Produits.COCHON, 5, 20.0, marche);

        Vente vente10 = marche.getListVenteClient(p1).get(0);
        Vente vente11 = marche.getListVenteClient(p1).get(1);
        Vente vente20 = marche.getListVenteClient(p2).get(0);
        Vente vente21 = marche.getListVenteClient(p2).get(1);
        Vente vente3 = marche.getListVenteClient(p3).get(0);

        assertEquals(p1, vente10.getVendeur());
        assertEquals(100, vente10.getPrix(), 0.001);
        assertEquals(p1, vente11.getVendeur());
        assertEquals(60, vente11.getPrix(), 0.001);

        assertEquals(p2, vente20.getVendeur());
        assertEquals(10, vente20.getPrix(), 0.001);
        assertEquals("Pomme", vente20.getProduitVendu().getType());
        assertEquals(p2, vente21.getVendeur());
        assertEquals(5, vente21.getPrix(), 0.001);
        assertEquals("Orange", vente21.getProduitVendu().getType());

        assertEquals(p3, vente3.getVendeur());
        assertEquals(20, vente3.getPrix(), 0.001);

    }

    /**
     * Teste si les données d'une offre sont respectées avec plusieurs offres dans le marché dont parfois par le même utilisateur
     */
    @Test
    public void test_Offre_Donnees_Multiple_Meme_Paysan() {
        p1.proposerOffre(Participant.Produits.COCHON, 20, 50.0, marche);
        p1.proposerOffre(Participant.Produits.POMME, 20, 50.0, marche);
        p2.proposerOffre(Participant.Produits.POMME, 20, 50.0, marche);
        p2.proposerOffre(Participant.Produits.FROMAGE, 10, 20.0, marche);
        p3.proposerOffre(Participant.Produits.FROMAGE, 15, 50.0, marche);

        Offre offre10 = marche.getListOffreClient(p1).get(0);
        Offre offre11 = marche.getListOffreClient(p1).get(1);
        Offre offre20 = marche.getListOffreClient(p2).get(0);
        Offre offre21 = marche.getListOffreClient(p2).get(1);
        Offre offre3 = marche.getListOffreClient(p3).get(0);

        assertEquals(p1, offre10.getAcheteur());
        assertEquals(50, offre10.getPrixOffre(), 0.001);
        assertEquals(Participant.Produits.COCHON, offre10.getProduitOffre());
        assertEquals(p1, offre11.getAcheteur());
        assertEquals(50, offre11.getPrixOffre(), 0.001);
        assertEquals(Participant.Produits.POMME, offre11.getProduitOffre());

        assertEquals(p2, offre20.getAcheteur());
        assertEquals(50, offre20.getPrixOffre(), 0.001);
        assertEquals(Participant.Produits.POMME, offre20.getProduitOffre());
        assertEquals(p2, offre21.getAcheteur());
        assertEquals(20, offre21.getPrixOffre(), 0.001);
        assertEquals(Participant.Produits.FROMAGE, offre21.getProduitOffre());

        assertEquals(p3, offre3.getAcheteur());
        assertEquals(50, offre3.getPrixOffre(), 0.001);
        assertEquals(Participant.Produits.FROMAGE, offre3.getProduitOffre());

    }
}