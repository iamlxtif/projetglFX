package com.example.main;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import produits.ProduitLaitier;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentVenteController implements Initializable {
    @FXML
    private Button gererCatalogueButton;
    @FXML
    private Button rechercherButton;
    @FXML
    private Button gererClientsButton;
    @FXML
    private Button gererCommandesButton;
    @FXML
    private Button historiqueButton;
    @FXML
    private AnchorPane gererCatalogueLayer;
    @FXML
    private AnchorPane rechercherLayer;
    @FXML
    private AnchorPane gererClientsLayer;
    @FXML
    private AnchorPane gererCommandesLayer;
    @FXML
    private AnchorPane historiqueLayer;
    @FXML
    private TextField ajoutRef;
    @FXML
    private TextField ajoutDes;
    @FXML
    private TextField ajoutCr;
    @FXML
    private TextField ajoutPrix;
    @FXML
    private TextField ajoutVn;
    @FXML
    private TextField ajoutPoids;
    @FXML
    private DatePicker ajoutProd;
    @FXML
    private DatePicker ajoutPer;
    @FXML
    private TextArea ajoutIng;
    @FXML
    private ChoiceBox<String> ajoutType;
    @FXML
    private Button ajouter;
    @FXML
    private Label ajoutMsg;
    @FXML
    private TextField modRef;
    @FXML
    private TextField modDes;
    @FXML
    private TextField modCr;
    @FXML
    private TextField modPrix;
    @FXML
    private TextField modVn;
    @FXML
    private TextField modPoids;
    @FXML
    private DatePicker modProd;
    @FXML
    private DatePicker modPer;
    @FXML
    private TextArea modIng;
    @FXML
    private ChoiceBox<String> modType;
    @FXML
    private Button modifier;
    @FXML
    private Label modMsg;
    @FXML
    private TextField suppRef;
    @FXML
    private Button supprimer;
    @FXML
    private Label suppMsg;
    @FXML
    private ChoiceBox<String> mode;
    @FXML
    private TextField keyword;
    @FXML
    private TableView<ProduitLaitier> produitTable = new TableView<>();
    @FXML
    private TableColumn<ProduitLaitier,String> produitDes;
    @FXML
    private TableColumn<ProduitLaitier,Double> coutRevien;
    @FXML
    TableColumn<ProduitLaitier,Double> prix;
    @FXML
    private TableColumn<ProduitLaitier,String> vn;
    @FXML
    private TableColumn<ProduitLaitier,DatePicker> dProd;
    @FXML
    private TableColumn<ProduitLaitier,DatePicker> dPer;
    @FXML
    private TableColumn<ProduitLaitier,Integer> poids;
    @FXML
    private Button rechercher;

    @FXML
    private TextField ajoutNumCl;
    @FXML
    private TextField ajoutClDes;
    @FXML
    private TextField ajoutClEmail;
    @FXML
    private TextField ajoutClAdresse;
    @FXML
    private ChoiceBox<Boolean> ajoutIsRevendeur;
    @FXML
    private Button ajouterCl;
    @FXML
    private Label ajoutMsgCl;
    @FXML
    private TextField modNumCl;
    @FXML
    private TextField modClDes;
    @FXML
    private TextField modClEmail;
    @FXML
    private TextField modClAdresse;
    @FXML
    private ChoiceBox<Boolean> modIsRevendeur;
    @FXML
    private Button modifierCl;
    @FXML
    private Label modMsgCl;
    @FXML
    private TextField suppNumCl;
    @FXML
    private Button supprimerCl;
    @FXML
    private Label suppMsgCl;
    @FXML
    private Button cmdAjout;

    @FXML
    private DatePicker cmdDate;

    @FXML
    private TextField cmdNumCl;

    @FXML
    private Button cmdPasser;

    @FXML
    private TextField cmdQt;

    @FXML
    private TextField cmdRef;

    @FXML
    private TableView<ProduitLaitier> cmdTable;
    @FXML
    private TableColumn<?, ?> cmdDes;
    @FXML
    private TableColumn<?, ?> cmdQtCol;



    private final String[] type = {"lait","dérivé lait"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modType.getItems().addAll(type);
        ajoutType.getItems().addAll(type);
        ajoutType.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    ajoutIng.setDisable(!(new_val.intValue() == 1));
                });
        modType.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    modIng.setDisable(!(new_val.intValue() == 1));
                });
        ajoutIsRevendeur.getItems().addAll(true,false);
        modIsRevendeur.getItems().addAll(true,false);
    }
    @FXML
    public void gererCatalogueButtonOnAction(ActionEvent event){
        gererCatalogueLayer.setDisable(false);
        gererCatalogueLayer.setOpacity(1);
        rechercherLayer.setOpacity(0);
        gererClientsLayer.setOpacity(0);
        gererCommandesLayer.setOpacity(0);
        historiqueLayer.setOpacity(0);
        rechercherLayer.setDisable(true);
        gererClientsLayer.setDisable(true);
        gererCommandesLayer.setDisable(true);
        historiqueLayer.setDisable(true);
    }

    @FXML
    public void rechercherButtonOnAction(ActionEvent event){
        rechercherLayer.setDisable(false);
        gererCatalogueLayer.setOpacity(0);
        rechercherLayer.setOpacity(1);
        gererClientsLayer.setOpacity(0);
        gererCommandesLayer.setOpacity(0);
        historiqueLayer.setOpacity(0);
        gererCatalogueLayer.setDisable(true);
        gererClientsLayer.setDisable(true);
        gererCommandesLayer.setDisable(true);
        historiqueLayer.setDisable(true);
    }
    @FXML
    public void gererClientsButtonOnAction(ActionEvent event){
        gererClientsLayer.setDisable(false);
        gererCatalogueLayer.setOpacity(0);
        rechercherLayer.setOpacity(0);
        gererClientsLayer.setOpacity(1);
        gererCommandesLayer.setOpacity(0);
        historiqueLayer.setOpacity(0);
        gererCatalogueLayer.setDisable(true);
        rechercherLayer.setDisable(true);
        gererClientsLayer.setDisable(false);
        gererCommandesLayer.setDisable(true);
        historiqueLayer.setDisable(true);
    }
    @FXML
    public void gererCommandesButtonOnAction(ActionEvent event){
        gererCommandesLayer.setDisable(false);
        gererCatalogueLayer.setOpacity(0);
        rechercherLayer.setOpacity(0);
        gererClientsLayer.setOpacity(0);
        gererCommandesLayer.setOpacity(1);
        historiqueLayer.setOpacity(0);
        gererCatalogueLayer.setDisable(true);
        rechercherLayer.setDisable(true);
        gererClientsLayer.setDisable(true);
        historiqueLayer.setDisable(true);
    }
    @FXML
    public void historiqueButtonOnAction(ActionEvent event){
        historiqueLayer.setDisable(false);
        gererCatalogueLayer.setOpacity(0);
        rechercherLayer.setOpacity(0);
        gererClientsLayer.setOpacity(0);
        gererCommandesLayer.setOpacity(0);
        historiqueLayer.setOpacity(1);
        gererCatalogueLayer.setDisable(true);
        rechercherLayer.setDisable(true);
        gererClientsLayer.setDisable(true);
        gererCommandesLayer.setDisable(true);
    }
}
