/*Priority-P, Priority-NP*/
package com.asu.scheduler.model.controller;

import com.asu.scheduler.ProcessorSchedulerApplication;
import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.processor.priority.PriorityPProcessor;
import com.asu.scheduler.model.process.Process;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import static com.asu.scheduler.ProcessorSchedulerApplication.*;


public class PriorityCtrl implements Initializable{

    @FXML
    private Button AddButton;

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
    private TextField priority_field;

    @FXML
    private Text priority_label;

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
        priorityColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("priority"));
        remainingTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("remainingTime"));
        Process.pidCounter=1;
    }
    @FXML
    void Add(ActionEvent event){

        if (Arrival_Time.getText() == null || Arrival_Time.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Arrival Time is Empty");
            alert.showAndWait();
            return;
        }
        else if (Burst_Time.getText() == null || Burst_Time.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Burst Time is Empty");
            alert.showAndWait();
            return;
        }
        else if (priority_field.getText() == null || priority_field.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Priority is Empty");
            alert.showAndWait();
            return;
        }

        Process process = new Process(
                Integer.parseInt(Arrival_Time.getText()),
                Integer.parseInt(Burst_Time.getText()),
                Integer.parseInt(priority_field.getText()));

        processes= table.getItems();
        addProcess(process);
        table.setItems(processes);

        Arrival_Time.clear();
        Burst_Time.clear();
        priority_field.clear();
    }
    @FXML
    private void PrevScene(ActionEvent event) throws IOException {
        fxmlFileName = "AlgoScene.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(ProcessorSchedulerApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 450, 150);
        Stage stage = (Stage) PreviousButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Solve(ActionEvent event) {
        // Change the processor type here
        initProcessor(new PriorityPProcessor());

        // Simulate the application
        simulate();

        // Initialize the Gantt Chart Scene
        initGanttChartScene();
        Stage stage = (Stage) Solve_Button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
