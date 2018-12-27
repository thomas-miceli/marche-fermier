package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

public class CentraleAchat {

    private ArrayList<Trader> tradersEnregistres;
    private ArrayList<ProduitFermier> stockCentrale;
    private ArrayList<ProduitFermier> stockAVendreCentrale;

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

    public ArrayList<Trader> getTradersEnregistres() {
        return tradersEnregistres;
    }

    public void setTradersEnregistres(ArrayList<Trader> tradersEnregistres) {
        this.tradersEnregistres = tradersEnregistres;
    }

    public ArrayList<ProduitFermier> getStockCentrale() {
        return stockCentrale;
    }

    public void setStockCentrale(ArrayList<ProduitFermier> stockCentrale) {
        this.stockCentrale = stockCentrale;
    }

    public ArrayList<ProduitFermier> getStockAVendreCentrale() {
        return stockAVendreCentrale;
    }

    public void setStockAVendreCentrale(ArrayList<ProduitFermier> stockAVendreCentrale) {
        this.stockAVendreCentrale = stockAVendreCentrale;
    }
}
