package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AgentCommercialController {
    @FXML
    private Button attribuerPrixButton;
    @FXML
    private AnchorPane attribuerPrixLayer;

    @FXML
    public void attribuerPrixButtonOnAction(ActionEvent event){
        attribuerPrixLayer.setOpacity(1);
        attribuerPrixLayer.setDisable(false);
    }
}
