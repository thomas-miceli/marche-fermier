package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Pierre LEJEUNE
 */
public class homeController implements Initializable {

    @FXML
    private VBox contentVBox;

    @FXML
    private Text soldeFxmlUser;

    @FXML
    private Text notification;

    public void setStageAndSetupListeners(Stage stage) {
        stage.setOnCloseRequest(event -> Platform.exit());
    }

    @FXML
    public void setSceneToCatalogView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new catalogController());
    }

    @FXML
    public void setNotification(String s) {
        notification.setText(s);
        setSoldeFxmlUser();
    }

    @FXML
    public void setSceneToTransactionView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new transactionController());
    }

    @FXML
    public void setSceneToCotationView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new cotationController());
    }

    @FXML
    public void setSceneToParticipantsView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    @FXML
    public void setSceneToAjoutParticipant() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new ajoutParticipantController());
    }

    @FXML
    public void setSceneToAjoutPaysan() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new ajoutPaysanController());
    }

    @FXML
    public void setSceneToOffreView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeOffresController());
    }

    @FXML
    public void setSceneToAjoutTrader() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new ajoutTraderController());
    }

    @FXML
    public void setSceneToProposerOffreView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new ProposerOffreController());
    }

    @FXML
    public void actionQuitter() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Voulez-vous quitter ?", ButtonType.CLOSE, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.CLOSE) {
            Platform.exit();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Marche.setHomeController(this);
        achatProduitController.setHomeController(this);
        soldeFxmlUser.setText("Solde : " + Seeding.getFxmlUser().getSolde());
        contentVBox.getChildren().clear();
        try {
            contentVBox.getChildren().addAll(new catalogController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setSoldeFxmlUser() {
        soldeFxmlUser.setText("Solde : " + Seeding.getFxmlUser().getSolde());
    }
}
