package com.ledgerflow.controller;

import com.ledgerflow.model.ContaFinanceira;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class MenuController {

    @FXML
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private HBox contaContainer;

    public void ContasConteiner(List<ContaFinanceira> contas) throws IOException {

        contaContainer.getChildren().clear();

        for (ContaFinanceira conta : contas){
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContaCard.fxml"));

            AnchorPane card = loader.load();

            CardContaController controller = loader.getController();
            controller.setConta(conta);

            contaContainer.getChildren().add(card);

        }
    }

    public void Close() {
        Platform.exit();
    }

    public void Minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void Maximize() {
        Stage stage = (Stage) maximize.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }
}