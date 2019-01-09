package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Yann FORNER
 */
public abstract class ProduitFermier implements ProduitVisitable, Cloneable {
    protected int quantite;
    protected Date dateDePeremption;
    protected String label;

    protected ProduitFermier() {
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ProduitFermier{" +
                "quantite=" + quantite +
                ", dateDePeremption=" + dateDePeremption +
                ", label='" + label + '\'' +
                '}';
    }

    public ProduitFermier(int quantite, Date dateDePeremption) {
        this.quantite = quantite;
        this.dateDePeremption = dateDePeremption;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return dateDePeremption
     */
    public Date getDateDePeremption() {
        return dateDePeremption;
    }

    /**
     * @param dateDePeremption
     */
    public void setDateDePeremption(Date dateDePeremption) {
        this.dateDePeremption = dateDePeremption;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return String
     */
    public String getDateToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        return sdf.format(getDateDePeremption());
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @param label
     * @return boolean
     */
    public boolean valider(String label) {
        if (getDateDePeremption().compareTo(new Date()) > 0) {
            this.label = label;
            return true;
        }
        return false;
    }

    /**
     * @param produitFermier
     */
    public void fusionnerObjet(ProduitFermier produitFermier) {
        if (this.getClass().getSimpleName().equals(produitFermier.getClass().getSimpleName())) {
            setQuantite(quantite + produitFermier.getQuantite());
            produitFermier = null;
        }
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return String
     */
    public String getStringDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(dateDePeremption);
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return this.getClass().getSimpleName()
     */
    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Participant.Produits identifier(ProduitVisiteur v) {
        return null;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @return o
     */
    public Object clone() {
        Object o = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la
            // méthode super.clone()
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return o;
    }
}
