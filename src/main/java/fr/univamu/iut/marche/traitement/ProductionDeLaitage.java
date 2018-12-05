package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

public class ProductionDeLaitage extends UniteDeProduction {
    @Override
    public ProduitFermier fabriquer(String type, Paysan P) throws ClassNotFoundException {
        ProduitFermier prod= cree(type,P);
        System.out.println("La production de Laitage a crée du "+prod.getNom()+" grace au paysan : "+ P.getNom());
        return prod;
    }
}
