package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class ProduictionDeFruit extends UniteDeProduction {
    @Override
    public ProduitFermier fabriquer(String type, Paysan P) throws ClassNotFoundException {
        return cree(type,P);
    }
}
