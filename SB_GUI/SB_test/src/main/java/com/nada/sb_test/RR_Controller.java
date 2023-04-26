/*Round Robin*/
package com.nada.sb_test;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RR_Controller implements Initializable {

    @FXML
    private TextField quantum_field;

    @FXML
    private Text quantum_label;

    @FXML
    private Button PreviousButton;

    @FXML
    private Button NextButton;

    String fxmlFileName = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleSceneSelection(ActionEvent event) throws IOException {
    }
    @FXML
    private void PrevScene(ActionEvent event) throws IOException {
        fxmlFileName = "AlgoScene.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Stage stage = (Stage) PreviousButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void NextScene(ActionEvent event) throws IOException {
        fxmlFileName = "ParentScene.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Stage stage = (Stage) NextButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Solve(ActionEvent event) {

    }

}
