package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AlunoController implements Initializable {
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnIncluir;

    @FXML
    private TableColumn<Aluno, String> colEnd  = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, Long> colID  = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colMatricula  = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colNome  = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colTel  = new TableColumn<>();

    @FXML
    private TableColumn<Leitor, Integer> colPrazo = new TableColumn<>();

    @FXML
    private TableView<Aluno> tabela = new TableView<Aluno>();

    @FXML
    private TextField txtEnd;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTel;

    DaoAluno daoAluno = new DaoAluno();

    private Aluno aluno;

    private Boolean incluindo;

    @FXML
    void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    void excluir_click(ActionEvent event) {
        daoAluno.apagar(aluno);
        preencherTabela();
    }

    @FXML
    void gravar_click(ActionEvent event) {
        aluno.setNome(txtNome.getText());
        aluno.setEndereco(txtEnd.getText());
        aluno.setTelefone(txtTel.getText());
        aluno.setMatricula(txtMatricula.getText());

        if (incluindo) {
            daoAluno.inserir(aluno);
        }
        else {
            daoAluno.alterar(aluno);
        }


        preencherTabela();
        editar(false);
    }

    @FXML
    void incluir_click(ActionEvent event) {
        editar(true);

        incluindo = true;
        preencherTabela();

        aluno = new Aluno();
        txtNome.setText("");
        txtEnd.setText("");
        txtTel.setText("");
        txtMatricula.setText("");
        txtNome.requestFocus();
    }

    private void editar(boolean habilitar) {
        tabela.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtEnd.setDisable(!habilitar);
        txtTel.setDisable(!habilitar);
        txtMatricula.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {

        this.aluno = tabela.getSelectionModel().getSelectedItem();

        if (aluno==null) return;

        txtNome.setText(aluno.getNome());
        txtEnd.setText(aluno.getEndereco());
        txtTel.setText(aluno.getTelefone());
        txtMatricula.setText(aluno.getMatricula());
    }


    private void preencherTabela() {
        List<Aluno> alunos = daoAluno.buscarTodos();

        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tabela.setItems(data);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("Id"));
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno,String>("nome"));
        colEnd.setCellValueFactory(new PropertyValueFactory<Aluno,String>("endereco"));
        colTel.setCellValueFactory(new PropertyValueFactory<Aluno,String>("telefone"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno,String>("matricula"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<Leitor,Integer>("prazoMaximoDevolucao"));

        preencherTabela();
    }  
}
