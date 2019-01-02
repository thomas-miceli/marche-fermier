package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class listeParticipantController extends VBox implements Initializable {

    @FXML
    private VBox root;

    private Scene scene;

    @FXML
    private BorderPane content;

    @FXML
    private ListView listeParticipant = new ListView();

    private static String selectedParticipant;
    private static Map<String, String> textaffichetotextdescription = new HashMap<>();
    private ObservableList<String> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Participant.getAllParticipants());
        for (Participant participant:Participant.getAllParticipants()) {
            textaffichetotextdescription.put(participant.getPrenom() +"  "+ participant.getNom(), String.valueOf(participant.getId()));
            data.add(participant.getPrenom()+"  "+participant.getNom());
        }
        listeParticipant.setItems(data);
        listeParticipant.getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                    try {
                        root.getChildren().clear();
                        selectedParticipant = newValue;
                        root.getChildren().addAll(new participantController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }



    public listeParticipantController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ListeParticipant.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
    public static String getSelectedParticipant(){
        return textaffichetotextdescription.get(selectedParticipant);
    }
}
