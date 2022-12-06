package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AutorController implements Initializable {

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private TableView<Autor> tabela = new TableView<Autor>();
    
    @FXML
    private TableColumn<Autor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colSobrenome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNacionalidade = new TableColumn<>();

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtSobrenome;

    @FXML
    private TextField TxtNacionalidade;

    private Autor autor;

    private DaoAutor daoautor = new DaoAutor();

    private boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event){
	    autor.setNome(TxtNome.getText());
        autor.setSobrenome(TxtSobrenome.getText());
        autor.setNacionalidade(TxtNacionalidade.getText());
        
		
	    if(incluindo){
		    daoautor.inserir(autor);
	    } else{
		    daoautor.alterar(autor);
	    }
	
	    preencherTabela();
	    editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event){
	    editar(true);
	
	    incluindo = true;
	    autor = new Autor();
	    TxtNome.setText("");
	    TxtNome.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event){
	    editar(true);
	
	    incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event){
	    daoautor.apagar(autor);
	    preencherTabela();
    }

    private void editar(boolean habilitar){
	    TxtNome.setDisable(!habilitar);
        TxtSobrenome.setDisable(!habilitar);
        TxtNacionalidade.setDisable(!habilitar);
	    BtnGravar.setDisable(!habilitar);
	    BtnAlterar.setDisable(habilitar);
	    BtnIncluir.setDisable(habilitar);
	    BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados(){
	    this.autor = tabela.getSelectionModel().getSelectedItem();
	
	    if(autor == null) return;
	
	    TxtNome.setText(autor.getNome());
	    TxtSobrenome.setText(autor.getSobrenome());
        TxtNacionalidade.setText(autor.getNacionalidade());
    }

    private void preencherTabela() {
        List<Autor> autores = daoautor.buscarTodosPorId();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        tabela.setItems(data);
    }    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Autor, Long>("ID")
        );        
        
        colNome.setCellValueFactory(
            new PropertyValueFactory<Autor, String>("Nome")
        );
        
        colSobrenome.setCellValueFactory(
            new PropertyValueFactory<Autor, String>("Sobrenome")
        );

        colNacionalidade.setCellValueFactory(
            new PropertyValueFactory<Autor, String>("Nacionalidade")
        );

        preencherTabela();
    }    
}