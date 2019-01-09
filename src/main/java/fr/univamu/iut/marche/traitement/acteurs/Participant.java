package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import fr.univamu.iut.marche.traitement.produits.Identificateur;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
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
    public Trader trader = null;

    public enum Produits {
        COCHON,
        FROMAGE,
        LAIT,
        MIEL,
        ORANGE,
        POMME,
        VACHE
    }

    private int maxStock = 500;

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
        id = listeParticipant.size() + 1;
        listeParticipant.add(this);
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
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

    /**
     * modifie le prenom d'un Participant
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * renvoie l'age d'un Participant
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * renvoie l'id d'un Participant
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * renvoie l'âge d'un Participant
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * renvoie le contenu de la variable solde
     *
     * @return solde
     */
    public double getSolde() {
        return solde;
    }

    /**
     * modifie la valeur du solde d'un Participant
     *
     * @param solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }

    /**
     * ajoute un montant prédéfini au solde d'un Participant
     *
     * @param montant
     */
    public void opArgent(double montant) {
        this.solde += montant;
    }

    /**
     * renvoie vrai si la difference entre le solde d'un participant et un montant prédéfini est positif
     * faux sinon
     *
     * @param argent
     * @return boolean
     */
    public boolean canBuy(double argent) {
        return !((this.solde -= argent) < 0);
    }

    /**
     * renvoie le contenu de la variable listeParticipants
     *
     * @return listeParticipant
     */
    public static ArrayList<Participant> getAllParticipants() {
        return listeParticipant;
    }

    /**
     * renvoie un Participant parmi listeParticipant en fonction de son id
     *
     * @param id
     * @return Participant
     */
    public static Participant getParticipantbyId(int id) {
        for (Participant participant : listeParticipant) {
            if (participant.getId() == id) return participant;
        }
        return null;
    }

    /**
     * renvoie la valeur de la variable stock d'un Participant
     *
     * @return
     */
    public ArrayList<ProduitFermier> getStock() {
        return stock;
    }

    /**
     * modifie la valeur de la variable produitOffre d'un objet Participant
     *
     * @param stock
     */
    public void setStock(ArrayList<ProduitFermier> stock) {
        this.stock = stock;
    }

    /**
     * Ajoute le produit fermier au stock déjà existant de produits
     *
     * @param produit
     */
    public void addProduit(ProduitFermier produit) {

        // le stock max est de 500

        if (this.stock.size() == maxStock)
            return;

        int numproduits = 0;
        for (ProduitFermier prod : stock) {
            numproduits += prod.getQuantite();
        }

        numproduits += produit.getQuantite();

        if (maxStock + numproduits >= 500) {
            produit.setQuantite(maxStock + produit.getQuantite() - maxStock);
        }
        this.stock.add(produit);
        setStock(Seeding.compilerProduits(this.stock));
    }

    /**
     * Retire le produit fermier du stock
     *
     * @param produit
     */
    public void removeProduit(ProduitFermier produit) {
        this.stock.remove(produit);
    }

    /**
     * renvoie la variable produitFermier d'un objet de type Particpant
     *
     * @param prod
     * @return produitFermier
     */
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
     *
     * @param prix
     * @param v
     */
    public void addSolde(Double prix, Vente v) {
        System.out.println("ADD SOLDE NORMAL " + solde + " + " + prix);
        this.setSolde(this.getSolde() + prix);

    }

    /**
     * Ajoute de l'argent au solde existant du participant pour l'offre de produits
     *
     * @param prix
     * @param o
     */
    public void addSolde(Double prix, Offre o) {
        System.out.println("ADD SOLDE NORMAL" + prix);
        this.setSolde(this.getSolde() + prix);
    }

    /**
     * Retire de l'argent au solde existant du participant pour la vente de produits
     *
     * @param prix
     * @param v
     */
    public void subSolde(Double prix, Vente v) {
        System.out.println("SUB SOLDE NORMAL " + solde + " - " + prix);
        this.setSolde(this.getSolde() - prix);
    }

    /**
     * Retire de l'argent au solde existant du participant pour l'offre de produits
     *
     * @param prix
     * @param o
     */
    public void subSolde(Double prix, Offre o) {
        System.out.println("SUB SOLDE NORMAL");
        this.setSolde(this.getSolde() - prix);
    }

    /**
     * Fonction qui permet de vendre un produit. La fonction va chercher dans le stock si le produit à vendre est disponible, si oui, alors on établit la quantité à vendre ainsi que le prix.
     * Le produit est vendu que si les règles de vente établies par le marché sont respectées.
     *
     * @param produitAVendre
     * @param quantite
     * @param prix
     * @param marche
     */
    public void vendreProduit(Produits produitAVendre, Integer quantite, Double prix, Marche marche) {
        Identificateur identificateur = new Identificateur();
        for (int i = stock.size() - 1; i >= 0; i--) {
            if (stock.get(i).identifier(identificateur).equals(produitAVendre)) {
                if (stock.get(i).getQuantite() >= quantite) {
                    ProduitFermier pTemp = (ProduitFermier) stock.get(i).clone();
                    pTemp.setQuantite(quantite);
                    if (marche.getControlleur().validerVente(pTemp, "validé", prix, this)) {
                        new Vente(pTemp, this, prix, marche);
                        if (stock.get(i).getQuantite() - quantite == 0) {
                            stock.remove(stock.get(i));
                        } else {
                            stock.get(i).setQuantite(stock.get(i).getQuantite() - quantite);
                        }
                        System.out.println("la vente c'est bien passée");
                    } else {
                        System.out.println("le controle de l'objet c'est mal passé");
                    }


                }
            }
        }
    }

    /**
     * Fonction permettant à un participant de proposer une offre tant que le solde de celui-ci est supérieur au prix qu'il propose
     *
     * @param produitAAcheter
     * @param quantite
     * @param prix
     * @param marche
     */
    public void proposerOffre(Produits produitAAcheter, Integer quantite, Double prix, Marche marche) {
        if (quantite != 0) {
            if (solde >= prix) {
                new Offre(produitAAcheter, this, prix, quantite, marche);
            } else {
                System.out.println(nom + " n'a pas assez de solde pour placer cette offre");
            }
        }
    }

    /**
     * Calcule les cotisations d'une participant en fonction d'une ou plusieurs stratégies.
     *
     * @param strategies
     */
    public void calculerRemises(Strategy... strategies) {
        System.out.println("aa" + ventesNonRemisees);
        double remises = 0;
        for (Strategy s : strategies) {
            remises += s.calcRemise(this);
        }
        this.clearVenteNonRemisee();
        opArgent(0 - (this.solde * ((15 - (remises / 100)) / 100)));
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

    /**
     * modifie la valeur de la variable trader d'un objet Participant
     *
     * @param trader
     */
    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return String
     */
    @Override
    public String toString() {
        return nom + "\n";
    }

}
