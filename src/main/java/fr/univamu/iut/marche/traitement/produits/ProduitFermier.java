package fr.univamu.iut.marche.traitement.produits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public abstract class ProduitFermier implements ProduitVisitable {
    protected int quantite;
    protected Date dateDePeremption;
    protected String label;
    protected int id=0;
    protected static ArrayList<ProduitFermier> listeProduit = new ArrayList<>();

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

    public String getType() {
        return this.getClass().getSimpleName();
    }

}
