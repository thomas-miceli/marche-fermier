package fr.univamu.iut.marche.traitement.produits;

import java.util.Date;

public abstract class Laitage extends ProduitFermier {
    public Laitage(int quantite, Date dateDePeremption) {
        super(quantite, dateDePeremption);
    }
}
