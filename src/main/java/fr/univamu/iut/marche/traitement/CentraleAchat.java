package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.Trader;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CentraleAchat {
    Map<ProduitFermier, Integer> prixMoyen = new HashMap<>();

    ArrayList<Trader> acheteurs = new ArrayList<>();

    public CentraleAchat(Map<ProduitFermier, Integer> prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public void enregisterTrader(Trader trader) {
        acheteurs.add(trader);
    }

    public void recalculerPrix(String classeDeProduit) {

    }
}
