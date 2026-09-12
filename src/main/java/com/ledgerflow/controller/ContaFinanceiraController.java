package com.ledgerflow.controller;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.PopupWarning;
import com.ledgerflow.model.enums.ContaTipo;
import com.ledgerflow.service.ContaFinanceiraService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.Optional;

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
    private TableColumn<ContaFinanceira, String> colunaConta;
    @FXML
    private TableColumn<ContaFinanceira, Boolean> colunaAtiva;

    private final ObservableList<ContaFinanceira> listaTabela = FXCollections.observableArrayList();
    private ContaFinanceira contaSelecionado;
    private ContaFinanceiraService contaService = new ContaFinanceiraService();


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
                new PropertyValueFactory<>("nome")
        );
        colunaAtiva.setCellValueFactory(
                new PropertyValueFactory<>("ativo")
        );

        listaTabela.setAll(contaService.listarContas());

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

                        nomeBanco.setDisable(true);
                        agenciaBanco.setDisable(true);
                        numeroBanco.setDisable(true);
                        valorInicial.setDisable(true);
                        tipoConta.setDisable(true);
                    }
                });

    }

    public void Salvar(){

        if (contaSelecionado != null && checkBoxAtiva.isSelected()){

            if(PopupWarning.confirmation(
                    "Alterar a Conta",
                    "Você realmente deseja ativar a conta?"
            )) {
                contaService.ativarConta(contaSelecionado);
                tabelaConta.getSelectionModel().clearSelection();
                listaTabela.setAll(contaService.listarContas());
            }

        }else if (contaSelecionado != null && !checkBoxAtiva.isSelected()) {

            if(PopupWarning.confirmation(
                    "Alterar a Conta",
                    "Você realmente deseja desativar a conta?"
            )) {
                contaService.desativarConta(contaSelecionado);
                tabelaConta.getSelectionModel().clearSelection();
                listaTabela.setAll(contaService.listarContas());
            }

        }else{
            CriarConta();
            limparCampos();
        }

    }

    public void limparCampos() {
        nomeBanco.clear();
        agenciaBanco.clear();
        numeroBanco.clear();
        valorInicial.clear();

        checkBoxAtiva.setSelected(false);
        nomeBanco.setDisable(false);
        agenciaBanco.setDisable(false);
        numeroBanco.setDisable(false);
        valorInicial.setDisable(false);
        tipoConta.setDisable(false);

        contaSelecionado = null;

        tabelaConta.getSelectionModel().clearSelection();
        listaTabela.setAll(contaService.listarContas());
    }

    public void CriarConta(){
        String nome = nomeBanco.getText().toUpperCase();
        String agencia = agenciaBanco.getText();
        String numero = numeroBanco.getText();
        String valor = valorInicial.getText();

        ContaTipo tipo = tipoConta.getValue();
        boolean ativo = checkBoxAtiva.isSelected();

        if (
                nome.isBlank() ||
                agencia.isBlank() ||
                numero.isBlank() ||
                valor.isBlank() ||
                tipo == null
        ){
            PopupWarning.warning(
                    "Dados Faltantes",
                    "Para salvar a conta, deve-ser preencher corretamente o formulario"
            );
            return;
        }
        
        BigDecimal valorConvertido =  new BigDecimal(valor);

        contaService.criarConta(
                nome,
                agencia,
                numero,
                tipo,
                valorConvertido
        );
    }

}