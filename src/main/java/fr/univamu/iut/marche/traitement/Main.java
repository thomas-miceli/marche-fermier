package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Apiculteur;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.Cochon;
import fr.univamu.iut.marche.traitement.produits.Laitage;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 337, 320));
        primaryStage.show();
    }


    public static void main(String[] args) {


    }
}
