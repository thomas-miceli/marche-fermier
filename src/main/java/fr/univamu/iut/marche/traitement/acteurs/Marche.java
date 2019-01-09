package fr.univamu.iut.marche.traitement.acteurs;


import fr.univamu.iut.marche.traitement.Observer.Observer;
import fr.univamu.iut.marche.traitement.produits.*;
import fr.univamu.iut.marche.traitement.remises.StratProduitBio;
import fr.univamu.iut.marche.traitement.remises.StratProduitQuantite;

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
    private static ArrayList<TransactionFini> historiqueDesVentes = new ArrayList<>();
    private String region;
    private Controlleur controlleur;

    public Marche(String region,Controlleur c) {
        this.region = region;
        this.controlleur= c;
    }

    /**
     * revoie la valeur de la variable region d'un objet de type Marche
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * ajoute une Vente à la liste des offres offresMarche
     * @param vente
     */
    public void addVente(Vente vente){
        compositionMarche.add(vente);
        System.out.println("un nouveau produit est disponible");
        updateAll();
    }

    /**
     * ajoute une Offre à la liste des offres offresMarche
     * @param o
     */
    public void addOffre(Offre o){
        offresMarche.add(o);
        System.out.println("une nouvelle offre est présente");
    }

    /**
     * affiche l'ensemble des Vente et Offre présentes pour le Marche sur el terminal
     */
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

    /**
     *  revoie le contenu de la variable offresMarche d'un objet de type Marche
     * @return offresMarche
     */
    public static ArrayList<Offre> getOffresMarche() {
        return offresMarche;
    }

    /**
     *  revoie le contenu de la variable compositionMarche d'un objet de type Marche
     * @return compositionMarche
     */
    public static ArrayList<Vente> getCompositionMarche() {
        return compositionMarche;
    }

    /**
     *
     */
    public void updateMarket(){
        Identificateur i = new Identificateur();
        for (int j = offresMarche.size() - 1 ; j >= 0 ; j--) {
            for (int k = compositionMarche.size() - 1 ; k >= 0 ; k--) {
                if(offresMarche.get(j).getProduitOffre().equals(compositionMarche.get(k).getProduitVendu().identifier(i)))aquisition(offresMarche.get(j),compositionMarche.get(k));
            }
        }
    }

    /**
     *
     * @param o
     * @param v
     */
    public void aquisition(Offre o, Vente v){
        if(o.getPrixOffre( ).equals(v.getPrix())// cas o = v
               && (v.getProduitVendu().getQuantite())== o.getQuantite()
                ){
            if(v.getVendeur().getTrader()==null){
                v.getVendeur().addSolde(o.getPrixOffre(),v);
            }else{
                v.getVendeur().getTrader().ajouterAuSolde(v.getPrix());
                v.getVendeur().addSolde(o.getPrixOffre()*(7/8),v);
            }

            if (v.getProduitVendu().getType().equals("Miel") || v.getProduitVendu().getType().equals("Pomme")) {
                v.getVendeur().calculerCotisations(new StratProduitBio());
            }

            if (v.getProduitVendu().getQuantite() > 200) {
                v.getVendeur().calculerCotisations(new StratProduitQuantite());
            }

           v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre());//refaire
            o.getAcheteur().subSolde(o.getPrixOffre(),v);
           o.getAcheteur().addProduit(v.getProduitVendu());
           offresMarche.remove(o);
           compositionMarche.remove(v);
           new TransactionFini(o,v,o.getQuantite());
        }//cas basique
        else if(o.getPrixParU().equals(v.getPrixParU())){
            ProduitFermier pTemp= (ProduitFermier)v.getProduitVendu().clone();
            if(o.getQuantite()<v.getProduitVendu().getQuantite()){// cas o < v
                if(v.getVendeur().getTrader()==null){
                    v.getVendeur().addSolde(o.getPrixOffre(),o);
                }else{
                    v.getVendeur().getTrader().ajouterAuSolde(o.getPrixOffre());
                    v.getVendeur().addSolde(o.getPrixOffre()*(7/8),o);
                }
                o.getAcheteur().subSolde(o.getPrixOffre(),v);
                v.getProduitVendu().setQuantite(v.getProduitVendu().getQuantite()-o.getQuantite());
                v.setPrix(v.getPrix()-o.getPrixOffre());
                pTemp.setQuantite(o.getQuantite());
                System.out.println(o.getAcheteur()+" ---> " +pTemp.getClass().getSimpleName()+ " "+ pTemp.getQuantite());
                o.getAcheteur().addProduit(pTemp);
                offresMarche.remove(o);
                new TransactionFini(o,v,o.getQuantite());
            }
            else if(o.getQuantite()>v.getProduitVendu().getQuantite()){// cas o > v
                if(v.getVendeur().getTrader()==null){
                    v.getVendeur().addSolde(v.getPrix(),v);
                }else{
                    v.getVendeur().getTrader().ajouterAuSolde(v.getPrix());
                    v.getVendeur().addSolde(v.getPrix()*(7/8),v);
                }

                o.setPrixOffre(o.getPrixOffre()-v.getPrix());
                o.getAcheteur().setSolde(o.getAcheteur().getSolde()-v.getPrix());
                o.getAcheteur().subSolde(v.getPrix(),v);
                o.setQuantite(o.getQuantite()-v.getProduitVendu().getQuantite());
                pTemp.setQuantite(v.getProduitVendu().getQuantite());
                compositionMarche.remove(v);
                new TransactionFini(o,v,v.getProduitVendu().getQuantite());
            }
        }
    }

    /**
     * ajoute un Observer à observers
     * @param o
     */
    public void addObserver(Observer o){
        this.observers.add(o);
    }

    /**
     * mets à jour le marché en cas d'ajout de vente
     */
    public void updateAll(){
        for (Observer o: observers
             ) {
            o.updateO();
        }
    }//provisoire

    /**
     * calcule la moyenne de prix d'un ProduitFermier sur le Marche
     * @param p
     * @return prixTot/qMoy
     */
    public Integer cotationProduitparU(Participant.Produits p){
        Identificateur identificateur = new Identificateur();
        int prixTot=0;
        int qMoy=0;
        for (Vente v: compositionMarche) {
            if(v.getProduitVendu().identifier(identificateur).equals(p)){
                prixTot+=v.getPrix();
                qMoy+=v.getProduitVendu().getQuantite();
            }
        }
        return prixTot/qMoy;
    }

    /**
     * revoie le contenu de la variable historiqueDesVentes d'un Marche
     * @return historiqueDesVentes
     */
    public ArrayList<TransactionFini> getHistoriqueDesVentes() {

        return historiqueDesVentes;
    }

    /**
     * ajoute une TransactionFinie à l'historique des ventes.
     * @param t
     */
    public void addTransactionFinie(TransactionFini t){
        historiqueDesVentes.add(t);
    }

    /**
     * revoie la valeur de la variable controller d'un objet de type Marche
     * @return controlleur
     */
    public Controlleur getControlleur() {
        return controlleur;
    }

    /**
     * récupère les Vente effectuées par un Participant dans compositionMarche d'un Marche
     * pour les stocker dans une liste ventesClient puis le renvoie
     * @param p
     * @return ventesClient
     */
    public ArrayList<Vente> getListVenteClient(Participant p){
        ArrayList<Vente> ventesClient = new ArrayList<>(0);
        for (Vente v: compositionMarche) {
            if(v.getVendeur()==p)ventesClient.add(v);
        }
        return ventesClient;
    }

    /**
     * récupère les Offre effectuées par un Participant dans offresMarche d'un Marche
     * pour les stocker dans une liste offresClient puis le renvoie
     * @param p
     * @return offresClient
     */
    public ArrayList<Offre> getListOffreClient(Participant p){
        ArrayList<Offre> offresClient = new ArrayList<>(0);
        for (Offre o: offresMarche) {
            if(o.getAcheteur()==p)offresClient.add(o);
        }
        return offresClient;
    }

    /**
     *  revoie la contenu de la variable historiqueDesVentes d'un objet de type Marche
     * @return historiqueDesVentes
     */
    public static ArrayList<TransactionFini> getTransactions(){
        return historiqueDesVentes;
    }
}
