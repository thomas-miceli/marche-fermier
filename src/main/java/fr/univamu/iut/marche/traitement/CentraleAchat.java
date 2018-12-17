package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.acteurs.Trader;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CentraleAchat {

    private ArrayList<Trader> tradersEnregistres;
    private ArrayList<Participant.Produits> stockCentrale;

    public CentraleAchat() {

    }

    public void addTrader(Trader trader) {
        tradersEnregistres.add(trader);
    }

    public boolean hasTrader(Trader trader) {
        for (Trader _trader : tradersEnregistres) {
            if (_trader == trader) {
                return true;
            }
        }

        return false;
    }
}
