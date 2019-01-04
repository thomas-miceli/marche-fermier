package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.*;

import java.util.ArrayList;

public class CentraleAchat extends Participant {

    private ArrayList<Participant> membres = new ArrayList<>();
    private ArrayList<VenteCentrale> ventesDeCentrale = new ArrayList<>();
    private ArrayList<OffreCentrale> offresDeCentrale = new ArrayList<>();
    public CentraleAchat(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public ArrayList<Participant> getMembres() {
        return membres;
    }

    public void addMembre(Participant p){
        membres.add(p);
    }

    @Override
    public String toString() {
        return "CentraleAchat{" +
                "membres=" + membres +
                ", nom='" + nom + '\'' +
                '}';
    }
    public void vendre(Produits p,Participant vendeurInitial,Double prix , Integer quantite , Marche marche){
        Identificateur i = new Identificateur();
        for (ProduitFermier produitsVendeur :vendeurInitial.getStock()) {
            if(produitsVendeur.identifier(i).equals(p) && produitsVendeur.getQuantite() >= quantite){
                if(produitsVendeur.getQuantite()==quantite){
                    vendeurInitial.removeProduit(produitsVendeur);
                    this.addProduit(produitsVendeur);
                }else{
                    produitsVendeur.setQuantite(produitsVendeur.getQuantite()-quantite);
                    ProduitFermier pTemp;
                    System.out.println("test-");
                    switch (p){
                        case MIEL:
                            pTemp = new Miel(produitsVendeur);
                            break;
                        case VACHE:
                            pTemp = new Vache(produitsVendeur);
                            break;
                        case POMME:
                            pTemp = new Pomme(produitsVendeur);
                            break;
                        case ORANGE:
                            pTemp = new Orange(produitsVendeur);
                            break;
                        case LAIT:
                            pTemp = new Lait(produitsVendeur);
                            break;
                        case FROMAGE:
                            pTemp = new Fromage(produitsVendeur);
                            break;
                        case COCHON:
                            pTemp = new Cochon(produitsVendeur);
                            break;
                        default:
                            pTemp=null;
                            break;
                    }
                    this.addProduit(pTemp);
                }
                if (isPresent(p)){
                    for (Vente v: marche.getListVenteClient(this)) {
                        if(v.getProduitVendu().identifier(i).equals(p) && v.getPrixParU()== prix/quantite){
                            v.getProduitVendu().setQuantite(v.getProduitVendu().getQuantite()+quantite);
                            v.setPrix(v.getPrixParU()*v.getProduitVendu().getQuantite());
                        }
                    }
                }else
                {
                    this.vendreProduit(p,quantite,prix,marche);
                }

                new VenteCentrale(p,vendeurInitial,prix,quantite,marche);
            }
        }
    }

    private class VenteCentrale {

        private Participant.Produits produits;
        private Participant vendeur;
        private Double prix;
        private Integer quantite;
        private Marche marche;


        public VenteCentrale(Participant.Produits produits, Participant vendeur, Double prix, Integer quantite,Marche marche) {
            this.produits = produits;
            this.vendeur = vendeur;
            this.prix = prix;
            this.quantite = quantite;
            this.marche = marche;
            ventesDeCentrale.add(this);
        }

        public Produits getProduits() {
            return produits;
        }

        public Participant getVendeur() {
            return vendeur;
        }

        public Double getPrix() {
            return prix;
        }

        public Integer getQuantite() {
            return quantite;
        }

        public Marche getMarche() {
            return marche;
        }

        @Override
        public String toString() {
            return "VenteCentrale{" +
                    "produits=" + produits.getClass().getSimpleName() +
                    ", vendeur=" + vendeur +
                    ", prix=" + prix +
                    ", quantite=" + quantite +
                    '}';
        }
    }
    private class OffreCentrale{

    }

    public void showCentralArray(){
        for (VenteCentrale v: ventesDeCentrale) {
            System.out.println(v);
        }
    }
    public boolean isPresent(Produits p){
        for (int j = ventesDeCentrale.size()-1 ; j >= 0 ; j--) {
            if(ventesDeCentrale.get(j).getProduits().equals(p))return true;
        }
        return false;
    }
    public void setSolde(double solde) {


    }

}
