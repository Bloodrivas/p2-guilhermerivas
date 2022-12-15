package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainBibliotecarioController implements Initializable {
  

    @FXML
    private void autores_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Autor.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Menu de Atendente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void exemplares_click(ActionEvent event) {

        try { 
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Exemplar.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Menu de Atendente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void livro_click(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Livro.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Menu de Bibliotecario");
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
