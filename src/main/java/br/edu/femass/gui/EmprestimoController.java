package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmprestimoController implements Initializable {
    @FXML
    private Button BtnGravarAluno;

    @FXML
    private Button BtnGravarDevolucao;

    @FXML
    private Button BtnGravarProfessor;

    @FXML
    private ComboBox<Aluno> CboAluno;

    @FXML
    private ComboBox<Professor> CboProfessor;

    @FXML
    private ComboBox<Exemplar> CboExemplar;

    @FXML
    private TableColumn<Emprestimo, LocalDate> ColData  = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> ColDevolucao  = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, String> ColExemplar  = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, Long> ColID  = new TableColumn<>();

    @FXML
    private TableColumn<Leitor, String> ColLeitor  = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> ColPrevisao  = new TableColumn<>();

    @FXML
    private TableView<Emprestimo> tabela = new TableView<Emprestimo>();

    private DaoAluno daoAluno = new DaoAluno();
    
    private DaoProfessor daoProfessor = new DaoProfessor();

    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();

    private DaoExemplar daoexemplar = new DaoExemplar();


    private Emprestimo emprestimo;
    
    private Exemplar exemplar;

    private Aluno aluno;

    private Professor professor;

    @FXML
    void gravar_devolucao(ActionEvent event) {
        preencherTabela();

        emprestimo = tabela.getSelectionModel().getSelectedItem();
        emprestimo.setDataDevolucao(LocalDate.now());

        daoEmprestimo.alterar(emprestimo);
        preencherTabela();
    }

    @FXML
    void gravar_emprestimo_aluno(ActionEvent event) {
        exemplar = CboExemplar.getSelectionModel().getSelectedItem();
        aluno = CboAluno.getSelectionModel().getSelectedItem();

        emprestimo = new Emprestimo(exemplar, aluno);
        
        daoEmprestimo.inserir(emprestimo);
        preencherTabela();
    }

    @FXML
    void gravar_emprestimo_professor(ActionEvent event) {
        exemplar = CboExemplar.getSelectionModel().getSelectedItem();
        professor = CboProfessor.getSelectionModel().getSelectedItem();
        
        emprestimo = new Emprestimo(exemplar, professor);

        daoEmprestimo.inserir(emprestimo);
        preencherTabela();
    }

    private void preencherCombo(){
        List<Aluno> alunos = daoAluno.buscarTodos();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        CboAluno.setItems(data);     

        List<Professor> professores = daoProfessor.buscarTodos();
        ObservableList<Professor> dataProfessor = FXCollections.observableArrayList(professores);
        CboProfessor.setItems(dataProfessor); 
        
        List<Exemplar> exemplares = daoexemplar.buscarTodos();
        ObservableList<Exemplar> dataExemplares = FXCollections.observableArrayList(exemplares);
        CboExemplar.setItems(dataExemplares); 

    }     

    

    private void preencherTabela(){
        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();
        
        ObservableList<Emprestimo> dataEmprestimo = FXCollections.observableArrayList(emprestimos);
        tabela.setItems(dataEmprestimo);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColID.setCellValueFactory(
            new PropertyValueFactory<Emprestimo, Long>("Id")
        );

        ColLeitor.setCellValueFactory(
            new PropertyValueFactory<Leitor, String>("leitor")
        );
        
        ColExemplar.setCellValueFactory(
            new PropertyValueFactory<Exemplar, String>("exemplar")
        );

        ColData.setCellValueFactory(
            new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo")
        );

        ColPrevisao.setCellValueFactory(
            new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao")
        );

        ColDevolucao.setCellValueFactory(
            new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao")
        );

        preencherCombo();
        preencherTabela();
    }

}
