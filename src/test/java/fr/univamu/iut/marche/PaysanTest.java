package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Controlleur;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Paysan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaysanTest {

    private Marche marche;
    private Apiculteur p1;
    private Apiculteur p2;

    @Before
    public void setUp() {
        marche = new Marche("PACA", new Controlleur());
        p1 = new Apiculteur("Miceli", "Thomas", 38);
        p2 = new Apiculteur("Forner", "Yann", 39);
        p1.setSolde(500);
        p2.setSolde(700);

    }

    @After
    public void tearDown() {
        marche = null;
        p1 = null;
        p2 = null;

    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après une vente sur le marché
     */
    @Test
    public void test_Apiculteur_Stock_Apres_Vente() {
        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 100.0, marche);

        assertEquals(50, p1.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Apiculteur_Stock_Apres_Achat() {
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
    public void test_Apiculteur_Stock_Apres_Vente_Achat() {

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);
        p2.vendreProduit(Participant.Produits.MIEL, 120, 130.0, marche);
        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(50, p2.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Apiculteur_Solde_Apres_Achat() {

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
    public void test_Apiculteur_Solde_Non_Suffisant() {

        p1.setSolde(500);
        p2.setSolde(10);

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);
        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(10, p2.getSolde(), 0.001);
    }


}