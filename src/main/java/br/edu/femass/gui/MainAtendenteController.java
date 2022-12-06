package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAtendenteController implements Initializable {
    @FXML
    private void aluno_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Aluno.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Aluno");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void professor_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Professor.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Professor");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void emprestimo_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Emprestimo.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Menu de Emprestimo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void devolucao_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Devolucao.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Menu de Devolucao");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
