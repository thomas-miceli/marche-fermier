package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProduictionDeFruit;

import java.util.List;

public class Arboriculteur extends Paysan {
    public Arboriculteur(String nom, String prenom, int age, List produits) {
        super(nom, prenom, age);
    }

    @Override
    public void fabriquerProduit(String objetFab)  {
        ProduictionDeFruit produictionDeFruit = new ProduictionDeFruit();
        try {
            this.addProduit(produictionDeFruit.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
