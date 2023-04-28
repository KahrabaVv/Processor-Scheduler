package com.asu.scheduler.model.processor.priority;

import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;


public class PriorityPProcessor extends Processor {

    public PriorityPProcessor() {
        super(ProcessorType.PRIORITY_P);
    }

    @Override
    public void execute() {
        // TODO: Implement Priority Processor
        if (currentProcess != null) {
            currentProcess.decrementRemainingTime();
            if (currentProcess.getRemainingTime() == 0) {
                terminatedProcesses.add(currentProcess);
                currentProcess = null;
            }
        }

        Process highestPriorityProcess = null;
        if (readyProcesses.size() > 0) {
            highestPriorityProcess = readyProcesses.get(0);
            for (Process process : readyProcesses) {
                if (process.getPriority() < highestPriorityProcess.getPriority()) {
                    highestPriorityProcess = process;
                }
            }
            if (currentProcess != null && highestPriorityProcess.getPriority() >= currentProcess.getPriority()) {
                return;
            } else if (currentProcess != null) {
                currentProcess.state = Process.ProcessState.READY;
                readyProcesses.add(currentProcess);
            }
            currentProcess = highestPriorityProcess;
            currentProcess.state = Process.ProcessState.RUNNING;
            readyProcesses.remove(highestPriorityProcess);
        } else if (currentProcess != null) {
            return;
        }

        state = currentProcess == null ? ProcessorState.IDLE : ProcessorState.BUSY;

        super.increaseWaitingTime();
    }
}
