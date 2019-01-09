package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.Paysan;
import fr.univamu.iut.marche.traitement.produits.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class productionController extends VBox implements Initializable  {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;
    @FXML
    private HBox productionBox;

    private String selectedType ="";
    private TextField textField = new TextField("0");
    private Button button = new Button("Valider");

    @FXML
    public void setViewToParticipant() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new participantController());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productionBox.getChildren().add(0,new Text("Produire : "));
        System.out.println(listeParticipantController.getSelectedParticipant().getClass().getSimpleName());
        ChoiceBox<String> cb = null;
        switch (listeParticipantController.getSelectedParticipant().getClass().getSimpleName()){
            case "Apiculteur":
                cb = new ChoiceBox(FXCollections.observableArrayList("Miel"));
                productionBox.getChildren().add(1, textField);
                productionBox.getChildren().add(2, button);
                break;
            case "Orticulteur":
                cb = new ChoiceBox(FXCollections.observableArrayList("Orange", "Pomme"));
                productionBox.getChildren().add(1, textField);
                productionBox.getChildren().add(2, button);
                break;
            case "ProducteurDeViande":
                cb = new ChoiceBox(FXCollections.observableArrayList("Cochon", "Vache"));
                productionBox.getChildren().add(1, textField);
                productionBox.getChildren().add(2, button);
                break;
            case "ProducteurLaitier":
                cb = new ChoiceBox(FXCollections.observableArrayList("Fromage", "Lait"));
                productionBox.getChildren().add(1, textField);
                productionBox.getChildren().add(2, button);
                break;
            default:
                cb = new ChoiceBox<>(FXCollections.observableArrayList("Ne peut pas produire"));
                break;
        }
        ChoiceBox<String> finalCb = cb;
        cb.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            selectedType = finalCb.getItems().get((Integer)newValue);
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textField.getCharacters().toString().isEmpty()) {
                    try {
                        setViewToParticipant();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Paysan participantcurrent = (Paysan) listeParticipantController.getSelectedParticipant();
                    participantcurrent.fabriquerProduit(creerProduit(finalCb.getValue()), Integer.valueOf(textField.getCharacters().toString()));
                    contentVBox.getChildren().clear();
                    try {
                        contentVBox.getChildren().addAll(new participantController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        productionBox.getChildren().add(1,cb);

    }
    public productionController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Production.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
    private Participant.Produits creerProduit(String s){

        switch (s){
            case "Cochon":
                return Participant.Produits.COCHON;
            case "Fromage":
                return Participant.Produits.FROMAGE;
            case "Lait":
                return Participant.Produits.LAIT;
            case "Miel":
                return Participant.Produits.MIEL;
            case "Orange":
                return Participant.Produits.ORANGE;
            case "Pomme":
                return Participant.Produits.POMME;
            case "Vache":
                return Participant.Produits.VACHE;
            default:
                return null;
        }
    }
}
