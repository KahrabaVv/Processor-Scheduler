package com.asu.scheduler;

import com.asu.scheduler.model.GlobalStopWatch;
import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;
import com.asu.scheduler.model.processor.priority.PriorityProcessor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class ProcessorSchedulerApplication extends Application {

    // Global Variables
    public static ArrayList<Process> processes = new ArrayList<>(); // List of processes
    public static ArrayList<Process> ganttChartProcesses = new ArrayList<>(); // List of processes for gannt chart
    public static Processor processor; // Processor object
    public static Scene[] scenes = new Scene[3]; // List of scenes

    /**
     * Start JavaFX Application
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Set the title of the stage
        stage.setTitle("Process Scheduler");

        // Select the scene to show
        stage.setScene(scenes[2]);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        // Change the processor type here
        initProcessor(new PriorityProcessor());

        // Add test data according to the processor type
        addTempData();

//        StringBuilder ganntChart = new StringBuilder();
//        ganntChart
//                .append("Gannt Chart for ")
//                .append(processor.getProcessorType())
//                .append(" Processor:\n");

        // Simulate the application
        while (true) {
            // Add new processes to the processor
            populateProcesses();

            // Run the processor
            processor.execute();

            // Increment the global time
            GlobalStopWatch.incrementTime();

            if (processes.size() == 0 && processor.state == Processor.ProcessorState.IDLE) {
                // System.out.println(ganntChart.toString());
                // System.out.println("\nAll processes have been terminated");
                break;
                // System.exit(0);
            } else {
                // ganntChart.append(processor.getCurrentProcess() == null ? "I" : "P(" + processor.getCurrentProcess().getPID() + ") ");
                if (processor.getCurrentProcess().color == null) {
                    processor.getCurrentProcess().color = Color.rgb(
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256)
                    );
                }

                ganttChartProcesses.add(processor.getCurrentProcess());
            }
        }

        // Debugging
        // for (Process process : ganttChartProcesses) {
        //     System.out.print(process.getPID() + " -> ");
        // }

        // Initialize the Gantt Chart Scene
        initGanttChartScene();

        // Launch the JavaFX Application
        launch(args);
    }

    /**
     * Initialize the Gantt Chart Scene
     */
    private static void initGanttChartScene() {
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
        scenes[2] = new Scene(root, 800, 150);

        // Add ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(chartPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportWidth(800);
        scrollPane.setPrefViewportHeight(150);
        root.getChildren().add(scrollPane);

        int currentTime = 0;
        for (Process process : ganttChartProcesses) {
            // Create the rectangle for the process and add it to the chart pane and having the label
            Rectangle processRect = new Rectangle(80, 15);

            processRect.setFill(Objects.requireNonNullElseGet(process.color,
                    () ->
                            Color.color(
                                    Math.random(),
                                    Math.random(),
                                    Math.random())
            ));

            Label processLabel = new Label("P" + process.getPID());
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
    }

    /**
     * Add temporary data to the list of processes
     */
    public static void addTempData() {
        // Priority Test Data
        addProcess(new Process(0, 10, 3));
        addProcess(new Process(0, 1, 1));
        addProcess(new Process(0, 2, 4));
        addProcess(new Process(0, 1, 5));
        addProcess(new Process(0, 5, 2));

//        // SJF-NP Test Data
//        addProcess(new Process(0, 7));
//        addProcess(new Process(2, 4));
//        addProcess(new Process(4, 1));
//        addProcess(new Process(5, 4));
//
//        // SJF-P Test Data
//        addProcess(new Process(0, 7));
//        addProcess(new Process(2, 4));
//        addProcess(new Process(4, 1));
//        addProcess(new Process(5, 4));
//
//        // FCFS Test Data
//        addProcess(new Process(0, 3));
//        addProcess(new Process(2, 6));
//        addProcess(new Process(4, 4));
//        addProcess(new Process(6, 5));
//        addProcess(new Process(8, 2));
//
//        // RR Test Data
//        addProcess(new Process(0, 3));
//        addProcess(new Process(2, 6));
//        addProcess(new Process(4, 4));
//        addProcess(new Process(6, 5));
//        addProcess(new Process(8, 2));
    }

    /*
     * Initialize the Processor
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
     * @param process
     */
    public static void addProcess(Process process) {
        processes.add(process);
    }
}