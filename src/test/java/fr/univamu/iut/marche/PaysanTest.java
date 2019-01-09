package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Controlleur;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Orticulteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.Vente;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import fr.univamu.iut.marche.traitement.remises.StratProduitBio;
import fr.univamu.iut.marche.traitement.remises.StratProduitQuantite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PaysanTest {

    private Marche marche;
    private Apiculteur p1;
    private Apiculteur p2;
    private Orticulteur p3;

    @Before
    public void setUp() {
        marche = new Marche("PACA", new Controlleur());
        p1 = new Apiculteur("Miceli", "Thomas", 38);
        p2 = new Apiculteur("Forner", "Yann", 39);
        p3 = new Orticulteur("March", "Dylan", 40);
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

    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après une vente sur le marché
     */
    @Test
    public void test_Vendeur_Stock_Apres_Vente() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 100.0, marche);

        assertEquals(50, p1.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Acheteur_Stock_Apres_Achat() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);
        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(170, p2.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après une vente et un  achat sur le marché
     */
    @Test
    public void test_Acheteur_Stock_Apres_Vente_Achat() {

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);

        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(170, p2.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Vendeur_et_Acheteur_Solde_Apres_Achat() {

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);
        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(530, p1.getSolde(), 0.001);
        assertEquals(670, p2.getSolde(), 0.001);
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Acheteur_Solde_Non_Suffisant() {

        p1.setSolde(500);
        p2.setSolde(10);

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);
        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(10, p2.getSolde(), 0.001);
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Apiculteur_Solde_Ne_Peut_Pas_Creer_Cochon() {
        p1.setSolde(500);
        p2.setSolde(10);

        p1.fabriquerProduit(Participant.Produits.COCHON, 150);

        assertTrue(p1.getStock().isEmpty());
    }

    @Test
    public void test_Remise_Strategy_Bio() {
        p3.fabriquerProduit(Participant.Produits.POMME, 200);
        p3.fabriquerProduit(Participant.Produits.ORANGE, 90);

        p3.vendreProduit(Participant.Produits.POMME, 200, 200.0, marche);
        p3.vendreProduit(Participant.Produits.ORANGE, 90, 90.0, marche);

        p1.proposerOffre(Participant.Produits.POMME, 200, 200.0, marche);
        p1.proposerOffre(Participant.Produits.ORANGE, 90, 90.0, marche);

        p3.calculerRemises(new StratProduitBio());

        // this.solde  * ((15 - (remises/100))/100))
        //    1190     * ((15 - (      3/100))/100))
        //    1190     * ((15 -          0.03)/100))
        //    1190     * ((14.97)             /100))
        //    1190     *   0.1497 = 134.73

        // solde restant => 1190 - 134.73

        assertEquals(p3.getSolde(), 1011.857, 0.0001);
    }

    @Test
    public void test_Remise_Strategy_Quantite() {
        p3.fabriquerProduit(Participant.Produits.POMME, 200);
        p3.fabriquerProduit(Participant.Produits.ORANGE, 250);

        p3.vendreProduit(Participant.Produits.POMME, 200, 200.0, marche);
        p3.vendreProduit(Participant.Produits.ORANGE, 250, 250.0, marche);

        p1.proposerOffre(Participant.Produits.POMME, 200, 200.0, marche);
        p1.proposerOffre(Participant.Produits.ORANGE, 250, 250.0, marche);

        // 1350
        p3.calculerRemises(new StratProduitQuantite());

        // this.solde  * ((15 - (remises/100))/100))
        //    1350     * ((15 - (  5 + 5/100))/100))
        //    1350     * ((15 -           0.1)/100))
        //    1350     * ((14.9)              /100))
        //    1350     *   0.149 = 201.15

        // solde restant => 1350 - 201.15

        assertEquals(1148.85, p3.getSolde(), 0.001);
    }

    @Test
    public void test_Remise_Strategy_QuantiteBio() {

        p3.fabriquerProduit(Participant.Produits.POMME, 200);
        p3.fabriquerProduit(Participant.Produits.ORANGE, 250);

        p3.vendreProduit(Participant.Produits.POMME, 200, 200.0, marche);
        p3.vendreProduit(Participant.Produits.ORANGE, 250, 250.0, marche);

        p1.proposerOffre(Participant.Produits.POMME, 200, 200.0, marche);
        p1.proposerOffre(Participant.Produits.ORANGE, 250, 250.0, marche);

        // 1350
        p3.calculerRemises(new StratProduitQuantite(), new StratProduitBio());

        // this.solde * ((15 -   (remises/100))/100))
        //    1350     * ((15 - (5 + 5 + 3/100))/100))
        //    1350     * ((15 -           0.13) /100))
        //    1350     * ((14.87)               /100))
        //    1350     *   0.1487 = 200.745

        // solde restant => 1350 - 200.745

        assertEquals(1149.255, p3.getSolde(), 0.001);
    }

    @Test
    public void test_Ventes_Non_Remises() {
        p3.fabriquerProduit(Participant.Produits.POMME, 200);
        p3.fabriquerProduit(Participant.Produits.ORANGE, 250);

        p3.vendreProduit(Participant.Produits.POMME, 200, 200.0, marche);
        p3.vendreProduit(Participant.Produits.ORANGE, 250, 250.0, marche);

        p1.proposerOffre(Participant.Produits.POMME, 200, 200.0, marche);
        p1.proposerOffre(Participant.Produits.ORANGE, 250, 250.0, marche);

        assertFalse(p3.getVentesNonRemisees().isEmpty());

        p3.calculerRemises(new StratProduitQuantite(), new StratProduitBio());

        assertTrue(p3.getVentesNonRemisees().isEmpty());
    }

    @Test
    public void test_Ventes_Maximum() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 550);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        marche.show();

        //assertEquals(500, vente.getProduitVendu().getQuantite());

    }

}