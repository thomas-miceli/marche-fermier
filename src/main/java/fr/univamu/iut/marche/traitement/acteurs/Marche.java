package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.Identificateur;

import java.util.*;

/***
 * @author Pierre LEJEUNE
 * @author Téo MARTIN
 * @author Yann FORNER
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */

public class Marche {
    private ArrayList<Vente> compositionMarche = new ArrayList<>();
    private ArrayList<Offre> offresMarche = new ArrayList<>();

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
            System.out.println(v.getProduitVendu().getId()+ "-"+ v.getProduitVendu().getClass().getSimpleName()+" : " + v.getPrix()+" euro pour "+v.getProduitVendu().getQuantite());
            System.out.println("vendu par "+v.getVendeur().getNom());
        }
        System.out.println("OFFRES :");
        for (Offre o: offresMarche) {
            System.out.println("------------");
            System.out.println(o.getProduitOffre()+" : " + o.getPrixOffre()+" euro pour "+o.getQuantite());
            System.out.println("Proposé par "+o.getAcheteur().getNom());
        }
        System.out.println("fin Marché");
        System.out.println("\n");
    }

    public ArrayList<Offre> getOffresMarche() {
        return offresMarche;
    }

    public ArrayList<Vente> getCompositionMarche() {
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
           v.getVendeur().setSolde(v.getVendeur().getSolde()+o.getPrixOffre());
           o.getAcheteur().setSolde(v.getVendeur().getSolde()-o.getPrixOffre());
           o.getAcheteur().addProduit(v.getProduitVendu());
           offresMarche.remove(o);
           System.out.println("test");
           compositionMarche.remove(v);

        }//cas basique

    }

}
