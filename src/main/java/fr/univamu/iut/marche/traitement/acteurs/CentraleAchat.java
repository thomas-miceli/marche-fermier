package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.*;

import java.util.ArrayList;
import java.util.Collections;

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
    public void vendre(Produits p,Participant vendeurInitial,Double prix , Integer quantite , Marche marche) {
        Identificateur i = new Identificateur();
        if(isMember(vendeurInitial)) {
            for (int j = vendeurInitial.getStock().size() - 1; j >= 0; j--) {
                if (vendeurInitial.getStock().get(j).identifier(i).equals(p) && vendeurInitial.getStock().get(j).getQuantite() >= quantite) {
                    if (vendeurInitial.getStock().get(j).getQuantite() == quantite) {
                        getStock().add(vendeurInitial.getStock().get(j));
                        vendeurInitial.removeProduit(vendeurInitial.getStock().get(j));
                    } else {
                        vendeurInitial.getStock().get(j).setQuantite(vendeurInitial.getStock().get(j).getQuantite() - quantite);
                        ProduitFermier pTemp= (ProduitFermier) vendeurInitial.getStock().get(j).clone();
                        getStock().add(pTemp);
                    }
                    if (isPresentVentesC(p)) {
                        for (Vente v : marche.getListVenteClient(this)) {
                            if (v.getProduitVendu().identifier(i).equals(p) && v.getPrixParU() == prix / quantite) {
                                v.getProduitVendu().setQuantite(v.getProduitVendu().getQuantite() + quantite);
                                v.setPrix(v.getPrixParU() * v.getProduitVendu().getQuantite());

                            }
                        }
                    } else {
                        this.vendreProduit(p, quantite, prix, marche);
                    }

                    VenteCentrale vLOL = new VenteCentrale(p, vendeurInitial, prix, quantite, marche);
                    System.out.println(vLOL);
                }
            }
        }
    }
    public void poserOffre(Produits produit,Participant achteurInitial,Double prix, Integer quantite,Marche marche){
        if(achteurInitial.getSolde()>=prix && isMember(achteurInitial)){
            this.setSolde(solde+prix);
            achteurInitial.subSolde(prix,(Vente)null);
            if(isPresentoffresC(produit)){
                for (Offre o: marche.getListOffreClient(this)) {
                    if(o.getProduitOffre().equals(produit) && o.getPrixParU()== prix/quantite){
                        o.setQuantite(o.getQuantite()+quantite);
                        o.setPrixOffre(o.getPrixParU()*o.getQuantite());
                    }
                }
            }else
            {
                this.proposerOffre(produit,quantite,prix,marche);
            }
            new OffreCentrale(produit,achteurInitial,prix,quantite,marche);
        }
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
        public void addProduit(ProduitFermier p){
            //ne doit absolument rien faire dans les cas d'une centrale
            System.out.println("JE FAIS RIEN LOL");
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
        private Participant.Produits produits;
        private Participant acheteur;
        private Double prix;
        private Integer quantite;
        private Double prixParU;
        private Marche marche;

        public OffreCentrale(Produits produits, Participant acheteur, Double prix, Integer quantite, Marche marche) {
            this.produits = produits;
            this.acheteur = acheteur;
            this.prix = prix;
            this.quantite = quantite;
            this.prixParU = prix/quantite;
            this.marche = marche;
            offresDeCentrale.add(this);
        }

        public Produits getProduits() {
            return produits;
        }

        public Participant getAcheteur() {
            return acheteur;
        }

        public Double getPrix() {
            return prix;
        }

        public Integer getQuantite() {
            return quantite;
        }

        public Double getPrixParU() {
            return prixParU;
        }

        public Marche getMarche() {
            return marche;
        }

        public void setQuantite(Integer quantite) {
            prix=prixParU*quantite;
            this.quantite = quantite;
        }

        @Override
        public String toString() {
            return "OffreCentrale{" +
                    "produits=" + produits +
                    ", acheteur=" + acheteur +
                    ", prix=" + prix +
                    ", quantite=" + quantite +
                    '}';
        }

    }
    public ArrayList<VenteCentrale> recupVentesCentrales(Produits p,Double prixParU){
        ArrayList<VenteCentrale> venteCentralesFiltrer = new ArrayList<>();
        for (VenteCentrale v: ventesDeCentrale
             ) {
            if(v.getProduits().equals(p)&& v.getPrixParU().equals(prixParU))venteCentralesFiltrer.add(v);
        }
        return venteCentralesFiltrer;
    }
    public ArrayList<OffreCentrale> recupOffresCentrales(Produits p , Double prixParU){
        ArrayList<OffreCentrale> offreCentralesFiltrer = new ArrayList<>();
        for (OffreCentrale o : offresDeCentrale
             ) {
            if(o.getProduits().equals(p)&& o.getPrixParU().equals(prixParU))offreCentralesFiltrer.add(o);
        }
        return offreCentralesFiltrer;
    }

    public void showCentralArray(){
        System.out.println("VENTES CENTRALES");
        for (VenteCentrale v: ventesDeCentrale) {
            System.out.println(v);
        }
        System.out.println("OFFRES CENTRALES");
        for (OffreCentrale o:offresDeCentrale){
            System.out.println(o);
        }
    }
    public boolean isPresentVentesC(Produits p){
        for (int j = ventesDeCentrale.size()-1 ; j >= 0 ; j--) {
            if(ventesDeCentrale.get(j).getProduits().equals(p))return true;
        }
        return false;
    }
    public boolean isPresentoffresC(Produits p){
        for (int j = offresDeCentrale.size()-1 ; j >= 0 ; j--) {
            if(offresDeCentrale.get(j).getProduits().equals(p))return true;
        }
        return false;
    }

    public void addSolde(Double prix,Vente v){
        System.out.println("CEST PASSSEEE VENTE " + prix);
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

    public void subSolde(Double prix , Vente v){
        Identificateur i = new Identificateur();
        ArrayList<OffreCentrale> recupFiltre = recupOffresCentrales(v.getProduitVendu().identifier(i),v.getPrixParU());;
        if(v.getProduitVendu().getQuantite()>=recupQuantiteTotDeOffreCentrale(v.getProduitVendu().identifier(i),v.getPrixParU())){
            for (OffreCentrale oC:recupFiltre
                 ) {
//                System.out.println("-------------"+oC.getQuantite()+"/"+recupQuantiteTotDeOffreCentrale(v.getProduitVendu().identifier(i),v.getPrixParU()));
                ProduitFermier prodTemp= (ProduitFermier)v.getProduitVendu().clone();
                prodTemp.setQuantite(oC.getQuantite());
                oC.getAcheteur().addProduit(prodTemp);
            }
            for (OffreCentrale  oC: recupFiltre
                 ) {

                removeO(oC,v.getMarche());
            }
        }else{
            Integer quantiteLever;
            if(v.getProduitVendu().getQuantite()>=recupQuantiteTotDeOffreCentrale(v.getProduitVendu().identifier(i),v.getPrixParU())){
                quantiteLever=recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i),v.getPrixParU());
                v.getProduitVendu().setQuantite(v.getProduitVendu().getQuantite()-quantiteLever);
            }else{
                quantiteLever=v.getProduitVendu().getQuantite();
                Marche.getCompositionMarche().remove(v);
            }
            for (int j = recupFiltre.size()-1; j >=0  ; j--) {
                ProduitFermier prodTemp = (ProduitFermier)v.getProduitVendu().clone();
                System.out.println("ICI");
                System.out.println(quantiteLever);
                if(quantiteLever==0)break;
                if(recupFiltre.get(j).getQuantite()<= quantiteLever){
                    prodTemp.setQuantite(recupFiltre.get(j).getQuantite());
                    recupFiltre.get(j).getAcheteur().addProduit(prodTemp);
                    quantiteLever-=recupFiltre.get(j).getQuantite();
                    this.removeO(recupFiltre.get(j),v.getMarche());

                }else{
                    prodTemp.setQuantite(quantiteLever);
                    recupFiltre.get(j).getAcheteur().addProduit(prodTemp);
                    recupFiltre.get(j).setQuantite(recupFiltre.get(j).getQuantite()-quantiteLever);
                    quantiteLever-=recupFiltre.get(j).getQuantite();
                }
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
    public Integer recupQuantiteTotDeOffreCentrale(Produits p,Double prixParaU){
        Integer quantiteOcTot =0;
        for (OffreCentrale oc: offresDeCentrale
             ) {
            if(p.equals(oc.getProduits()) && prixParaU.equals(oc.getPrixParU()))quantiteOcTot+=oc.getQuantite();
        }
        return quantiteOcTot;
    }

    private boolean isMember(Participant p){
        for (Participant membre : membres
             ) {
            if(membre==p)return true;
        }
        return false;
    }
    private boolean removeV(VenteCentrale v,Marche m){
        ventesDeCentrale.remove(v);
        Identificateur i = new Identificateur();
        for ( Vente venteOrigin : m.getListVenteClient(this)
             ) {
            if(venteOrigin.getProduitVendu().identifier(i).equals(v.getProduits())
                    && venteOrigin.getPrixParU().equals(v.getPrixParU())
                    && venteOrigin.getProduitVendu().getQuantite()==v.getQuantite()){
                Marche.getCompositionMarche().remove(venteOrigin);
            }

        }
        return true;
    }
    private boolean removeO(OffreCentrale o,Marche m){
        offresDeCentrale.remove(o);
        for ( Offre offreOrigin : m.getListOffreClient(this)
        ) {
            if(offreOrigin.getProduitOffre().equals(o.getProduits())
                    && offreOrigin.getPrixParU().equals(o.getPrixParU())
                    && offreOrigin.getQuantite().equals(o.getQuantite())){
                Marche.getOffresMarche().remove(offreOrigin);
            }

        }
        return true;
    }

}
