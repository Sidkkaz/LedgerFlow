package com.ledgerflow.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Navegador;

public class MainController {

    @FXML
    private StackPane content;

    private Navegador nav;

    @FXML
    public void initialize(){
        nav = new Navegador(content);
        nav.AlterarView("Menu");
    }

    public void AbrirMenu(){
        nav = new Navegador(content);
        nav.AlterarView("Menu");
    }

    public void AbrirConta(){
        nav = new Navegador(content);
        nav.AlterarView("ContaFinanceiraView");
    }

    public void AbrirCategoria(){
        nav = new Navegador(content);
        nav.AlterarView("CategoriaView");
    }

    public void AbrirLancamento(){
        nav = new Navegador(content);
        nav.AlterarView("Lancamento");
    }

    public void AbrirRelatorio(){
        nav = new Navegador(content);
        nav.AlterarView("Relatorio");
    }

    public void AbrirConfiguracoes(){
        nav = new Navegador(content);
        nav.AlterarView("Configuracoes");
    }

    public void Sair(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/Login.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }


}
