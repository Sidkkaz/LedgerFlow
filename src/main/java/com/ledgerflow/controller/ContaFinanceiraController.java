package com.ledgerflow.controller;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.ContaTipo;
import com.ledgerflow.service.ContaFinanceiraService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private CheckBox checkBoxAtiva;
    @FXML
    private TableView<ContaFinanceira> tabelaConta;
    @FXML
    private TableColumn<ContaFinanceira, ContaFinanceira> colunaConta;
    @FXML
    private TableColumn<ContaFinanceira, Boolean> colunaAtiva;

    private static final ObservableList<ContaTipo> listaTabela = FXCollections.observableArrayList();
    private ContaFinanceira contaSelecionado;



    @FXML
    public void initialize(){

        nomeBanco.setPromptText("Nome do Banco");
        agenciaBanco.setPromptText("Agencia");
        numeroBanco.setPromptText("Numero do conta");
        valorInicial.setPromptText("Valor Inicial");

        tipoConta.getItems().setAll(ContaTipo.values());

        checkBoxAtiva.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                checkBoxAtiva.setText("A conta está ativa");
            }else{
                checkBoxAtiva.setText("A conta não está ativa");
            }
        });

        colunaConta.setCellValueFactory(
                new PropertyValueFactory<>("Conta")
        );
        colunaAtiva.setCellValueFactory(
                new PropertyValueFactory<>("Ativa?")
        );
        listaTabela.setAll(ContaFinanceiraService.ListarContas());

        tabelaConta.setItems(listaTabela);

        tabelaConta.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    contaSelecionado = newValue;

                    if(contaSelecionado != null){
                        nomeBanco.setText(contaSelecionado.getNome());
                        agenciaBanco.setText(String.valueOf(contaSelecionado.getAgencia()));
                        numeroBanco.setText(String.valueOf(contaSelecionado.getNumero()));
                        valorInicial.setText(String.valueOf(contaSelecionado.getSaldo()));
                        tipoConta.setValue(contaSelecionado.getTipo());
                        checkBoxAtiva.setSelected(contaSelecionado.isAtivo());
                    }
                });


    }

    public void Salvar(){

    }
}
