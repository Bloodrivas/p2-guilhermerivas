package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LivroController implements Initializable {

    @FXML
    private Button BntIncluir;

    @FXML
    private Button BntAlterar;

    @FXML
    private Button BntExcluir;

    @FXML
    private Button BntGravar;

    @FXML
    private TextField TxtTitulo;
    
    @FXML
    private ComboBox<Autor> CboAutor;
    
    @FXML
    private TextField TxtAno;

    @FXML 
    private ListView<Livro> LstLivros;

    private DaoLivro dao = new DaoLivro();

    private DaoAutor daoautor = new DaoAutor();

    private Livro livro;

    private Autor autor;

    
    private boolean incluindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    } 

    @FXML
    private void gravar_click(ActionEvent event){
	    //livro.setCodigo(TxtCodigo.getText());
	    livro.setAutor(CboAutor.getSelectionModel().getSelectedItem());
        livro.setTitulo(TxtTitulo.getText());
        livro.setAno(TxtAno.getText());
		
	    if(incluindo){
		    dao.inserir(livro);
	    } else{
		    dao.alterar(livro);
	    }
	
	    preencherLista();
        preencherCombo();
	    editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event){
	    editar(true);
	
	    incluindo = true;
	    livro = new Livro();
	    TxtTitulo.setText("");
	    TxtTitulo.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event){
	    editar(true);
	
	    incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event){
	    dao.apagar(livro);
	    preencherLista();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent event){
	    exibirDados();
    }

    @FXML
    private void LstLivros_MouseClicked(MouseEvent event){
	    exibirDados();
    }

    private void editar(boolean habilitar){
	    LstLivros.setDisable(habilitar);
	    TxtTitulo.setDisable(!habilitar);
        CboAutor.setDisable(!habilitar);
        TxtAno.setDisable(!habilitar);
	    BntGravar.setDisable(!habilitar);
	    BntAlterar.setDisable(habilitar);
	    BntIncluir.setDisable(habilitar);
	    BntExcluir.setDisable(habilitar);
    }

    private void exibirDados(){
	    this.livro = LstLivros.getSelectionModel().getSelectedItem();
	
	    if(livro == null) return;
	
	    TxtTitulo.setText(livro.getTitulo());
	    livro.setAutor(CboAutor.getSelectionModel().getSelectedItem());
        TxtAno.setText(livro.getAno());
    }


    private void preencherLista(){
	    List<Livro> livros = dao.buscarTodos();
	
	    ObservableList<Livro> data = FXCollections.observableArrayList(livro);
	    LstLivros.setItems(data);
    }
    
    private void preencherCombo() {
        List<Autor> autores = daoautor.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        CboAutor.setItems(data);
    }   

       
}