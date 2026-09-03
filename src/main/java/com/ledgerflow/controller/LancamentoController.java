package com.ledgerflow.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LancamentoController {


    @FXML
    private Button minimize;
    @FXML
    private Button maximize;

    public void initialize(){

    }

    public void Close(){
        Platform.exit();
    }

    public void Minimize(){
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void Maximize(){
        Stage stage = (Stage) maximize.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

}
