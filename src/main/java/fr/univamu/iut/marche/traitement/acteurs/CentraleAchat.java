package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.Identificateur;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

import static fr.univamu.iut.marche.traitement.Main.*;

/**
 * @author Yann FORNER
 */
public class CentraleAchat extends Participant {

    private ArrayList<Participant> membres = new ArrayList<>();
    private ArrayList<VenteCentrale> ventesDeCentrale = new ArrayList<>();
    private ArrayList<OffreCentrale> offresDeCentrale = new ArrayList<>();

    public CentraleAchat(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    /**
     * renvoie le contenu de la variable membres d'une CentraleAchat
     *
     * @return membres
     */
    public ArrayList<Participant> getMembres() {
        return membres;
    }

    /**
     * ajoute un Participant à la liste des membres membres
     *
     * @param p
     */
    public void addMembre(Participant p) {
        membres.add(p);
    }

    /**
     * renvoie l'ensemble des valeurs des variables d'un objet de type CentraleAchat
     *
     * @return String
     */
    @Override
    public String toString() {
        return ANSI_GREEN + "CentraleAchat" + ANSI_RESET + "@" + Integer.toHexString(System.identityHashCode(this)) + "{" +
                "membres=" + membres +
                ", ventesDeCentrale=" + ventesDeCentrale +
                ", offresDeCentrale=" + offresDeCentrale +
                '}';
    }

    /**
     * Ajoute une VenteCentrale à l'offre de Marche, si elle existe déjà, ajoute la quantité à la quantité
     * de l'offre de Marche, sinon créé un objet VenteCentrale
     *
     * @param p
     * @param vendeurInitial
     * @param prix
     * @param quantite
     * @param marche
     */
    public void vendre(Produits p, Participant vendeurInitial, Double prix, Integer quantite, Marche marche) {
        Identificateur i = new Identificateur();
        if (isMember(vendeurInitial)) {
            for (int j = vendeurInitial.getStock().size() - 1; j >= 0; j--) {
                if (vendeurInitial.getStock().get(j).identifier(i).equals(p) && vendeurInitial.getStock().get(j).getQuantite() >= quantite) {
                    if (vendeurInitial.getStock().get(j).getQuantite() == quantite) {
                        getStock().add(vendeurInitial.getStock().get(j));
                        vendeurInitial.removeProduit(vendeurInitial.getStock().get(j));
                    } else {
                        vendeurInitial.getStock().get(j).setQuantite(vendeurInitial.getStock().get(j).getQuantite() - quantite);
                        ProduitFermier pTemp = (ProduitFermier) vendeurInitial.getStock().get(j).clone();
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
                    new VenteCentrale(p, vendeurInitial, prix, quantite, marche);
                }
            }
        }
    }

    /**
     * Ajoute une OffreCentrale à l'offre de Marche, si elle existe déjà, ajoute la quantité à la quantité
     * de l'offre de Marche, sinon créé un objet OffreCentrale
     *
     * @param produit
     * @param achteurInitial
     * @param prix
     * @param quantite
     * @param marche
     */
    public void poserOffre(Produits produit, Participant achteurInitial, Double prix, Integer quantite, Marche marche) {
        if (achteurInitial.getSolde() >= prix && isMember(achteurInitial)) {
            this.setSolde(solde + prix);
            achteurInitial.subSolde(prix, (Vente) null);
            if (isPresentoffresC(produit)) {
                for (Offre o : marche.getListOffreClient(this)) {
                    if (o.getProduitOffre().equals(produit) && o.getPrixParU() == prix / quantite) {
                        o.setQuantite(o.getQuantite() + quantite);
                        o.setPrixOffre(o.getPrixParU() * o.getQuantite());
                    }
                }
            } else {
                this.proposerOffre(produit, quantite, prix, marche);
            }
            new OffreCentrale(produit, achteurInitial, prix, quantite, marche);
        }
    }

    public class VenteCentrale {

        private Participant.Produits produits;
        private Participant vendeur;
        private Double prix;
        private Integer quantite;
        private Double prixParU;
        private Marche marche;

        public VenteCentrale(Participant.Produits produits, Participant vendeur, Double prix, Integer quantite, Marche marche) {
            this.produits = produits;
            this.vendeur = vendeur;
            this.prix = prix;
            this.quantite = quantite;
            this.marche = marche;
            this.prixParU = prix / quantite;
            ventesDeCentrale.add(this);

        }

        /**
         * Override de la méthode addProduit dans une centrale pour qu'elle ne fasse rien
         *
         * @param p
         */
        public void addProduit(ProduitFermier p) {
            //ne doit absolument rien faire dans les cas d'une centrale
        }

        /**
         * renvoie le contenu de la variable produits d'un objet de type VenteCentrale
         *
         * @return produits
         */
        public Produits getProduits() {
            return produits;
        }

        /**
         * renvoie la valeur de la variable vendeur d'un objet de type VenteCentrale
         *
         * @return vendeur
         */
        public Participant getVendeur() {
            return vendeur;
        }

        /**
         * renvoie la valeur de la variable prix d'un objet de type VenteCentrale
         *
         * @return prix
         */
        public Double getPrix() {
            return prix;
        }

        /**
         * renvoie la valeur de la variable quantite d'un objet de type VenteCentrale
         *
         * @return quantite
         */
        public Integer getQuantite() {
            return quantite;
        }

        /**
         * renvoie la valeur de la variable marche d'un objet de type VenteCentrale
         *
         * @return marche
         */
        public Marche getMarche() {
            return marche;
        }

        /**
         * renvoie la valeur de la variable prixParU d'un objet de type VenteCentrale
         *
         * @return prixParU
         */
        public Double getPrixParU() {
            return prixParU;
        }

        /**
         * modifie la valeur de la variable quantite d'un objet de type VenteCentrale
         *
         * @param quantite
         */
        public void setQuantite(Integer quantite) {
            prix = prixParU * quantite;
            this.quantite = quantite;
        }

        /**
         * Affiche l'ensemble des valeurs des variables d'un objet de type VenteCentrale
         *
         * @return String
         */
        @Override
        public String toString() {
            return ANSI_GREEN + "VenteCentrale" + ANSI_RESET + "@" + Integer.toHexString(System.identityHashCode(this)) + "{" +
                    "produits=" + produits.getClass().getSimpleName() +
                    ", vendeur=" + vendeur +
                    ", prix=" + prix +
                    ", quantite=" + quantite +
                    '}';
        }
    }

    public class OffreCentrale {
        private Participant.Produits produits;
        private Participant acheteur;
        private Double prix;
        private Integer quantite;
        private Double prixParU;
        private Marche marche;

        /**
         * @param produits
         * @param acheteur
         * @param prix
         * @param quantite
         * @param marche
         */
        public OffreCentrale(Produits produits, Participant acheteur, Double prix, Integer quantite, Marche marche) {
            this.produits = produits;
            this.acheteur = acheteur;
            this.prix = prix;
            this.quantite = quantite;
            this.prixParU = prix / quantite;
            this.marche = marche;
            offresDeCentrale.add(this);
        }

        /**
         * renvoie le contenu de la variable produits d'un objet de type OffreCentrale
         *
         * @return produits
         */
        public Produits getProduits() {
            return produits;
        }

        /**
         * renvoie la valeur de la variable acheteur d'un objet de type OffreCentrale
         *
         * @return acheteur
         */
        public Participant getAcheteur() {
            return acheteur;
        }

        /**
         * renvoie la valeur de la variable prix d'un objet de type OffreCentrale
         *
         * @return prix
         */
        public Double getPrix() {
            return prix;
        }

        /**
         * revoie la valeur de la variable quantité d'un objet de type OffreCentrale
         *
         * @return quantité
         */
        public Integer getQuantite() {
            return quantite;
        }

        /**
         * revoie la valeur de la variable prixParU d'un objet de type OffreCentrale
         *
         * @return prixParU
         */
        public Double getPrixParU() {
            return prixParU;
        }

        /**
         * revoie la valeur de la variable marche d'un objet de type OffreCentrale
         *
         * @return marche
         */
        public Marche getMarche() {
            return marche;
        }

        /**
         * modifie la valeur de la variable quantité et prix d'un objet de type OffreCentrale
         *
         * @param quantite
         */
        public void setQuantite(Integer quantite) {
            prix = prixParU * quantite;
            this.quantite = quantite;
        }

        /**
         * renvoie l'ensemble des valeurs des variables d'un objet OffreCentrale
         *
         * @return String
         */
        @Override
        public String toString() {
            return ANSI_GREEN + "OffreCentrale" + ANSI_RESET + "@" + Integer.toHexString(System.identityHashCode(this)) + "{" +
                    "produits=" + produits +
                    ", acheteur=" + acheteur +
                    ", prix=" + prix +
                    ", quantite=" + quantite +
                    '}';
        }

    }

    /**
     * Récupère parmi l'ensemble des VenteCentrale contenues dans la variable ventesDeCentrale celles qui
     * concernent un Produits et un prixParU prédéfinis.
     *
     * @param p
     * @param prixParU
     * @return venteCentralesFilter
     */
    public ArrayList<VenteCentrale> recupVentesCentrales(Produits p, Double prixParU) {
        ArrayList<VenteCentrale> venteCentralesFiltrer = new ArrayList<>();
        for (VenteCentrale v : ventesDeCentrale
        ) {
            if (v.getProduits().equals(p) && v.getPrixParU().equals(prixParU)) venteCentralesFiltrer.add(v);
        }
        return venteCentralesFiltrer;
    }

    /**
     * Récupère parmi l'ensemble des OffreCentrale contenues dans la variable offresDeCentrale celles qui
     * concernent un Produits et un prixParU prédéfinis.
     *
     * @param p
     * @param prixParU
     * @return offreCentralesFilter
     */
    public ArrayList<OffreCentrale> recupOffresCentrales(Produits p, Double prixParU) {

        ArrayList<OffreCentrale> offreCentralesFiltrer = new ArrayList<>();
        for (OffreCentrale o : offresDeCentrale
        ) {
            if (o.getProduits().equals(p) && o.getPrixParU().equals(prixParU)) offreCentralesFiltrer.add(o);
        }
        return new ArrayList<>(offreCentralesFiltrer);
    }

    /**
     * Affiche l'ensemble des ProduitFermier qui se trouvent dans les ArrayList ventesDeCentrale et
     * offresDeCentrale
     */
    public void showCentralArray() {
        System.out.println(ANSI_YELLOW + "Ventes de la centrale");
        for (VenteCentrale v : ventesDeCentrale) {
            System.out.println(v);
        }
        System.out.println(ANSI_YELLOW + "Offres de la centrale");
        for (OffreCentrale o : offresDeCentrale) {
            System.out.println(o);
        }
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @param p
     * @return boolean
     */
    public boolean isPresentVentesC(Produits p) {
        for (int j = ventesDeCentrale.size() - 1; j >= 0; j--) {
            if (ventesDeCentrale.get(j).getProduits().equals(p)) return true;
        }
        return false;
    }

    public boolean isPresentoffresC(Produits p) {
        for (int j = offresDeCentrale.size() - 1; j >= 0; j--) {
            if (offresDeCentrale.get(j).getProduits().equals(p)) return true;
        }
        return false;
    }

    /**
     * cas offre
     *
     * @param prix
     * @param v
     */
    public void addSolde(Double prix, Vente v) {
        Identificateur i = new Identificateur();
        ArrayList<VenteCentrale> recupFiltre = recupVentesCentrales(v.getProduitVendu().identifier(i), v.getPrixParU());
        if (v.getProduitVendu().getQuantite() == recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i), v.getPrixParU())) {
            for (VenteCentrale vC : recupFiltre
            ) {
                vC.getVendeur().addSolde(prix * ((double) (vC.getQuantite() / (double) v.getProduitVendu().getQuantite())), (Vente) null);
                ventesDeCentrale.remove(vC);
            }
        } else {
            for (VenteCentrale vc : recupFiltre
            ) {
                vc.getVendeur().addSolde(prix * ((double) vc.getQuantite() / (double) recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i), v.getPrixParU())), (Vente) null);

            }
            for (VenteCentrale vc : recupFiltre
            ) {
                vc.setQuantite(vc.getQuantite() * (v.getProduitVendu().getQuantite() / recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i), v.getPrixParU())));
                if (vc.getQuantite() == 0) ventesDeCentrale.remove(vc);
            }
        }

    }

    /**
     * cas vente
     *
     * @param prix
     * @param o
     */
    public void addSolde(Double prix, Offre o) {
        Identificateur i = new Identificateur();
        ArrayList<VenteCentrale> recupFiltre = recupVentesCentrales(o.getProduitOffre(), o.getPrixParU());
        System.out.println(o.getQuantite() + "---------->" + recupQuantiteTotDeVenteCentrale(o.getProduitOffre(), o.getPrixParU()));
        if (o.getQuantite().equals(recupQuantiteTotDeVenteCentrale(o.getProduitOffre(), o.getPrixParU()))) {
            for (VenteCentrale vC : recupFiltre
            ) {
                vC.getVendeur().addSolde(prix * ((double) (vC.getQuantite() / (double) o.getQuantite())), (Vente) null);
                ventesDeCentrale.remove(vC);
            }
        } else {
            for (VenteCentrale vc : recupFiltre
            ) {
                vc.getVendeur().addSolde(prix * ((double) vc.getQuantite() / (double) recupQuantiteTotDeVenteCentrale(o.getProduitOffre(), o.getPrixParU())), (Vente) null);

            }
            for (VenteCentrale vc : recupFiltre
            ) {
                vc.setQuantite(vc.getQuantite() * (o.getQuantite() / recupQuantiteTotDeVenteCentrale(o.getProduitOffre(), o.getPrixParU())));
                if (vc.getQuantite() == 0) ventesDeCentrale.remove(vc);
            }
        }
    }

    /**
     * @param prix
     * @param v
     */
    public void subSolde(Double prix, Vente v) {
        Identificateur i = new Identificateur();
        ArrayList<OffreCentrale> recupFiltre = recupOffresCentrales(v.getProduitVendu().identifier(i), v.getPrixParU());
        ;
        if (v.getProduitVendu().getQuantite() >= recupQuantiteTotDeOffreCentrale(v.getProduitVendu().identifier(i), v.getPrixParU())) {
            for (OffreCentrale oC : recupFiltre
            ) {
                ProduitFermier prodTemp = (ProduitFermier) v.getProduitVendu().clone();
                prodTemp.setQuantite(oC.getQuantite());
                oC.getAcheteur().addProduit(prodTemp);
            }
            for (OffreCentrale oC : recupFiltre
            ) {

                removeO(oC, v.getMarche());
            }
        } else {
            Integer quantiteLever;
            if (v.getProduitVendu().getQuantite() >= recupQuantiteTotDeOffreCentrale(v.getProduitVendu().identifier(i), v.getPrixParU())) {
                quantiteLever = recupQuantiteTotDeVenteCentrale(v.getProduitVendu().identifier(i), v.getPrixParU());
                v.getProduitVendu().setQuantite(v.getProduitVendu().getQuantite() - quantiteLever);
            } else {
                quantiteLever = v.getProduitVendu().getQuantite();
                Marche.getCompositionMarche().remove(v);
            }
            for (int j = recupFiltre.size() - 1; j >= 0; j--) {
                ProduitFermier prodTemp = (ProduitFermier) v.getProduitVendu().clone();
                if (quantiteLever == 0) break;
                if (recupFiltre.get(j).getQuantite() <= quantiteLever) {
                    prodTemp.setQuantite(recupFiltre.get(j).getQuantite());
                    recupFiltre.get(j).getAcheteur().addProduit(prodTemp);
                    quantiteLever -= recupFiltre.get(j).getQuantite();
                    this.removeO(recupFiltre.get(j), v.getMarche());

                } else {
                    prodTemp.setQuantite(quantiteLever);
                    recupFiltre.get(j).getAcheteur().addProduit(prodTemp);
                    recupFiltre.get(j).setQuantite(recupFiltre.get(j).getQuantite() - quantiteLever);
                    quantiteLever -= recupFiltre.get(j).getQuantite();
                }
            }
        }

    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @param p
     * @param prixParU
     * @return quantiteVcTot
     */
    public Integer recupQuantiteTotDeVenteCentrale(Produits p, Double prixParU) {
        Integer quantiteVcTot = 0;
        for (VenteCentrale vc : ventesDeCentrale) {
            if (p.equals(vc.getProduits()) && prixParU.equals(vc.getPrixParU())) {
                quantiteVcTot += vc.getQuantite();
            }
        }
        return quantiteVcTot;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @param p
     * @param prixParaU
     * @return quantitéOcTot
     */
    public Integer recupQuantiteTotDeOffreCentrale(Produits p, Double prixParaU) {
        Integer quantiteOcTot = 0;
        for (OffreCentrale oc : offresDeCentrale
        ) {
            if (p.equals(oc.getProduits()) && prixParaU.equals(oc.getPrixParU())) quantiteOcTot += oc.getQuantite();
        }
        return quantiteOcTot;
    }

    /**
     * revoie la valeur de la variable produitOffre d'un objet de type Offre
     *
     * @param p
     * @return boolean
     */
    private boolean isMember(Participant p) {
        for (Participant membre : membres
        ) {
            if (membre == p) return true;
        }
        return false;
    }


    /**
     * Supprime une offre centrale de la centrale et s'il n'y a pas d'autre offre du même produit, supprime
     * l'offre du marché
     *
     * @param o
     * @param m
     * @return boolean
     */
    private boolean removeO(OffreCentrale o, Marche m) {
        offresDeCentrale.remove(o);
        for (Offre offreOrigin : m.getListOffreClient(this)
        ) {
            if (offreOrigin.getProduitOffre().equals(o.getProduits())
                    && offreOrigin.getPrixParU().equals(o.getPrixParU())
                    && offreOrigin.getQuantite().equals(o.getQuantite())) {
                Marche.getOffresMarche().remove(offreOrigin);
            }

        }
        return true;
    }

}
