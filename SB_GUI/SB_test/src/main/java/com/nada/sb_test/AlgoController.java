package com.nada.sb_test;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlgoController implements Initializable {

    @FXML
    private Text Algorithm;

    @FXML
    private javafx.scene.control.ComboBox<String> comboBox;

    @FXML
    private Button NextButton;

    String fxmlFileName = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBox.setItems(FXCollections.observableArrayList("FCFS","SJF-NP","SJF-P","Priority-NP","Priority-P","Round Robin"));
    }

    @FXML
    private void handleSceneSelection(ActionEvent event) throws IOException {
        String selectedScene = comboBox.getValue();


        if (selectedScene.equals("Round Robin")) {
            fxmlFileName = "RRScene.fxml";
        }
        else if(selectedScene.equals("Priority-NP")||selectedScene.equals("Priority-P")){
            fxmlFileName = "PriorityScene.fxml";
        }
        else if(selectedScene.equals("FCFS")||selectedScene.equals("SJF-NP")||selectedScene.equals("SJF-P")){
            fxmlFileName = "ParentScene.fxml";
        }


    }

    @FXML
    private void NextScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Stage stage = (Stage) comboBox.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
