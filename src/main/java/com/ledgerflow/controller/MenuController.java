package com.ledgerflow.controller;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.service.ContaFinanceiraService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;


public class MenuController {

    @FXML
    private HBox contaContainer;

    private final ContaFinanceiraService contaService = new ContaFinanceiraService();

    public void ContasConteiner(List<ContaFinanceira> contas) throws IOException {

        contaContainer.getChildren().clear();

        for (ContaFinanceira conta : contas){
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContaCard.fxml"));

            if(!conta.isAtivo()) continue;
            AnchorPane card = loader.load();

            CardContaController controller = loader.getController();
            controller.setConta(conta);

            contaContainer.getChildren().add(card);

        }
    }

    public void initialize() throws IOException {
        ContasConteiner(contaService.listarContas());
    }

}