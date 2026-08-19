package com.ledgerflow.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import main.Navegador;

import java.io.IOException;

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

    public void Sair(){
        Platform.exit();
    }
}
