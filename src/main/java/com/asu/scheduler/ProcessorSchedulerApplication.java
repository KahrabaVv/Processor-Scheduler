package com.asu.scheduler;

import com.asu.scheduler.model.GlobalStopWatch;
import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ProcessorSchedulerApplication extends Application {

    // Global Variables
    // I tried to use array list that implemented in project but the table isn't
    // updated using casting as its method needs observable list
    public static ObservableList<Process> processes; // List of processes
    public static ArrayList<Process> ganttChartProcesses = new ArrayList<>(); // List of processes for gannt chart
    public static Processor processor; // Processor object
    public static Scene scene; // List of scenes

    /**
     * Start JavaFX Application
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProcessorSchedulerApplication.class.getResource("AlgoScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 150);

        // Set the title of the stage
        stage.setTitle("Process Scheduler");

        // Set the icon of the stage
        stage.getIcons().add(new Image("cpu_icon.png"));

        // Select the scene to show
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX Application
        launch(args);
    }

    /**
     * Simulate
     */
    public static void simulate() {
        System.out.println("Simulating...");
        System.out.println("Processes: " + processes);
        while (true) {
            // Add new processes to the processor
            populateProcesses();

            // Run the processor
            processor.execute();

            // Increment the global time
            GlobalStopWatch.incrementTime();

            if (processes.size() == 0 && processor.state == Processor.ProcessorState.IDLE && processor.readyProcesses.size() == 0) {
                break;
            } else {
                if (processor.getCurrentProcess() != null && processor.getCurrentProcess().color == null) {
                    processor.getCurrentProcess().color = Color.rgb(
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256)
                    );
                }

                ProcessorSchedulerApplication.ganttChartProcesses.add(processor.getCurrentProcess());
            }
        }
    }

    /**
     * Initialize the Gantt Chart Scene
     */
    public static void initGanttChartScene() {
        // Create the chart pane
        GridPane chartPane = new GridPane();
        chartPane.setHgap(10);
        chartPane.setVgap(10);
        chartPane.setPadding(new Insets(10));

        // Add the chart pane and process labels to the root pane
        HBox root = new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        // Create the scene and show the stage
        scene = new Scene(root, 900, 150);

        // Add ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(chartPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportWidth(650);
        scrollPane.setPrefViewportHeight(150);
        root.getChildren().add(scrollPane);

        // Add outside VBox Avg Waiting Time and Avg Turnaround Time
        VBox outsideVBox = new VBox();
        outsideVBox.setSpacing(10);
        outsideVBox.setPadding(new Insets(10));
        outsideVBox.setAlignment(Pos.CENTER);
        outsideVBox.setPrefWidth(250);
        root.getChildren().add(outsideVBox);

        int currentTime = 0;
        int avgWaitingTime = 0;
        int avgTurnaroundTime = 0;
        System.out.println("Gannt Chart Processes: " + ganttChartProcesses);
        for (Process process : ganttChartProcesses) {
            // Create the rectangle for the process and add it to the chart pane and having
            // the label
            Rectangle processRect = new Rectangle(80, 15);

            if (process != null) {
                processRect.setFill(process.color);
            } else {
                processRect.setFill(Color.GRAY);
            }


            Label processLabel = new Label(process != null ? "P" + process.getPid() : "IDLE");
            processLabel.setPrefWidth(50);
            processLabel.setAlignment(Pos.CENTER);

            VBox processLabelBox = new VBox();
            processLabelBox.getChildren().addAll(processRect, processLabel);
            processLabelBox.setAlignment(Pos.BOTTOM_CENTER);
            chartPane.add(processLabelBox, currentTime, 1);

            // Create the label for the process and add it to the chart pane
            Label timeLabel = new Label(String.valueOf(currentTime));
            chartPane.add(timeLabel, currentTime, 2);

            // Increment the current time
            currentTime += 1;
        }

        avgWaitingTime = processor.terminatedProcesses.stream().mapToInt(Process::getWaitingTime).sum();
        avgTurnaroundTime = processor.terminatedProcesses.stream().mapToInt(Process::getTurnAroundTime).sum();

        avgWaitingTime /= processor.terminatedProcesses.size() != 0 ? processor.terminatedProcesses.size() : 1;
        avgTurnaroundTime /= processor.terminatedProcesses.size() != 0 ? processor.terminatedProcesses.size() : 1;



        // Add Avg Waiting Time
        Label avgWaitingTimeLabel = new Label("Average Waiting Time: " + avgWaitingTime);
        outsideVBox.getChildren().add(avgWaitingTimeLabel);

        // Add Avg Turnaround Time
        Label avgTurnaroundTimeLabel = new Label("Average Turnaround Time: " + avgTurnaroundTime);
        outsideVBox.getChildren().add(avgTurnaroundTimeLabel);
    }

    /**
     * Add temporary data to the list of processes (Debugging)
     */
    public static void addTempData() {
        // Priority Test Data
        addProcess(new Process(0, 10, 3));
        addProcess(new Process(0, 1, 1));
        addProcess(new Process(0, 2, 4));
        addProcess(new Process(0, 1, 5));
        addProcess(new Process(0, 5, 2));

        // // SJF-NP Test Data
        // addProcess(new Process(0, 7));
        // addProcess(new Process(2, 4));
        // addProcess(new Process(4, 1));
        // addProcess(new Process(5, 4));
        //
        // // SJF-P Test Data
        // addProcess(new Process(0, 7));
        // addProcess(new Process(2, 4));
        // addProcess(new Process(4, 1));
        // addProcess(new Process(5, 4));
        //
        // // FCFS Test Data
        // addProcess(new Process(0, 3));
        // addProcess(new Process(2, 6));
        // addProcess(new Process(4, 4));
        // addProcess(new Process(6, 5));
        // addProcess(new Process(8, 2));
        //
        // // RR Test Data
        // addProcess(new Process(0, 3));
        // addProcess(new Process(2, 6));
        // addProcess(new Process(4, 4));
        // addProcess(new Process(6, 5));
        // addProcess(new Process(8, 2));
    }

    /*
     * Initialize the Processor
     * 
     * @param processor
     */
    public static void initProcessor(Processor processor) {
        ProcessorSchedulerApplication.processor = processor;
    }

    /*
     * Populate the Processes to the Processor
     */
    public static void populateProcesses() {
        if (processor == null) {
            return;
        }

        processes.stream()
                .filter(process -> process.getArrivalTime() == GlobalStopWatch.getTime())
                .forEach(process -> {
                    processor.readyProcesses.add(process);
                    process.state = Process.ProcessState.READY;
                });

        processes.removeIf(process -> process.getArrivalTime() == GlobalStopWatch.getTime());
    }

    /*
     * Add a Process to the list of Processes
     * 
     * @param process
     */
    public static void addProcess(Process process) {
        processes.add(process);
    }
}