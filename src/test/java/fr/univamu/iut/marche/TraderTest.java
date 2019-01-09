package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Controlleur;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TraderTest {

    private Marche marche;
    private Apiculteur p1;
    private Trader p2;
    private Apiculteur p3;
    private Trader p4;


    @Before
    public void setUp() {
        marche = new Marche("PACA", new Controlleur());
        p1 = new Apiculteur("Miceli", "Thomas", 38);
        p2 = new Trader("Forner", "Yann", 39);
        p3 = new Apiculteur("March", "Dylan", 40);
        p4 = new Trader("Garcia", "Andrea", 41);

        p1.setSolde(500);
        p2.setSolde(700.0);
        p3.setSolde(900);
        p4.setSolde(1100.0);

    }

    @After
    public void tearDown() {
        marche = null;
        p1 = null;
        p2 = null;

    }

    /**
     * Teste si le trader a bien un nouveau client
     */
    @Test
    public void test_Trader_Possede_Client() {

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);
        p2.addClients(p1);

        assertEquals(p1, p2.getClients().get(0));
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock vendue par le trader
     */
    @Test
    public void test_Trader_Met_En_Vente() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 1500);

        p2.addClients(p1);
        p2.metEnVente(p1, Participant.Produits.MIEL, 50, 75.0, marche);

        p3.proposerOffre(Participant.Produits.MIEL, 50, 75.0, marche);

        assertEquals(50, p3.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock achetée par le trader
     */
    @Test
    public void test_Trader_Propose_Offre() {

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.addClients(p3);
        p2.poseUneOffre(p3, Participant.Produits.MIEL, 50, 75.0, marche);

        assertEquals(50, p3.getStock().get(0).getQuantite());
    }

    /**
     * Teste le bon fonctionnement d'achat/vente entre 2 traders
     */
    @Test
    public void test_Trader_Interactions() {

        p1.fabriquerProduit(Participant.Produits.MIEL, 200);
        p3.fabriquerProduit(Participant.Produits.MIEL, 40);

        p2.addClients(p1);
        p4.addClients(p3);

        p2.metEnVente(p1, Participant.Produits.MIEL, 150, 75.0, marche);
        p4.poseUneOffre(p3, Participant.Produits.MIEL, 150, 75.0, marche);

        assertEquals(190, p3.getStock().get(0).getQuantite());
    }

    @Test
    public void test_Trader_Solde() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 1500);

        p2.addClients(p1);
        p2.metEnVente(p1, Participant.Produits.MIEL, 100, 75.0, marche);

        p3.proposerOffre(Participant.Produits.MIEL, 40, 30.0, marche);

        assertEquals(703.75, p2.getSolde(), 0.001); // 700 + 30.0/8

        p3.proposerOffre(Participant.Produits.MIEL, 60, 45.0, marche);

        assertEquals(709.375, p2.getSolde(), 0.001); // 703.75 + 45.0/8
    }


}
