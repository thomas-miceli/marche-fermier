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
        for (int j = vendeurInitial.getStock().size()-1; j >=0 ; j--) {
            if(vendeurInitial.getStock().get(j).identifier(i).equals(p) && vendeurInitial.getStock().get(j).getQuantite() >= quantite){
                if(vendeurInitial.getStock().get(j).getQuantite()==quantite){
                    this.addProduit(vendeurInitial.getStock().get(j));
                    vendeurInitial.removeProduit(vendeurInitial.getStock().get(j));
                }else{
                    vendeurInitial.getStock().get(j).setQuantite(vendeurInitial.getStock().get(j).getQuantite()-quantite);
                    ProduitFermier pTemp;
                    switch (p){
                        case MIEL:
                            pTemp = new Miel(vendeurInitial.getStock().get(j));
                            break;
                        case VACHE:
                            pTemp = new Vache(vendeurInitial.getStock().get(j));
                            break;
                        case POMME:
                            pTemp = new Pomme(vendeurInitial.getStock().get(j));
                            break;
                        case ORANGE:
                            pTemp = new Orange(vendeurInitial.getStock().get(j));
                            break;
                        case LAIT:
                            pTemp = new Lait(vendeurInitial.getStock().get(j));
                            break;
                        case FROMAGE:
                            pTemp = new Fromage(vendeurInitial.getStock().get(j));
                            break;
                        case COCHON:
                            pTemp = new Cochon(vendeurInitial.getStock().get(j));
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

                VenteCentrale vLOL = new VenteCentrale(p,vendeurInitial,prix,quantite,marche);
                System.out.println(vLOL);
            }
        }
//        for (ProduitFermier produitsVendeur :vendeurInitial.getStock()) {
//
//        }
    }

    private class VenteCentrale {

        private Participant.Produits produits;
        private Participant vendeur;
        private Double prix;
        private Integer quantite;
        private Double prixParU;
        private Marche marche;


        public VenteCentrale(Participant.Produits produits, Participant vendeur, Double prix, Integer quantite,Marche marche) {
            this.produits = produits;
            this.vendeur = vendeur;
            this.prix = prix;
            this.quantite = quantite;
            this.marche = marche;
            this.prixParU=prix/quantite;
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

        public Double getPrixParU() {
            return prixParU;
        }

        public void setQuantite(Integer quantite) {
            prix=prixParU*quantite;
            this.quantite = quantite;
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
    public ArrayList<VenteCentrale> recupVentesCentrales(Produits p,Double prixParU){
        ArrayList<VenteCentrale> venteCentralesFiltrer = new ArrayList<>();
        for (VenteCentrale v: ventesDeCentrale
             ) {
            if(v.getProduits().equals(p)&& v.getPrixParU().equals(prixParU))venteCentralesFiltrer.add(v);
        }
        return venteCentralesFiltrer;
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

    public void addSolde(Double prix,Vente v){
        System.out.println("CEST PASSSEEE  VENTE " + prix);
            Identificateur i = new Identificateur();
            ArrayList <VenteCentrale> recupFiltre = recupVentesCentrales(v.getProduitVendu().identifier(i),v.getPrixParU());
        if(v.getProduitVendu().getQuantite()==recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i),v.getPrixParU())){
            for (VenteCentrale vC: recupFiltre
                    ) {
                vC.getVendeur().addSolde(prix*((double)(vC.getQuantite()/(double)v.getProduitVendu().getQuantite())),(Vente) null);
                ventesDeCentrale.remove(vC);
            }
        }else{
            for (VenteCentrale vc: recupFiltre
                    ) {
                vc.getVendeur().addSolde(prix*((double)vc.getQuantite()/(double)recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i),v.getPrixParU())),(Vente) null);
                //                System.out.println((double)vc.getQuantite()+"/"+(double)recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU())+"="+(double)vc.getQuantite()/(double)recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU()));

            }
            for (VenteCentrale vc: recupFiltre
                    ) {
                vc.setQuantite(vc.getQuantite()*(v.getProduitVendu().getQuantite()/recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i),v.getPrixParU())));
                if(vc.getQuantite()==0)ventesDeCentrale.remove(vc);
            }
        }

    }
    public void addSolde(Double prix,Offre o){
        System.out.println("CEST PASSSEEE  OFFRE " + prix);
        Identificateur i = new Identificateur();
        ArrayList <VenteCentrale> recupFiltre = recupVentesCentrales(o.getProduitOffre(),o.getPrixParU());
        System.out.println(o.getQuantite()+"---------->" +recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU()) );
        if(o.getQuantite().equals(recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU()))){
            for (VenteCentrale vC: recupFiltre
                    ) {
                vC.getVendeur().addSolde(prix*((double)(vC.getQuantite()/(double)o.getQuantite())),(Vente) null);
                ventesDeCentrale.remove(vC);
            }
        }else{
            for (VenteCentrale vc: recupFiltre
                    ) {
                vc.getVendeur().addSolde(prix*((double)vc.getQuantite()/(double)recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU())),(Vente) null);
                //                System.out.println((double)vc.getQuantite()+"/"+(double)recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU())+"="+(double)vc.getQuantite()/(double)recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU()));

            }
            for (VenteCentrale vc: recupFiltre
                    ) {
                vc.setQuantite(vc.getQuantite()*(o.getQuantite()/recupQuantiteTotDeVenteCentrale(o.getProduitOffre(),o.getPrixParU())));
                if(vc.getQuantite()==0)ventesDeCentrale.remove(vc);
            }
        }
    }

    public Integer recupQuantiteTotDeVenteCentrale(Produits p , Double prixParU){

        Integer quantiteVcTot = 0;
        for (VenteCentrale vc: ventesDeCentrale) {
            if(p.equals(vc.getProduits())&& prixParU.equals(vc.getPrixParU())){
                quantiteVcTot+=vc.getQuantite();
            }
        }
        return quantiteVcTot;
    }
}
