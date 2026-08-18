package com.ledgerflow.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import main.Navegador;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane content;

    private final Navegador nav = new Navegador(content);

    @FXML
    public void initialize(){
        nav.AlterarView("Menu");
    }

    public void AbrirMenu(){
        nav.AlterarView("Menu");
    }

    public void AbrirConta(){
        nav.AlterarView("ContaFinanceiraView");
    }
}
