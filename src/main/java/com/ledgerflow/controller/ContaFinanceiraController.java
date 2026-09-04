package com.ledgerflow.controller;

import com.ledgerflow.model.ContaFinanceira;
import com.ledgerflow.model.enums.ContaTipo;
import com.ledgerflow.service.ContaFinanceiraService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private TableView<ContaFinanceira> tabelaConta;
    @FXML
    private TableColumn<ContaFinanceira, String> colunaConta;
    @FXML
    private TableColumn<ContaFinanceira, Boolean> colunaAtiva;

    private final ObservableList<ContaFinanceira> listaTabela = FXCollections.observableArrayList();
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
                new PropertyValueFactory<>("nome")
        );
        colunaAtiva.setCellValueFactory(
                new PropertyValueFactory<>("ativo")
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

            if(PopupStatusConta()) {
                ContaFinanceiraService.AtivarConta(contaSelecionado.getId());
                tabelaConta.getSelectionModel().clearSelection();
                listaTabela.setAll(ContaFinanceiraService.ListarContas());
            }

        }else if (contaSelecionado != null && !checkBoxAtiva.isSelected()) {

            if(PopupStatusConta()) {
                ContaFinanceiraService.DesativarConta(contaSelecionado.getId());
                tabelaConta.getSelectionModel().clearSelection();
                listaTabela.setAll(ContaFinanceiraService.ListarContas());
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
        listaTabela.setAll(ContaFinanceiraService.ListarContas());
    }

    public void CriarConta(){
        String nome = nomeBanco.getText().toUpperCase();
        String agencia = agenciaBanco.getText();
        String numero = numeroBanco.getText();
        String valor = valorInicial.getText().replaceAll(",",".");

        ContaTipo tipo = tipoConta.getValue();
        boolean ativo = checkBoxAtiva.isSelected();

        if (nome.isBlank() ||
                agencia.isBlank() ||
                numero.isBlank() ||
                valor.isBlank() ||
                tipo == null) {

            return;
        }

        int agenciaConvertida = Integer.parseInt(agencia);
        int numeroConvertido = Integer.parseInt(numero);
        //BigDecimal valorConvertido = (BigDecimal) valor;

        //ContaFinanceiraService.CriarConta(nome, agenciaConvertida, numeroConvertido, tipo, valorConvertido, ativo);
    }

    public boolean PopupStatusConta(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmar Alteração");
        alert.setHeaderText("Alterar Status");
        alert.setContentText("Você deseja alterar o status da conta?");

        ButtonType buttonTypeOk = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == buttonTypeOk;

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
//Aonde eu tava com a cabeça para começar essa palhaçada?