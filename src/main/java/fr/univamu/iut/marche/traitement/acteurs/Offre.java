package fr.univamu.iut.marche.traitement.acteurs;

/**
 * @author Téo MARTIN
 * @author Yann FORNER
 */
public class Offre {
    private Participant.Produits produitOffre;
    private Double prixOffre;
    private Participant acheteur;
    private Marche marche;
    private Integer quantite;
    private Double prixParU;

    public Offre(Participant.Produits produitOffre, Participant acheteur, Double prixOffre, Integer quantite, Marche marche) {
        this.produitOffre = produitOffre;
        this.prixOffre = prixOffre;
        this.acheteur = acheteur;
        this.marche = marche;
        this.quantite = quantite;
        this.prixParU = Double.valueOf((prixOffre / quantite));
        marche.addOffre(this);
        marche.updateMarket();
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return produitOffre
     */
    public Participant.Produits getProduitOffre() {
        return produitOffre;
    }

    /**
     * modifie la valeur de la variable produitOffre d'un objet Offre
     *
     * @param produitOffre
     */
    public void setProduitOffre(Participant.Produits produitOffre) {
        this.produitOffre = produitOffre;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return quantité
     */
    public Integer getQuantite() {
        return quantite;
    }

    /**
     * modifie la valeur de la variable quantite d'un objet Offre
     *
     * @param quantite
     */
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return marche
     */
    public Marche getMarche() {
        return marche;
    }

    /**
     * modifie la valeur de la variable produitOffre d'un objet Offre
     *
     * @param marche
     */
    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return prixOffre
     */
    public Double getPrixOffre() {
        return prixOffre;
    }

    /**
     * @param prixOffre
     */
    public void setPrixOffre(Double prixOffre) {
        this.prixOffre = prixOffre;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return acheteur
     */
    public Participant getAcheteur() {
        return acheteur;
    }

    /**
     * modifie la valeur de la variable acheteur d'un objet Offre
     *
     * @param acheteur
     */
    public void setAcheteur(Participant acheteur) {
        this.acheteur = acheteur;
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return prixParU
     */
    public Double getPrixParU() {
        return prixParU;
    }

    /**
     * modifie la valeur de la variable prixParU d'un objet Offre
     *
     * @param prixParU
     */
    public void setPrixParU(Double prixParU) {
        this.prixParU = prixParU;
    }

    /**
     * redéfinit la valeur du prixParU en fonction du prixOffre et la quantite
     */
    public void refreshPrixParU() {
        prixParU = (double) (prixOffre / quantite);
    }

    /**
     * revoie la variable produitOffre d'un objet de type Offre
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Offre{" +
                "produitOffre=" + produitOffre +
                ", prixOffre=" + prixOffre +
                ", quantite=" + quantite +
                '}';
    }
}
