package com.ledgerflow.controller;

import com.ledgerflow.model.ContaFinanceira;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardContaController {

    @FXML
    private Label nomeConta;
    @FXML
    private Label saldoConta;
    @FXML
    private Label despesasConta;
    @FXML
    private Label receitasConta;

    public void setConta(ContaFinanceira c){
        nomeConta.setText(c.getNome());
        saldoConta.setText(String.valueOf(c.getSaldo()));
    }
}
