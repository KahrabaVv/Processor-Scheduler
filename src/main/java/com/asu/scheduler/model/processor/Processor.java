package com.asu.scheduler.model.processor;

import java.util.ArrayList;
import com.asu.scheduler.model.process.Process;

public abstract class Processor {

    // Enumerations
    public enum ProcessorState { IDLE, BUSY }

    public enum ProcessorType { FCFS, SJF_P, SJF_NP, RR, PRIORITY }

    // Constructor
    protected Processor(ProcessorType processorType) {
        this.processorType = processorType;
    }

    public ProcessorState state = ProcessorState.IDLE;

    private ProcessorType processorType;

    public ArrayList<Process> readyProcesses = new ArrayList<Process>();
    public ArrayList<Process> terminatedProcesses = new ArrayList<Process>();

    protected Process currentProcess = null;


    // Abstract method to be implemented by the child classes
    public abstract void execute();

    // Override toString() method
    @Override
    public String toString() {
        return "Processor [state=" + state + ", processorType=" + processorType + ", readyProcesses=" + readyProcesses
                + ", terminatedProcesses=" + terminatedProcesses + ", currentProcess=" + currentProcess + "]";
    }

    // Getters and Setters
    public Process getCurrentProcess() {
        return currentProcess;
    }

    public ProcessorType getProcessorType() {
        return processorType;
    }

    public void setProcessorType(ProcessorType processorType) {
        this.processorType = processorType;
    }
}
