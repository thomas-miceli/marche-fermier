package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.*;
import fr.univamu.iut.marche.traitement.produits.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ajoutPaysanController extends VBox implements Initializable{
    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Age;
    @FXML
    private Text alertText;
    @FXML
    private HBox Type;
    @FXML
    private VBox listeProduit;

    private ArrayList<String> listesProduits = new ArrayList<>();
    private ArrayList<TextField> choixQuantiteProduits = new ArrayList<>();
    private String selectedType = "";
    @FXML
    public void newPaysan() throws IOException{
        if(!Nom.getCharacters().toString().isEmpty() && !Prenom.getCharacters().toString().isEmpty() && !selectedType.isEmpty()){
            Paysan paysan;
            switch (selectedType) {
                case "Apiculteur":
                    paysan = new Apiculteur(getStringOfTextField(Nom), getStringOfTextField(Prenom), getIntOfTextField(Age));
                    break;
                case "Orticulteur":
                    paysan = new Orticulteur(getStringOfTextField(Nom), getStringOfTextField(Prenom), getIntOfTextField(Age));
                    break;
                case "Producteur de viande":
                    paysan = new ProducteurDeViande(getStringOfTextField(Nom), getStringOfTextField(Prenom), getIntOfTextField(Age));
                    break;
                case "Producteur Laitier":
                    paysan = new ProducteurLaitier(getStringOfTextField(Nom), getStringOfTextField(Prenom), getIntOfTextField(Age));
                    break;
                default:
                    paysan = null;
            }
            for (String string : listesProduits){
                System.out.println(choixQuantiteProduits.get(listesProduits.indexOf(string)).getCharacters().toString());
                ajouterProdtoPaysan(paysan, string);
            }
            Random Solde = new Random();
            paysan.setSolde(Solde.nextInt(400)+200);
            contentVBox.getChildren().clear();
            contentVBox.getChildren().addAll(new catalogController());
        }else{
            alertText.setText("Il manque des arguments");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Participant.Produits produit : Participant.Produits.values()){
            HBox hBox = new HBox();
            TextField textField = new TextField("0");
            String produitStringtemp = produit.toString().substring(0,1);
            String produitString = produitStringtemp + produit.toString().substring(1).toLowerCase();
            hBox.getChildren().add(0, new Text(produitString));
            listesProduits.add(produitString);
            choixQuantiteProduits.add(textField);
            String espace = "";
            for (int i = produitString.length(); i<40; ++i) espace+=' ';
            hBox.getChildren().add(1, new Text(espace));
            hBox.getChildren().add(2, textField);
            listeProduit.getChildren().add(hBox);
        }
        ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList("Apiculteur", "Orticulteur", "Producteur de viande", "Producteur Laitier"));
        cb.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            selectedType = cb.getItems().get((Integer)newValue);
        });
        Type.getChildren().add(cb);
    }
    public ajoutPaysanController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AjoutPaysan.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public String getStringOfTextField(TextField textField){
        return textField.getCharacters().toString();
    }
    public int getIntOfTextField(TextField textField){
        try{
            return Integer.valueOf(getStringOfTextField(textField));
        }catch (Exception e){
            return 0;
        }

    }
    private void ajouterProdtoPaysan(Paysan p ,  String s){
        ProduitFermier pr = creerProduit(s);
        if(pr.getQuantite()>0){
            p.addProduit(pr);
        }
    }
    private ProduitFermier creerProduit(String s){
        int i = Integer.valueOf(choixQuantiteProduits.get(listesProduits.indexOf(s)).getCharacters().toString());
        switch (s){
            case "Cochon":
                return new Cochon(i, calcDatePeremption());
            case "Fromage":
                return new Fromage(i, calcDatePeremption());
            case "Lait":
                return new Lait(i, calcDatePeremption());
            case "Miel":
                return new Miel(i, calcDatePeremption());
            case "Orange":
                return new Orange(i, calcDatePeremption());
            case "Pomme":
                return new Pomme(i, calcDatePeremption());
            case "Vache":
                return new Vache(i, calcDatePeremption());
                default:
                return null;
        }
    }
    private Date calcDatePeremption(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return (c.getTime());
    }
}
