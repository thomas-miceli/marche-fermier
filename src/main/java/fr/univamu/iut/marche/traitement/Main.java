package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.*;
import fr.univamu.iut.marche.traitement.produits.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 337, 320));
        primaryStage.show();
    }


    public static void main(String[] args) {

        Apiculteur p1 = new Apiculteur("Claude", "Jean", 50);
        ProducteurDeViande p2 = new ProducteurDeViande("Claudette", "Jean", 45);
        Orticulteur p3 = new Orticulteur("Claudinette", "Jean", 40);
        ProducteurLaitier p4 = new ProducteurLaitier("Claudasse", "Jean", 45);


        p1.fabriquerProduit(Apiculteur.ProduitsFabricables.MIEL);

        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.COCHON);
        p2.fabriquerProduit(ProducteurDeViande.ProduitsFabricables.VACHE);

        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.POMME);
        p3.fabriquerProduit(Orticulteur.ProduitsFabricables.ORANGE);

        p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.FROMAGE);
        p4.fabriquerProduit(ProducteurLaitier.ProduitsFabricables.LAIT);

        p1.toString();
        p2.toString();
        p3.toString();
        p4.toString();


    }
}
