package fr.univamu.iut.marche.traitement.produits;

import java.util.Date;

public abstract class Charcuterie extends ProduitFermier {
    public Charcuterie(int quantite, Date dateDePeremption) {
        super(quantite, dateDePeremption);
    }
}
