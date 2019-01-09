package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Orticulteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.ProducteurDeViande;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CentraleTest {

    private Marche marche;
    private CentraleAchat centrale;
    private Apiculteur p1;
    private Orticulteur p2;
    private ProducteurDeViande p3;

    @Before
    public void setUp() {
        marche = new Marche("PACA", new Controlleur());
        centrale = new CentraleAchat("XD", "XD", 38);
        p1 = new Apiculteur("Miceli", "Thomas", 38);
        p2 = new Orticulteur("Forner", "Yann", 39);
        p3 = new ProducteurDeViande("March", "Dylan", 40);
        p1.setSolde(500);
        p2.setSolde(700);
        p3.setSolde(900);
        centrale.addMembre(p1);
        centrale.addMembre(p2);
    }

    @After
    public void tearDown() {
        marche = null;
        centrale = null;
        p1 = null;
        p2 = null;
        p3 = null;

    }

    /**
     * Teste si les données de la vente sont respectées
     */
    @Test
    public void test_has_membres() {
        assertEquals(p1, centrale.getMembres().get(0));
        assertEquals(p2, centrale.getMembres().get(1));
    }

    @Test
    public void test_add_offre() {
        p1.fabriquerProduit(Participant.Produits.MIEL,1000);

        centrale.poserOffre(Participant.Produits.MIEL, p1,10.0,2,marche);
        assertEquals(p1, centrale.recupOffresCentrales(Participant.Produits.MIEL, 5.0).get(0).getAcheteur());
    }

    @Test
    public void test_add_vente() {
        p1.fabriquerProduit(Participant.Produits.MIEL,1000);

        centrale.vendre(Participant.Produits.MIEL, p1,10.0,2,marche);
        assertEquals(p1, centrale.recupVentesCentrales(Participant.Produits.MIEL, 5.0).get(0).getVendeur());
    }

}