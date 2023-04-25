package com.nada.sb_test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableController implements Initializable {
    @FXML
    private static TableView<Process> table;
    @FXML
    private TableColumn<Process, String> Process;

    @FXML
    private TableColumn<Process, Integer> AT;

    @FXML
    private TableColumn<Process, Integer> BT;

    @FXML
    private TableColumn<Process, Integer> Priority;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Process.setCellValueFactory(new PropertyValueFactory<Process,String>("Process"));
        AT.setCellValueFactory(new PropertyValueFactory<Process, Integer>("AT"));
        BT.setCellValueFactory(new PropertyValueFactory<Process, Integer>("BT"));
        Priority.setCellValueFactory(new PropertyValueFactory<Process, Integer>("Priority"));
    }


    @FXML
    private void handleSceneSelection(ActionEvent event) throws IOException {

    }

//    public  TableController(List<Process> processList) {
//        ObservableList<Process> observableList = FXCollections.observableArrayList(processList);
//        table.setItems(observableList);
//    }

}

