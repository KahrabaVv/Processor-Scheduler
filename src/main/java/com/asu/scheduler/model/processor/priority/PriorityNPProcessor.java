package com.asu.scheduler.model.processor.priority;

import com.asu.scheduler.model.process.Process;
import com.asu.scheduler.model.processor.Processor;

import java.util.Comparator;


public class PriorityNPProcessor extends Processor {

    public PriorityNPProcessor() {
        super(ProcessorType.PRIORITY_NP);
    }

    @Override
    public void execute() {
        // Check if the current process has completed execution
        if (currentProcess != null && currentProcess.getRemainingTime() == 0) {
            currentProcess.state = Process.ProcessState.TERMINATED;
            terminatedProcesses.add(currentProcess);
            currentProcess = null;
        }

        // If there is no current process, select the highest priority process from the ready processes
        if (currentProcess == null && !readyProcesses.isEmpty()) {
            Process highestPriorityProcess = readyProcesses.stream()
                    .min(Comparator.comparingInt(Process::getPriority))
                    .orElse(null);

            if (highestPriorityProcess != null) {
                currentProcess = highestPriorityProcess;
                currentProcess.state = Process.ProcessState.RUNNING;
                readyProcesses.remove(highestPriorityProcess);
            }
        }

        // Execute the current process
        if (currentProcess != null) {
            currentProcess.decrementRemainingTime();
        }

        // Update the processor state
        state = currentProcess == null ? ProcessorState.IDLE : ProcessorState.BUSY;


        super.increaseWaitingTime();
    }
}
