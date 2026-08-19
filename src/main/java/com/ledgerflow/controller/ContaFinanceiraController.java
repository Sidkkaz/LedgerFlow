package com.ledgerflow.controller;

import com.ledgerflow.model.ContaTipo;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ContaFinanceiraController {

    @FXML
    private TextField nomeBanco;
    @FXML
    private TextField agenciaBanco;
    @FXML
    private TextField numeroBanco;
    @FXML
    private TextField valorInicial;
    @FXML
    private ComboBox<ContaTipo> tipoConta;


    @FXML
    public void initialize(){

        nomeBanco.setPromptText("Nome do Banco");
        agenciaBanco.setPromptText("Agencia");
        numeroBanco.setPromptText("Numero do conta");
        valorInicial.setPromptText("Valor Inicial");

        tipoConta.getItems().setAll(ContaTipo.values());



    }
}
