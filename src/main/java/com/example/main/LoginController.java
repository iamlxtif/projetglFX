package com.example.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import javafx.event.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button AnnulerButton;
    @FXML
    private Button connecterButton;
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView brandImageView;
    @FXML
    private ImageView userImageView;
    @FXML
    private TextField nomUtilisateur;
    @FXML
    private PasswordField motDePasse;
    @FXML
    private Button seConnecter;
    @FXML
    private Button creerCompte;
    @FXML
    private AnchorPane inscriptionLayer;
    @FXML
    private AnchorPane creerCompteLayer;
    @FXML
    private ChoiceBox<String> cb;
    private String[] fonction = {"directeur","agent commercial","agent de vente"};

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize (URL url, ResourceBundle rb){
        File brandFile = new File("imgs/dairy-products.png");
        Image brandImage = new Image(brandFile.toURI().toString());
        brandImageView.setImage(brandImage);
        File userFile = new File("imgs/user-free-icon-font.png");
        Image userImage = new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);
        cb.getItems().addAll(fonction);
    }
    @FXML
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) AnnulerButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void connecterButtonOnAction(ActionEvent event) throws IOException {
        if (!nomUtilisateur.getText().isBlank() && !motDePasse.getText().isBlank()){
            String fonction = "agent de vente";
            if(fonction=="directeur"){
                root = FXMLLoader.load(getClass().getResource("directeur.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (fonction=="agent commercial") {
                root = FXMLLoader.load(getClass().getResource("agentCommercial.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (fonction=="agent de vente") {
                root = FXMLLoader.load(getClass().getResource("agentVente.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else{
            messageLabel.setText("entrez votre informations !");
            messageLabel.setOpacity(1);
        }
    }
    @FXML
    public void seConnecterOnAction(ActionEvent event){
        inscriptionLayer.setOpacity(1);
        inscriptionLayer.setDisable(false);
        creerCompteLayer.setOpacity(0);
        creerCompteLayer.setDisable(true);
    }
    @FXML
    public void creerCompteOnAction(ActionEvent event){
        inscriptionLayer.setOpacity(0);
        inscriptionLayer.setDisable(true);
        creerCompteLayer.setOpacity(1);
        creerCompteLayer.setDisable(false);
    }
}