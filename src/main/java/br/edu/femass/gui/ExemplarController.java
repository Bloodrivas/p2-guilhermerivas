package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
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

public class ExemplarController implements Initializable {
    
    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnIncluir;

    @FXML
    private ComboBox<Autor> CboAutor;

    @FXML
    private TableColumn<Exemplar, LocalDate> ColData;

    @FXML
    private TableColumn<Exemplar, Long> colID;

    @FXML
    private TableColumn<Livro, String> colTitulo;

    @FXML
    private TableView<Exemplar> tabela = new TableView<Exemplar>();

    private boolean incluindo;

    private Livro livro;
    private Exemplar exemplar;
    private Autor autor;

    DaoExemplar daoexemplar = new DaoExemplar();
    DaoLivro daolivro = new DaoLivro();
    DaoAutor daoautor = new DaoAutor();
    
    @FXML
    void excluir_click(ActionEvent event) {
        daoexemplar.apagar(exemplar);
        preencherTabela();
    }

    @FXML
    void gravar_click(ActionEvent event) {
        autor.setAutor(CboAutor.getSelectionModel().getSelectedItem());
        
        if (incluindo) {
            daoexemplar.inserir(exemplar);
        } else {
            daoexemplar.alterar(exemplar);
        }

        preencherTabela();
        editar(false);
    }

    @FXML
    void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();

        incluindo = true;

        livro = new Livro();
        exemplar = new Exemplar();
        CboAutor.requestFocus();
    }

    private void editar(boolean habilitar) {
        tabela.setDisable(habilitar);
        CboAutor.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {

        this.exemplar = tabela.getSelectionModel().getSelectedItem();

        if (exemplar==null) return;

    }

    private void preencherCombo() {
        List<Autor> autores = daoautor.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        CboAutor.setItems(data);
    }

    private void preencherTabela() { 
        List<Exemplar> exemplares = daoexemplar.buscarTodos();

        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tabela.setItems(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}