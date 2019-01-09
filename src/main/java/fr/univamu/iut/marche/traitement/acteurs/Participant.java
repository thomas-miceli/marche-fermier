package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import fr.univamu.iut.marche.traitement.produits.Identificateur;
import fr.univamu.iut.marche.traitement.produits.*;
import fr.univamu.iut.marche.traitement.remises.StratProduitBio;
import fr.univamu.iut.marche.traitement.remises.StratProduitQuantite;
import fr.univamu.iut.marche.traitement.remises.Strategy;

import java.util.ArrayList;

//import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/**
 * @author Yann FORNER
 * @author Andrea GARCIA
 * @author Thomas MICELI
 * @author Téo MARTIN
 */
public abstract class Participant {
    public Trader trader=null;
    public enum Produits {
        COCHON,
        FROMAGE,
        LAIT,
        MIEL,
        ORANGE,
        POMME,
        VACHE
    }

    protected int id = 0;
    protected String nom;
    protected String prenom;
    protected int age;
    private ArrayList<ProduitFermier> stock = new ArrayList<>();
    protected double solde = 0;


    protected ArrayList<Vente> ventesNonRemisees = new ArrayList<>();

    protected static ArrayList<Participant> listeParticipant = new ArrayList<>();

    public Participant(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        id = listeParticipant.size()+1;
        listeParticipant.add(this);
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void opArgent(double montant) {
        this.solde += montant;
    }

    public static ArrayList<Participant> getAllParticipants(){
        return listeParticipant;
    }

    public static Participant getParticipantbyId(int id){
        for (Participant participant : listeParticipant) {
            if(participant.getId()==id) return participant;
        }
        return null;
    }

    public ArrayList<ProduitFermier> getStock() {
        return stock;
    }

    public void setStock(ArrayList<ProduitFermier> stock) {
        this.stock = stock;
    }

    /**
     * Ajoute le produit fermier au stock déjà existant de produits
     * @param produit
     */
    public void addProduit(ProduitFermier produit) {
        this.stock.add(produit);
        setStock(Seeding.compilerProduits(this.stock));
    }

    /**
     * Retire le produit fermier du stock
     * @param produit
     */
    public void removeProduit(ProduitFermier produit) {
        this.stock.remove(produit);
    }

    public ProduitFermier getProduit(Produits prod) {
        ProduitFermier produitFermier = null;
        for (ProduitFermier produ : stock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(prod.name())) {
                produitFermier = produ;
            }
        }
        return produitFermier;
    }

    /**
     * Ajoute de l'argent au solde existant du participant pour la vente de produits
     * @param prix
     * @param v
     */
    public void addSolde(Double prix,Vente v){
        System.out.println("ADD SOLDE NORMAL " + solde+ " + "+ prix);
        this.setSolde(this.getSolde()+prix);

    }
    /**
     * Ajoute de l'argent au solde existant du participant pour l'offre de produits
     * @param prix
     * @param o
     */
    public void addSolde(Double prix, Offre o){
        System.out.println("ADD SOLDE NORMAL");
        this.setSolde(this.getSolde()+prix);
    }

    /**
     * Retire de l'argent au solde existant du participant pour la vente de produits
     * @param prix
     * @param v
     */
    public void subSolde(Double prix , Vente v){
        System.out.println("SUB SOLDE NORMAL " + solde+" - "+prix );
        this.setSolde(this.getSolde()-prix);
    }
    /**
     * Retire de l'argent au solde existant du participant pour l'offre de produits
     * @param prix
     * @param o
     */
    public void subSolde(Double prix, Offre o){
        System.out.println("SUB SOLDE NORMAL");
        this.setSolde(this.getSolde()-prix);
    }

    /**
     * Fonction qui permet de vendre un produit. La fonction va chercher dans le stock si le produit à vendre est disponible, si oui, alors on établit la quantité à vendre ainsi que le prix.
        Le produit est vendu que si les règles de vente établies par le marché sont respectées.
     * @param produitAVendre
     * @param quantite
     * @param prix
     * @param marche
     */
    public void vendreProduit(Produits produitAVendre, Integer quantite, Double prix,Marche marche) {
        Identificateur identificateur = new Identificateur();
        for (int i = stock.size()-1; i >= 0 ; i--) {
            if(stock.get(i).identifier(identificateur).equals(produitAVendre)){
                if(stock.get(i).getQuantite()>=quantite){
                    ProduitFermier pTemp= (ProduitFermier) stock.get(i).clone();
                    pTemp.setQuantite(quantite);
                    if(marche.getControlleur().validerVente(pTemp,"validé", prix, this)){
                        new Vente(pTemp,this,prix,marche);
                        if(stock.get(i).getQuantite()-quantite==0){
                            stock.remove(stock.get(i));
                        }else{
                            stock.get(i).setQuantite(stock.get(i).getQuantite()-quantite);
                        }
                        System.out.println("la vente c'est bien passée");
                    }else{
                        System.out.println("le controle de l'objet c'est mal passé");
                    }


                }
            }
        }
    }

    /**
     * Fonction permettant à un participant de proposer une offre tant que le solde de celui-ci est supérieur au prix qu'il propose
     * @param produitAAcheter
     * @param quantite
     * @param prix
     * @param marche
     */
    public void proposerOffre(Produits produitAAcheter, Integer quantite, Double prix,Marche marche){
        if(solde >= prix ){
            new Offre(produitAAcheter,this,prix,quantite,marche);
        }else{
            System.out.println(nom+" n'a pas assez de solde pour placer cette offre");
        }
    }

    /**
     * Calcule les cotisations d'une participant en fonction d'une ou plusieurs stratégies.
     * @param strategies
     */
    public void calculerRemises(Strategy... strategies) {
        System.out.println("aa" + ventesNonRemisees);
        double remises = 0;
        for (Strategy s : strategies) {
            remises += s.calcRemise(this);
        }
        this.clearVenteNonRemisee();
        opArgent(0 - (this.solde * ((15 - (remises/100))/100)));
    }

    public void addVenteNonRemisee(Vente vente) {
        this.ventesNonRemisees.add(vente);
    }

    public void clearVenteNonRemisee() {
        this.ventesNonRemisees.clear();
    }

    public ArrayList<Vente> getVentesNonRemisees() {
        return ventesNonRemisees;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    @Override
    public String toString() {
        return nom+ "\n";
    }

}
