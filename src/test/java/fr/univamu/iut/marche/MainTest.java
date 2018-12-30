package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Orticulteur;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Paysan;
import fr.univamu.iut.marche.traitement.produits.Miel;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import fr.univamu.iut.marche.traitement.remises.StratProduitBio;
import fr.univamu.iut.marche.traitement.remises.Strategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MainTest {

    /**
     * Teste si l'apiculteur a bien son produit dans son stock après création
     */
    @Test
    public void test_Apiculteur_Stock() {
        Paysan p1 = new Apiculteur("Miceli", "Thomas", 38);
        p1.setSolde(50.1);

        p1.fabriquerProduit(Participant.Produits.MIEL, 550);

        ArrayList<ProduitFermier> stock = p1.getStock();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        ProduitFermier miel = new Miel(550, c.getTime());

        assertEquals(miel.getQuantite(), stock.get(0).getQuantite());
    }

    /**
     * Teste si l'apiculteur a bien le bon nombre de produits après une vente sur le marché
     */
    @Test
    public void test_Apiculteur_Vente() {
        Apiculteur p1 = new Apiculteur("Miceli", "Thomas", 38);
        Marche marche = new Marche("PACA");
        p1.setSolde(50.1);

        p1.fabriquerProduit(Participant.Produits.MIEL, 550);

        p1.vendreProduit(Participant.Produits.MIEL, 200, 100, marche);

        ArrayList<ProduitFermier> vente = p1.getStock();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        ProduitFermier miel = new Miel(350, c.getTime());

        assertEquals(miel.getQuantite(), vente.get(0).getQuantite());
    }

    @Test
    public void test_Strategy_Bio() {
        Orticulteur p1 = new Orticulteur("Miceli", "Thomas", 38);
        p1.setSolde(500);
        p1.fabriquerProduit(Participant.Produits.POMME, 20);
        p1.fabriquerProduit(Participant.Produits.POMME, 40);
        p1.fabriquerProduit(Participant.Produits.POMME, 40);
        p1.fabriquerProduit(Participant.Produits.ORANGE, 40);
        p1.fabriquerProduit(Participant.Produits.ORANGE, 50);

        p1.calculerCotisations(new StratProduitBio()); // 500 * ((15 - 3 - 3 - 3) / 100) = 30 || 500 - 30

        assertEquals(p1.getSolde(), 440, 0.001);
    }


}