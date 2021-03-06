package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajoutTraderController extends VBox implements Initializable {
    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private TextField Nom;
    @FXML
    private VBox Participants;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    public void newTrader() throws IOException {
        Trader trader = new Trader(Nom.getCharacters().toString());
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected())
                trader.addClients(Participant.getAllParticipants().get(checkBoxes.indexOf(checkBox)));
        }
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Participant participant : Participant.getAllParticipants()) {
            HBox hBox = new HBox();
            CheckBox checkBox = new CheckBox();
            checkBoxes.add(checkBox);
            String espace = "";
            hBox.getChildren().add(0, new Text(participant.getPrenom() + ' ' + participant.getNom()));
            for (int i = (participant.getPrenom() + ' ' + participant.getNom()).length(); i < 40; ++i) espace += ' ';
            hBox.getChildren().add(1, new Text(espace));
            hBox.getChildren().add(2, checkBox);
            Participants.getChildren().add(hBox);
        }
    }

    public ajoutTraderController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AjoutTrader.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public String getStringOfTextField(TextField textField) {
        return textField.getCharacters().toString();
    }

    public int getIntOfTextField(TextField textField) {
        try {
            return Integer.valueOf(getStringOfTextField(textField));
        } catch (Exception e) {
            return 0;
        }

    }
}
