package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.UniteDeProduction;

import java.util.Date;

public abstract class ProduitFermier{
    private int quantite;
    private String dateDePeremption;
    private String label;

    public ProduitFermier(int quantite, String dateDePeremption) {
        this.quantite = quantite;
        this.dateDePeremption = dateDePeremption;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDateDePeremption() {
        return dateDePeremption;
    }

    public void setDateDePeremption(String dateDePeremption) {
        this.dateDePeremption = dateDePeremption;
    }

    public boolean valider(){

        return false;
    }
}
