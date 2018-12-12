package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.UniteDeProduction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class ProduitFermier{
    private int quantite;
    private Date dateDePeremption;
    private String label;

    public ProduitFermier(int quantite, Date dateDePeremption) {
        this.quantite = quantite;
        this.dateDePeremption = dateDePeremption;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateDePeremption() {
        return dateDePeremption;
    }

    public void setDateDePeremption(Date dateDePeremption) {
        this.dateDePeremption = dateDePeremption;
    }

    public String setDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        return sdf.format(getDateDePeremption());
    }

    public boolean valider(String label){
        if(getDateDePeremption().compareTo(new Date())> 0){
            System.out.println(getDateDePeremption());
            System.out.println(new Date());
            this.label=label;
            return true;
        }
        return false;
    }
}
