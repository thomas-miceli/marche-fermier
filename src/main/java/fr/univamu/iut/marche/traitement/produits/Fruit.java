package fr.univamu.iut.marche.traitement.produits;

import java.util.Date;

public abstract class Fruit extends ProduitFermier {
    public Fruit(int quantite, Date dateDePeremption) {
        super(quantite, dateDePeremption);
    }
}
