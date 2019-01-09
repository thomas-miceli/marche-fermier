package fr.univamu.iut.marche.traitement.acteurs;

/**
 * @author Yann FORNER
 */
public class TransactionFini {
    private Participant vendeur;
    private Participant acheteur;

    private Double prix;
    private Integer quantite;

    private Participant.Produits produitVendu;

    public TransactionFini(Offre o, Vente v, Integer quantite) {

        this.vendeur = v.getVendeur();
        this.acheteur = o.getAcheteur();
        this.quantite = quantite;
        this.prix = v.getPrixParU();
        this.produitVendu = o.getProduitOffre();
        o.getMarche().addTransactionFinie(this);

    }

    /**
     * revoie le vendeur de la TransactionFini
     *
     * @return vendeur
     */
    public Participant getVendeur() {
        return vendeur;
    }

    /**
     * modifie le vendeur de la TransactionFini
     *
     * @param vendeur
     */
    public void setVendeur(Participant vendeur) {
        this.vendeur = vendeur;
    }

    /**
     * renvoie l'acheteur de la TransactionFini
     *
     * @return acheteur
     */
    public Participant getAcheteur() {
        return acheteur;
    }

    /**
     * modifie l'acheteur de la TransactionFini
     *
     * @param acheteur
     */
    public void setAcheteur(Participant acheteur) {
        this.acheteur = acheteur;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return prix
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * modifie le prix de la TransactionFini
     *
     * @param prix
     */
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    /**
     * renvoie la quantite d'une TransactionFinie
     *
     * @return quantite
     */
    public Integer getQuantite() {
        return quantite;
    }

    /**
     * modifie la quantite d'une TransactionFini
     *
     * @param quantite
     */
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    /**
     * renvoie le produitVendu d'une TransactionFinie
     *
     * @return produitVendu
     */
    public Participant.Produits getProduitVendu() {
        return produitVendu;
    }

    /**
     * modifie le produitVendu d'une TransactionFinie
     *
     * @param produitVendu
     */
    public void setProduitVendu(Participant.Produits produitVendu) {
        this.produitVendu = produitVendu;
    }

    /**
     * revoie les valeurs des variables de TransactionFinie dans une chaine de caractères
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Vendeur : " + vendeur.getPrenom() + ' ' + vendeur.getNom() + " vends " + quantite + ' ' + produitVendu + " à " + acheteur.getPrenom() + ' ' + acheteur.getNom() + " pour " + prix + "€ l'unité";
    }
}
