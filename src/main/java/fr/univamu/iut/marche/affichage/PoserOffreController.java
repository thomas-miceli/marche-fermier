package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PoserOffreController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;
    @FXML
    private HBox choix;


    private ArrayList<ProduitFermier> produitsParticipants = new ArrayList<>();
    private ArrayList<String> produitParticipantsString = new ArrayList<>();

    private Button choixButton;
    private Button choixButton2;
    @FXML
    public void setViewToListeparticipants() throws IOException{
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    private static ProduitFermier selectedProduit;
    private ObservableList<String> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public PoserOffreController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PoserOffre.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public void setViewToListeProduction() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new productionController());
    }
    public void setViewToAddParticipant() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new addParticipantController());
    }
    public static ProduitFermier getSelectedProduit(){
        return selectedProduit;
    }
}