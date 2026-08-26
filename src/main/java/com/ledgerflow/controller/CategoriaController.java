package com.ledgerflow.controller;

import com.ledgerflow.model.Categoria;
import com.ledgerflow.model.ContaTipo;
import com.ledgerflow.model.TipoLancamento;
import com.ledgerflow.service.CategoriaService;
import com.ledgerflow.service.ContaFinanceiraService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class CategoriaController {

    @FXML
    private TextField nomeCategoria;
    @FXML
    private ComboBox<TipoLancamento> tipoCategoria;
    @FXML
    private Button botaoSalvar;
    @FXML
    private TableView<Categoria>  tabelaCategoria;
    @FXML
    private TableColumn<Categoria, String> colunaNome;
    @FXML
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private TableColumn<TipoLancamento, Integer> colunaTipo;

    private final ObservableList<Categoria> lista = FXCollections.observableArrayList();
    private Categoria categoriaSelecionado;

    public void initialize(){

        tipoCategoria.getItems().setAll(TipoLancamento.values());


        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );
        colunaTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        lista.setAll(CategoriaService.ListarCategorias());

        tabelaCategoria.setItems(lista);

        tabelaCategoria.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    categoriaSelecionado = newValue;

                    if(categoriaSelecionado != null){
                        nomeCategoria.setText(categoriaSelecionado.getNome());
                        tipoCategoria.setValue(categoriaSelecionado.getTipo());

                        nomeCategoria.setDisable(true);
                    }
                });


    }

    public void salvar(){

        if (categoriaSelecionado != null && tipoCategoria.valueProperty().getValue() != categoriaSelecionado.getTipo()){

            if(PopupTipoCategoria()) {

                categoriaSelecionado.setTipo(tipoCategoria.getValue());
                CategoriaService.AtualizarTipoCategoria(categoriaSelecionado);
                tabelaCategoria.getSelectionModel().clearSelection();
                lista.setAll(CategoriaService.ListarCategorias());

            }

        }else{

            if (categoriaSelecionado != null) return;
            criarCategoria();
            limparCampos();
        }
    }


    public void limparCampos() {
        nomeCategoria.clear();
        nomeCategoria.setDisable(false);

        categoriaSelecionado = null;

        tabelaCategoria.getSelectionModel().clearSelection();
        lista.setAll(CategoriaService.ListarCategorias());
    }

    public void criarCategoria(){
        String nome = nomeCategoria.getText().toUpperCase();
        TipoLancamento tipo = tipoCategoria.getValue();

        if (nome.isBlank() || tipo == null) {
            return;
        }

        CategoriaService.CriarCategoria(new Categoria(nome, tipo));
    }

    public boolean PopupTipoCategoria(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmar Alteração");
        alert.setHeaderText("Você deseja alterar o tipo da categoria?");
        alert.setContentText("Alterando o tipo da categoria vai haver mudanças em dados já categorizados");

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
