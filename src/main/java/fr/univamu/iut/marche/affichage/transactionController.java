package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Offre;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
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

public class transactionController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;

    @FXML
    private ListView listeTransaction = new ListView();
    private ObservableList<String> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Participant.getAllParticipants());
        for (Offre offre: Marche.getOffresMarche()) {
            data.add(offre.toString());
        }
        listeTransaction.setItems(data);
    }



    public transactionController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Transaction.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
}
