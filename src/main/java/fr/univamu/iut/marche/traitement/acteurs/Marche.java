package fr.univamu.iut.marche.traitement.acteurs;


import fr.univamu.iut.marche.traitement.Observer.Observer;
import fr.univamu.iut.marche.traitement.produits.*;

import java.util.ArrayList;

/***
 * @author Pierre LEJEUNE
 * @author Téo MARTIN
 * @author Yann FORNER
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */

public class Marche {
    private static ArrayList<Vente> compositionMarche = new ArrayList<>();
    private static ArrayList<Offre> offresMarche = new ArrayList<>();
    private ArrayList<Observer> observers= new ArrayList<>();
    private ArrayList<TransactionFini> historiqueDesVentes = new ArrayList<>();
    private String region;

    public Marche(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void addVente(Vente vente){
        compositionMarche.add(vente);
        System.out.println("un nouveau produit est disponible");
        updateAll();
    }
    public void addOffre(Offre o){
        offresMarche.add(o);
        System.out.println("une nouvelle offre est présente");
    }

    public void show(){
        System.out.println("Marché :");
        System.out.println("VENTES : ");
        for (Vente v: compositionMarche) {
            System.out.println("------------");
            System.out.println(v.getProduitVendu().getClass().getSimpleName()+" : " + v.getPrix()+" euro pour "+v.getProduitVendu().getQuantite());
            System.out.println("vendu par "+v.getVendeur().getNom());
        }
        System.out.println("OFFRES :");
        for (Offre o: offresMarche) {
            System.out.println("------------");
            System.out.println(o.getProduitOffre()+" : " + o.getPrixOffre()+" euro pour "+o.getQuantite());
            System.out.println("Proposé par "+o.getAcheteur().getNom());
        }
        System.out.println("fin Marché");
        System.out.println("HISTORIQUE-------------");
        for (TransactionFini t: historiqueDesVentes) {
            System.out.println(t);
        }
        System.out.println("FIN HISTORIQUE ----------");
    }

    public static ArrayList<Offre> getOffresMarche() {
        return offresMarche;
    }

    public static ArrayList<Vente> getCompositionMarche() {
        return compositionMarche;
    }

    public void updateMarket(){
        Identificateur i = new Identificateur();
        for (int j = offresMarche.size() - 1 ; j >= 0 ; j--) {
            for (int k = compositionMarche.size() - 1 ; k >= 0 ; k--) {
                if(offresMarche.get(j).getProduitOffre().equals(compositionMarche.get(k).getProduitVendu().identifier(i)))aquisition(offresMarche.get(j),compositionMarche.get(k));
            }
        }
    }
    public void aquisition(Offre o, Vente v){
        if(o.getPrixOffre().equals(v.getPrix())
               && (v.getProduitVendu().getQuantite())== o.getQuantite()
                ){
            if(v.getVendeur().getTrader()==null){
                v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre());
            }else{
                v.getVendeur().getTrader().ajouterAuSolde(v.getPrix());
                v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre()*(7/8));
            }
           v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre());//refaire
           o.getAcheteur().setSolde(v.getVendeur().getSolde()-o.getPrixOffre());
           o.getAcheteur().addProduit(v.getProduitVendu());
           offresMarche.remove(o);
           System.out.println("test");
           compositionMarche.remove(v);
           new TransactionFini(o,v,o.getQuantite());
        }//cas basique
        else if(o.getPrixParU().equals(v.getPrixParU())){
            ProduitFermier pTemp;
            switch (o.getProduitOffre()){
                case MIEL:
                    pTemp = new Miel(v.getProduitVendu());
                    break;
                case VACHE:
                    pTemp = new Vache(v.getProduitVendu());
                    break;
                case POMME:
                    pTemp = new Pomme(v.getProduitVendu());
                    break;
                case ORANGE:
                    pTemp = new Orange(v.getProduitVendu());
                    break;
                case LAIT:
                    pTemp = new Lait(v.getProduitVendu());
                    break;
                case FROMAGE:
                    pTemp = new Fromage(v.getProduitVendu());
                    break;
                case COCHON:
                    pTemp = new Cochon(v.getProduitVendu());
                    break;
                default:
                    pTemp=null;
                    break;
            }
            if(o.getQuantite()<v.getProduitVendu().getQuantite()){
                v.getProduitVendu().setQuantite(v.getProduitVendu().getQuantite()-o.getQuantite());
                v.setPrix(v.getPrix()-o.getPrixOffre());
                if(v.getVendeur().getTrader()==null){
                    v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre());
                }else{
                    v.getVendeur().getTrader().ajouterAuSolde(v.getPrix());
                    v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre()*(7/8));
                }
                o.getAcheteur().setSolde(o.getAcheteur().getSolde()-o.getPrixOffre());
                pTemp.setQuantite(o.getQuantite());
                o.getAcheteur().addProduit(pTemp);
                offresMarche.remove(o);
                new TransactionFini(o,v,o.getQuantite());
            }
            if(o.getQuantite()>v.getProduitVendu().getQuantite()){
                o.setQuantite(o.getQuantite()-v.getProduitVendu().getQuantite());
                o.setPrixOffre(o.getPrixOffre()-v.getPrix());
                if(v.getVendeur().getTrader()==null){
                    v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre());
                }else{
                    v.getVendeur().getTrader().ajouterAuSolde(v.getPrix());
                    v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre()*(7/8));
                }
                o.getAcheteur().setSolde(o.getAcheteur().getSolde()-v.getPrix());
                pTemp.setQuantite(v.getProduitVendu().getQuantite());
                compositionMarche.remove(v);
                new TransactionFini(o,v,v.getProduitVendu().getQuantite());
            }
        }
    }
    public void addObserver(Observer o){
        this.observers.add(o);
    }
    public void updateAll(){
        for (Observer o: observers
             ) {
            o.updateO();
        }
    }//provisoire
    public Integer cotationProduitparU(Participant.Produits p){
        Identificateur identificateur = new Identificateur();
        int prixTot=0;
        int qMoy=0;
        for (Vente v: compositionMarche) {
            System.out.println(p);
            if(v.getProduitVendu().identifier(identificateur).equals(p)){
                prixTot+=v.getPrix();
                qMoy+=v.getProduitVendu().getQuantite();
            }
        }
        return prixTot/qMoy;
    }

    public ArrayList<TransactionFini> getHistoriqueDesVentes() {
        return historiqueDesVentes;
    }
    public void addTransactionFinie(TransactionFini t){
        historiqueDesVentes.add(t);
    }

}
