package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
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

public class ProfessorController implements Initializable {
    
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnIncluir;

    @FXML
    private TableColumn<Leitor, Integer> ColPrazo = new TableColumn<>();;

    @FXML
    private TableColumn<Professor, String> colDisciplina = new TableColumn<>();;

    @FXML
    private TableColumn<Professor, String> colEnd = new TableColumn<>();;

    @FXML
    private TableColumn<Professor, Long> colID = new TableColumn<>();;

    @FXML
    private TableColumn<Professor, String> colNome = new TableColumn<>();;

    @FXML
    private TableColumn<Professor, String> colTel = new TableColumn<>();

    @FXML
    private TableView<Professor> tabela = new TableView<Professor>();

    @FXML
    private TextField txtDisciplina;

    @FXML
    private TextField txtEnd;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTel;

    DaoProfessor daoProfessor = new DaoProfessor();

    private Professor professor;

    private Boolean incluindo;

    @FXML
    void alterar_click(ActionEvent event) {
        editar(true);
	
	    incluindo = false;
    }

    @FXML
    void excluir_click(ActionEvent event) {
        daoProfessor.apagar(professor);
	    preencherTabela();
    }

    @FXML
    void gravar_Click(ActionEvent event) {
        professor.setNome(txtNome.getText());
        professor.setEndereco(txtEnd.getText());
        professor.setTelefone(txtTel.getText());
        professor.setDisciplina(txtDisciplina.getText());

        if (incluindo) {
            daoProfessor.inserir(professor);
        }
        else {
            daoProfessor.alterar(professor);
        }


        preencherTabela();
        editar(false);
    }

    @FXML
    void incluir_click(ActionEvent event) {
        editar(true);

        incluindo = true;
        preencherTabela();

        professor = new Professor();
        txtNome.setText("");
        txtEnd.setText("");
        txtTel.setText("");
        txtDisciplina.setText("");
        txtNome.requestFocus();
    }

    private void editar(boolean habilitar) {
        tabela.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtEnd.setDisable(!habilitar);
        txtTel.setDisable(!habilitar);
        txtDisciplina.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {

        this.professor = tabela.getSelectionModel().getSelectedItem();

        if (professor==null) return;

        txtNome.setText(professor.getNome());
        txtEnd.setText(professor.getEndereco());
        txtTel.setText(professor.getTelefone());
        txtDisciplina.setText(professor.getDisciplina());
    }


    private void preencherTabela() {
        List<Professor> professores = daoProfessor.buscarTodos();

        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
        tabela.setItems(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Professor, Long>("ID")
        );        
        
        colNome.setCellValueFactory(
            new PropertyValueFactory<Professor, String>("nome")
        );
        
        colTel.setCellValueFactory(
            new PropertyValueFactory<Professor, String>("telefone")
        );

        colDisciplina.setCellValueFactory(
            new PropertyValueFactory<Professor, String>("disciplina")
        );

        ColPrazo.setCellValueFactory(
            new PropertyValueFactory<Leitor, Integer>("prazoMaximoDevolucao")
        );

        preencherTabela();
    } 
}
