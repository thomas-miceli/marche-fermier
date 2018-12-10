package fr.univamu.iut.marche.affichage;

import fr.univ_amu.iut.models.Serveur;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
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
import java.util.List;
import java.util.ResourceBundle;

public class catalogController extends VBox implements Initializable {

    @FXML
    private VBox root;

    private Scene scene;

    @FXML
    private BorderPane content;

    @FXML
    private ListView listeVentes = new ListView();

    private static int selectedVenteId;

    private List<Produit> tableauProduit;
    private ObservableList<String> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableauProduit = Marche;
        for (int i = 0; i<tableauServeur.size(); ++i){
            if (tableauServeur.get(i).isUserInServer(popupController.getCurrentUser()))
                data.add(tableauServeur.get(i).getServerName());
        }
        serveurListe.setItems(data);
        serveurListe.getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                    try {
                        root.getChildren().clear();
                        selectedServerId = Serveur.getIdServeurByName(newValue);
                        root.getChildren().addAll(new serverSendMsgController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }

    public static int getSelectedServerId() {
        return selectedServerId;
    }

    public homeController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr.univ_amu.iut/views/home.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
}