package fr.univamu.iut.marche.traitement.acteurs;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class Vente {

    private ProduitFermier produitVendu;
    private Participant vendeur;
    private Double prix;
    private Double prixParU;
    private Marche marche;

    public Vente(ProduitFermier produitVendu, Participant vendeur, Double prix, Marche marche) {
        this.produitVendu = produitVendu;
        this.vendeur = vendeur;
        this.prix = prix;
        this.marche = marche;
        this.prixParU= Double.valueOf((prix/produitVendu.getQuantite()));
        marche.addVente(this);
        marche.updateMarket();
    }



    public Double getPrixParU() {
        return prixParU;
    }

    public void setPrixParU(Double prixParU) {
        this.prixParU = prixParU;
    }
    public ProduitFermier getProduitVendu() {
        return produitVendu;
    }

    public void setProduitVendu(ProduitFermier produitVendu) {
        this.produitVendu = produitVendu;
    }

    public Participant getVendeur() {
        return vendeur;
    }

    public void setVendeur(Participant vendeur) {
        this.vendeur = vendeur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public void refreshPrixParU(){
        prixParU= (double) (prix/produitVendu.getQuantite());
    }

    @Override
    public String toString() {
        return "Vente{" +
                "produitVendu=" +produitVendu.getQuantite() + " "+ produitVendu.getClass().getSimpleName() +
                ", prix=" + prix +
                '}';
    }
}
