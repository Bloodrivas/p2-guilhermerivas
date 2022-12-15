package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExemplarController implements Initializable {
    
    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnIncluir;

    @FXML
    private ComboBox<Livro> CboLivro;

    @FXML
    private TableColumn<Exemplar, LocalDate> ColData = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Livro, String> colTitulo = new TableColumn<>();

    @FXML
    private TableView<Exemplar> tabela = new TableView<Exemplar>();

    private boolean incluindo;

    private Livro livro;
    private Exemplar exemplar;

    DaoExemplar daoexemplar = new DaoExemplar();
    DaoLivro daolivro = new DaoLivro();
   
    
    @FXML
    void excluir_click(ActionEvent event) {
        daoexemplar.apagar(exemplar);
        preencherTabela();
    }

    @FXML
    void gravar_click(ActionEvent event) {
        exemplar.setLivro(CboLivro.getSelectionModel().getSelectedItem());
        livro = CboLivro.getSelectionModel().getSelectedItem();
        exemplar.setTitulo(livro.getTitulo());

        if (incluindo) {
            daoexemplar.inserir(exemplar);
        } else {
            daoexemplar.alterar(exemplar);
        }

        preencherCombo();
        preencherTabela();
        editar(false);
    }

    @FXML
    void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        preencherTabela();

        incluindo = true;

        exemplar = new Exemplar();
        CboLivro.requestFocus();
    }

    private void editar(boolean habilitar) {
        tabela.setDisable(habilitar);
        CboLivro.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {

        this.exemplar = tabela.getSelectionModel().getSelectedItem();

        if (exemplar==null) return;

    }

    private void preencherCombo() {
        List<Livro> livros = daolivro.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        CboLivro.setItems(data);
    }

    private void preencherTabela() { 
        List<Exemplar> exemplares = daoexemplar.buscarTodosPorId();

        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tabela.setItems(data);
        tabela.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Exemplar,Long>("Id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro,String>("livro"));
        ColData.setCellValueFactory(new PropertyValueFactory<Exemplar,LocalDate>("dataAquisicao"));
        editar(false);
        preencherCombo();
        preencherTabela();
    }    
}