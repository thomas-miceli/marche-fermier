package fr.univamu.iut.marche.traitement.acteurs.Paysans;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import static fr.univamu.iut.marche.traitement.Main.*;
/**
 * @author Yann FORNER
 * @author Thomas MICELI
 */
public abstract class Paysan extends Participant {

    public Paysan(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }

    /**
     * Affiche le nom du paysan et le contenu de son stock sur le terminal.
     */
    public void show() {
        System.out.println(ANSI_YELLOW + "(" + this.getClass().getSimpleName() + ") " + ANSI_GREEN + this.prenom + " " + this.nom + " - " + this.age + " ans - " + this.solde + "€ :\n" +
                ANSI_CYAN + "Produits en stock : " + ANSI_RESET);

        for (ProduitFermier produit : this.getStock()) {
            System.out.println(produit.getClass().getSimpleName() + " | Quantité: " + produit.getQuantite() + " | Péremption: " + produit.getDateToString());
        }
        System.out.println("\n");
    }

    public abstract ProduitFermier fabriquerProduit(Produits objetFab, int quantite);
}
