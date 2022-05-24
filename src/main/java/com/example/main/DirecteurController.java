package com.example.main;

import database.MyConnection;
import documents.Bilan;
import documents.Plan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import produits.Catalogue;
import produits.ProduitLaitier;
import produits.ProduitStat;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DirecteurController implements Initializable {

    @FXML
    private TextField CA;
//    @FXML
//    private Date date;
    @FXML
    private AnchorPane etablirPlanLayer;
    @FXML
    private AnchorPane editerPlanLayer;
    @FXML
    private AnchorPane consulterBilanLayer;
    @FXML
    private AnchorPane sauvegardeMonsuelleLayer;
    @FXML
    private AnchorPane comparerCaLayer;
    @FXML
    private Button etablirPlanButton;
    @FXML
    private Button editerPlanButton;
    @FXML
    private Button consulterBilanButton;
    @FXML
    private Button sauvegardeMonsuelleButton;
    @FXML
    private Button comparerCaButton;
    @FXML
    private TableView<ProduitStat> table = new TableView<>();
    @FXML
    private TableColumn<ProduitStat,String> produit;
    @FXML
    private TableColumn<ProduitStat,Integer> quantiteTotal;
    @FXML
    private TableColumn<ProduitStat, Double> coutRevienTotal;
    @FXML
    private TableColumn<ProduitStat,Double> gainTotal;
    @FXML
    private Button creer;
    @FXML
    private Label smLabel1;
    @FXML
    private Label smLabel2;
    @FXML
    private Label smLabel3;
    @FXML
    private Label smLabel4;
    @FXML
    private Button comparer;
    @FXML
    private Label message;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void etablirPlanButtonOnAction(ActionEvent event){
        etablirPlanLayer.setOpacity(1);
        editerPlanLayer.setOpacity(0);
        consulterBilanLayer.setOpacity(0);
        sauvegardeMonsuelleLayer.setOpacity(0);
        comparerCaLayer.setOpacity(0);
        etablirPlanLayer.setDisable(false);
        editerPlanLayer.setDisable(true);
        consulterBilanLayer.setDisable(true);
        sauvegardeMonsuelleLayer.setDisable(true);
        comparerCaLayer.setDisable(true);
    }
    @FXML
    public void editerPlanButtonOnAction(ActionEvent event){
        etablirPlanLayer.setOpacity(0);
        editerPlanLayer.setOpacity(1);
        consulterBilanLayer.setOpacity(0);
        sauvegardeMonsuelleLayer.setOpacity(0);
        comparerCaLayer.setOpacity(0);
        etablirPlanLayer.setDisable(true);
        editerPlanLayer.setDisable(false);
        consulterBilanLayer.setDisable(true);
        sauvegardeMonsuelleLayer.setDisable(true);
        comparerCaLayer.setDisable(true);
    }
    @FXML
    public void consulterBilanButtonOnAction(ActionEvent event) throws SQLException {
        etablirPlanLayer.setOpacity(0);
        editerPlanLayer.setOpacity(0);
        consulterBilanLayer.setOpacity(1);
        sauvegardeMonsuelleLayer.setOpacity(0);
        comparerCaLayer.setOpacity(0);
        etablirPlanLayer.setDisable(true);
        editerPlanLayer.setDisable(true);
        consulterBilanLayer.setDisable(false);
        sauvegardeMonsuelleLayer.setDisable(true);
        comparerCaLayer.setDisable(true);
        Catalogue catalogue = new Catalogue(new ArrayList<ProduitLaitier>());
        catalogue.extracted(MyConnection.getConnection());
        Bilan bilan = new Bilan(catalogue);
        produit.setCellValueFactory(new PropertyValueFactory<>("produit.designation"));
        quantiteTotal.setCellValueFactory(new PropertyValueFactory<>("quantiteTotal"));
        gainTotal.setCellValueFactory(new PropertyValueFactory<>("gainTotal"));
        coutRevienTotal.setCellValueFactory(new PropertyValueFactory<>("coutRevienTotal"));
        table.setItems((ObservableList<ProduitStat>) bilan.getProduits());
        table.getColumns().addAll(produit,quantiteTotal,gainTotal,coutRevienTotal);
    }
    @FXML
    public void sauvegardeMonsuelleButtonOnAction(ActionEvent event){
        etablirPlanLayer.setOpacity(0);
        editerPlanLayer.setOpacity(0);
        consulterBilanLayer.setOpacity(0);
        sauvegardeMonsuelleLayer.setOpacity(1);
        comparerCaLayer.setOpacity(0);
        etablirPlanLayer.setDisable(true);
        editerPlanLayer.setDisable(true);
        consulterBilanLayer.setDisable(true);
        sauvegardeMonsuelleLayer.setDisable(false);
        comparerCaLayer.setDisable(true);
    }
    @FXML
    public void creerOnAction(ActionEvent event){
        smLabel1.setOpacity(1);
        smLabel2.setOpacity(1);
        smLabel3.setOpacity(1);
        smLabel4.setOpacity(1);
    }
    @FXML
    public void comparerCaButtonOnAction(ActionEvent event){
        etablirPlanLayer.setOpacity(0);
        editerPlanLayer.setOpacity(0);
        consulterBilanLayer.setOpacity(0);
        sauvegardeMonsuelleLayer.setOpacity(0);
        comparerCaLayer.setOpacity(1);
        etablirPlanLayer.setDisable(true);
        editerPlanLayer.setDisable(true);
        consulterBilanLayer.setDisable(true);
        sauvegardeMonsuelleLayer.setDisable(true);
        comparerCaLayer.setDisable(false);
    }
    @FXML
    public void comparerOnAction(ActionEvent event){
        message.setOpacity(1);
    }
}
