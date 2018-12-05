package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.ProduictionDeFruit;

import java.util.List;

public class Orticulteur extends Paysan {
    public Orticulteur(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    public void fabriquerProduit(String objetFab) {
        ProduictionDeFruit produictionDeFruit= new ProduictionDeFruit();
        try {
            this.addProduit(produictionDeFruit.fabriquer(objetFab,this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
