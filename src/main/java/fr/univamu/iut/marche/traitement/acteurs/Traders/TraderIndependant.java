//package fr.univamu.iut.marche.traitement.acteurs.Traders;
//
//import fr.univamu.iut.marche.traitement.acteurs.Participant;
//import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
//
//import static fr.univamu.iut.marche.traitement.Seeding.compilerProduits;
//
///**
// * @author Dylan MARCH
// * @author Téo MARTIN
// */
//public class TraderIndependant extends Trader {
//
//    private boolean connexion = true;
//
//    public TraderIndependant(String nom, String prenom, int age) {
//        super(nom, prenom, age);
//    }
//
//    public boolean hasConnexion() {
//        return connexion;
//    }
//
//    public void setConnexion(boolean connexion) {
//        this.connexion = connexion;
//    }
//
//    public void vendreProduit(ProduitFermier produitAVendre) {
//        ProduitFermier aVendre = null;
//        if (connexion) {
//            for (ProduitFermier produ : produitsEnStock) {
//                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAVendre.getClass().getSimpleName())) {
//                    aVendre = produ;
//                    break;
//                }
//            }
//            this.getProduitsAVendre().remove(aVendre);
//        }
//        System.out.println("Pas connecté, impossible de vendre le produit");
//    }
//
//    public void acheterProduit(Participant vendeur, ProduitFermier produitAchete) {
//        ProduitFermier aAcheter = null;
//        if (connexion) {
//            for (ProduitFermier produ : produitsEnStock) {
//                if (produ.getClass().getSimpleName().toUpperCase().equals(produitAchete.getClass().getSimpleName())) {
//                    aAcheter = produ;
//                    break;
//                }
//            }
//            this.getProduits().add(aAcheter);
//            this.setProduits(compilerProduits(this.getProduits()));
//            vendeur.getProduitsAVendre().remove(aAcheter);
//        }
//        System.out.println("Pas connecté, impossible d'acheter le produit");
//    }
//}
//
