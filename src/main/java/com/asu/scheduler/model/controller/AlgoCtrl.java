package com.asu.scheduler.model.controller;

import com.asu.scheduler.ProcessorSchedulerApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlgoCtrl implements Initializable {

    @FXML
    private Text Algorithm;

    @FXML
    private javafx.scene.control.ComboBox<String> comboBox;

    @FXML
    private Button NextButton;

    String fxmlFileName = "";
    public static String selectedScene;

    protected int defaultWidth = 400, defaultHeight = 150;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBox.setItems(FXCollections.observableArrayList("FCFS","SJF-NP","SJF-P","Priority-NP","Priority-P","Round Robin"));
    }

    @FXML
    private void handleSceneSelection(ActionEvent event) throws IOException {
        selectedScene = comboBox.getValue();

        defaultWidth = 770;
        defaultHeight = 425;
        if (selectedScene.equals("Round Robin")) {
            fxmlFileName = "RRScene.fxml";
            defaultWidth = 700;
            defaultHeight = 150;
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
        // Check if the user selected a scene
        if (fxmlFileName.equals("")) {
            // Show Message Alert Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Algorithm Selected");
            alert.setContentText("Please select an algorithm to continue");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(ProcessorSchedulerApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), defaultWidth, defaultHeight);
        Stage stage = (Stage) comboBox.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
