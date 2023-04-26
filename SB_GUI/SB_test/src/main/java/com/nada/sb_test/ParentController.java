/*FCFS, SJF-P, SJF-NP*/
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


public class ParentController implements Initializable {

    @FXML
    private Text AT_label;

    @FXML
    private TextField Arrival_Time;

    @FXML
    private Text BT_label;

    @FXML
    private TextField Burst_Time;

    @FXML
    private Button Solve_Button;

    @FXML
    private TableView<Process> table;

    @FXML
    private TableColumn<Process, Integer> arrivalTimeColumn;

    @FXML
    private TableColumn<Process, Integer> burstTimeColumn;

    @FXML
    private TableColumn<Process, Integer> priorityColumn;

    @FXML
    private TableColumn<Process, String> PIDColumn;

//    private int pidCounter = 1;
    @FXML
    private TableColumn<Process, Integer> remainingTimeColumn;

    @FXML
    private Button PreviousButton;
    String fxmlFileName = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PIDColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("pid"));

        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("arrivalTime"));
        burstTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("burstTime"));
        remainingTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("remainingTime"));
        Process.pidCounter=1;
    }
    @FXML
    void Add(ActionEvent event){
        Process process = new Process(
                Integer.parseInt(Arrival_Time.getText()),
                Integer.parseInt(Burst_Time.getText())
                );

        //pidCounter++;
        ObservableList<Process> currentTableData = table.getItems();
        currentTableData.add(process);
        table.setItems(currentTableData);
//        System.out.println(Process.pidString);

        Arrival_Time.clear();
        Burst_Time.clear();


    }

    @FXML
    void Solve(ActionEvent event) {

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
    private void handleSceneSelection(ActionEvent event) throws IOException {

    }

}

