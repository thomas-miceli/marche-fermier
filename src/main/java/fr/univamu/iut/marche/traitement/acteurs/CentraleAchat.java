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
        System.out.println("1");
        for (ProduitFermier produitsVendeur :vendeurInitial.getStock()) {
            System.out.println("2");
            if(produitsVendeur.identifier(i).equals(p) && produitsVendeur.getQuantite() >= quantite){
                System.out.println("3");
                if(produitsVendeur.getQuantite()==quantite){
                    System.out.println("4");
                    vendeurInitial.removeProduit(produitsVendeur);
                    this.addProduit(produitsVendeur);
                    System.out.println("test--");
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
                System.out.println("test");
                this.vendreProduit(p,quantite,prix,marche);
                new VenteCentrale(p,vendeurInitial,prix,quantite,marche);
            }else{
                System.out.println("toz");
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
}
