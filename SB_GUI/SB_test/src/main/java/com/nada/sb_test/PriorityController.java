/*Priority-P, Priority-NP*/
package com.nada.sb_test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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



public class PriorityController implements Initializable{


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
    private static TableView<Process> table;
//    @FXML
//    private TableColumn<Process, Integer> Process;

    @FXML
    private TableColumn<Process, Integer> arrivalTime;

    @FXML
    private TableColumn<Process, Integer> burstTime;

    @FXML
    private TableColumn<Process, Integer> priority;
//    ObservableList<Process> list= FXCollections.observableArrayList(
//            new Process(0,5,4),
//            new Process(1,6,3),
//            new Process(2,7,4),
//            new Process(2,7,4),
//            new Process(2,7,4)
//    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Process.setCellValueFactory(new PropertyValueFactory<Process,Integer>("pid"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("arrivalTime"));
        burstTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("burstTime"));
        priority.setCellValueFactory(new PropertyValueFactory<Process, Integer>("priority"));
//        processes = FXCollections.observableArrayList();
//        table.setItems(list);
    }
    @FXML
    void Add(ActionEvent event){
        Process process= new Process(
                Integer.parseInt(Arrival_Time.getText()),
                Integer.parseInt(Burst_Time.getText()),
                Integer.parseInt(priority_field.getText()));
        Arrival_Time.clear();
        Burst_Time.clear();
        priority_field.clear();
        processes= table.getItems();
        processes.add(process);
        table.setItems(processes);

    }

    @FXML
    void Solve(ActionEvent event) throws IOException {

    }


    @FXML
    private void handleSceneSelection(ActionEvent event) throws IOException {

    }

    private ObservableList<Process> processes=FXCollections.observableArrayList();
}
