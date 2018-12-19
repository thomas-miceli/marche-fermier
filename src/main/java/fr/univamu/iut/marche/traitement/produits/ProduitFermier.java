package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.IVisitable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public abstract class ProduitFermier implements IVisitable {
    private int quantite;
    private double prix;


    private Date dateDePeremption;
    private String label;

    protected int id=0;
    protected static ArrayList<ProduitFermier> listeProduit = new ArrayList<>();

    @Override
    public String toString() {
        return "ProduitFermier{" +
                "quantite=" + quantite +
                ", prix=" + prix +
                ", dateDePeremption=" + dateDePeremption +
                ", label='" + label + '\'' +
                '}';
    }

    public ProduitFermier(int quantite, Date dateDePeremption) {
        this.quantite = quantite;
        this.prix = 0;
        this.dateDePeremption = dateDePeremption;

        id = listeProduit.size()+1;
        listeProduit.add(this);
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        return sdf.format(getDateDePeremption());
    }
    public int getId() {
        return id;
    }
    public static ProduitFermier getProduitbyId(int id){
        for (ProduitFermier produitFermier : listeProduit) {
            if(produitFermier.getId()==id) return produitFermier;
        }
        return null;
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
}
