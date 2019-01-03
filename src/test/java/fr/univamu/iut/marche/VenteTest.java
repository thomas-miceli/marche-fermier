package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Controlleur;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Orticulteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Paysan;
import fr.univamu.iut.marche.traitement.produits.Miel;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import fr.univamu.iut.marche.traitement.remises.StratProduitBio;
import fr.univamu.iut.marche.traitement.remises.StratProduitQuantite;
import fr.univamu.iut.marche.traitement.remises.Strategy;
import org.junit.Test;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class VenteTest {

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après une vente sur le marché
     */
    @Test
    public void test_Apiculteur_Stock_Apres_Vente() {
        Marche marche = new Marche("PACA", new Controlleur());
        Paysan p1 = new Apiculteur("Miceli", "Thomas", 38);
        p1.setSolde(500);

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 100.0, marche);

        assertEquals(50, p1.getStock().get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien la bonne quantité de miel dans son stock après un achat sur le marché
     */
    @Test
    public void test_Apiculteur_Stock_Apres_Achat() {
        Marche marche = new Marche("PACA", new Controlleur());
        Paysan p1 = new Apiculteur("Miceli", "Thomas", 38);
        Paysan p2 = new Apiculteur("Forner", "Yann", 39);
        p1.setSolde(500);
        p2.setSolde(700);

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
        Marche marche = new Marche("PACA", new Controlleur());
        Paysan p1 = new Apiculteur("Miceli", "Thomas", 38);
        Paysan p2 = new Apiculteur("Forner", "Yann", 39);
        p1.setSolde(500);
        p2.setSolde(700);

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
    public void test_Solde_Apres_Achat() {
        Marche marche = new Marche("PACA", new Controlleur());
        Paysan p1 = new Apiculteur("Miceli", "Thomas", 38);
        Paysan p2 = new Apiculteur("Forner", "Yann", 39);
        p1.setSolde(500);
        p2.setSolde(700);

        p1.fabriquerProduit(Participant.Produits.MIEL, 150);
        p1.vendreProduit(Participant.Produits.MIEL, 100, 150.0, marche);

        p2.fabriquerProduit(Participant.Produits.MIEL, 150);
        p2.proposerOffre(Participant.Produits.MIEL, 20, 30.0, marche);

        assertEquals(530, p1.getSolde(), 0.001);
        assertEquals(670, p2.getSolde(), 0.001);
    }

}
