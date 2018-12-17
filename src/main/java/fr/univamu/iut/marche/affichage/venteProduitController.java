package fr.univamu.iut.marche.affichage;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class venteProduitController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;



    @FXML
    public void setViewToCatalog() throws IOException{
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new catalogController());
    }
    @FXML
    private Button buttonConfig;

    private static String produitvente;

    private ObservableList<String> data = FXCollections.observableArrayList();


    public venteProduitController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/VenteProduit.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
