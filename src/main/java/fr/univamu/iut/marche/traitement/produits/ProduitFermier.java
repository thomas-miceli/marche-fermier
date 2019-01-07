package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public abstract class ProduitFermier implements ProduitVisitable,Cloneable {
    protected int quantite;
    protected Date dateDePeremption;
    protected String label;

    protected ProduitFermier() {
    }


    @Override
    public String toString() {
        return "ProduitFermier{" +
                "quantite=" + quantite +
                ", dateDePeremption=" + dateDePeremption +
                ", label='" + label + '\'' +
                '}';
    }

    public ProduitFermier(int quantite, Date dateDePeremption) {
        this.quantite = quantite;
        this.dateDePeremption = dateDePeremption;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getLabel() {
        return label;
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

    public String getDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        return sdf.format(getDateDePeremption());
    }

    public boolean valider(String label){
        if(getDateDePeremption().compareTo(new Date())> 0){
            this.label=label;
            return true;
        }
        return false;
    }
    public void fusionnerObjet(ProduitFermier produitFermier){
        if(this.getClass().getSimpleName().equals(produitFermier.getClass().getSimpleName())){
            setQuantite(quantite+produitFermier.getQuantite());
            produitFermier=null;
        }
    }
    public String getStringDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(dateDePeremption);
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Participant.Produits identifier(ProduitVisiteur v) {
        return null;
    }
    public Object clone() {
        Object o = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la
            // méthode super.clone()
            o = super.clone();
        } catch(CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return o;
    }
}
