/*Round Robin*/
package com.nada.sb_test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.text.Text;

public class RR_Controller implements Initializable {
    List<Process> processList = new ArrayList<>();
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
    private Text quantum_label;
    @FXML
    private TextField Time_Quantum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void Add(ActionEvent event){
        Process process= new Process(
                Integer.parseInt(Arrival_Time.getText()),
                Integer.parseInt(Burst_Time.getText()),
                Integer.parseInt(Burst_Time.getText()));

        processList.add(process);
        Arrival_Time.clear();
        Burst_Time.clear();
    }

    @FXML
    private void handleSceneSelection(ActionEvent event) throws IOException {
    }

}
