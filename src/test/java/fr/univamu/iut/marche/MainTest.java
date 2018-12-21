package fr.univamu.iut.marche;

import fr.univamu.iut.marche.traitement.acteurs.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainTest {

    public void testApiculteur() {
        Apiculteur p1 = new Apiculteur("Miceli", "Thomas", 38);
        p1.setArgent(50.1);

        p1.fabriquerProduit(Participant.Produits.MIEL, 3.5,550);

        // ****
    }


}