/*Priority-P, Priority-NP*/
package com.nada.sb_test;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class PriorityController implements Initializable{

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

//    private int pidCounter = 1;

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
        Process process = new Process(
                Integer.parseInt(Arrival_Time.getText()),
                Integer.parseInt(Burst_Time.getText()),
                Integer.parseInt(priority_field.getText()));

        //pidCounter++;
        ObservableList<Process> currentTableData = table.getItems();
        currentTableData.add(process);
        table.setItems(currentTableData);
//        System.out.println(Process.pidString);

        Arrival_Time.clear();
        Burst_Time.clear();
        priority_field.clear();

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
    void Solve(ActionEvent event) {

    }

}
