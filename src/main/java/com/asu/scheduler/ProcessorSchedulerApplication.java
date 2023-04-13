package com.asu.scheduler;

import com.asu.scheduler.model.GlobalStopWatch;
import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.processor.fcfs.FCFSProcessor;
import com.asu.scheduler.model.process.Process;
import com.asu.scheduler.model.processor.priority.PriorityProcessor;
import com.asu.scheduler.model.processor.rr.RRProcessor;
import com.asu.scheduler.model.processor.sjf.SJFNPProcessor;
import com.asu.scheduler.model.processor.sjf.SJFPProcessor;

import java.util.ArrayList;

public class ProcessorSchedulerApplication {

    // Global Variables
    public static ArrayList<Process> processes = new ArrayList<>();
    public static Processor processor;

    public static void main(String[] args) {
        initProcessor(new PriorityProcessor()); // Change the processor type here

        addTempData(); // Add test data according to the processor type

        StringBuilder ganntChart = new StringBuilder();
        ganntChart
                .append("Gannt Chart for ")
                .append(processor.getProcessorType())
                .append(" Processor:\n");

        while (true) {
            populateProcesses();
            processor.execute();

            GlobalStopWatch.incrementTime();

            if (processes.size() == 0 && processor.state == Processor.ProcessorState.IDLE) {
                System.out.println(ganntChart.toString());
                System.out.println("\nAll processes have been terminated");
                System.exit(0);
            } else {
                ganntChart.append(processor.getCurrentProcess() == null ? "I" : "P(" + processor.getCurrentProcess().getPID() + ") ");
            }
        }
    }

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
