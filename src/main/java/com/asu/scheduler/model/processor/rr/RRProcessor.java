package com.asu.scheduler.model.processor.rr;

import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;

public class RRProcessor extends Processor {
    private final int quantum;
    private int counter;

    public RRProcessor(int quantum) {
        super(ProcessorType.RR);
        this.quantum = quantum;
        this.counter = 0;
    }

    @Override
    public void execute() {
        // Algorithm for Round Robin
        if (currentProcess != null) {
            currentProcess.decrementRemainingTime();
            counter++;
            if (currentProcess.getRemainingTime() == 0) {
                terminatedProcesses.add(currentProcess);
                currentProcess = null;
                counter = 0;
            } else if (counter == quantum) {
                currentProcess.state = Process.ProcessState.READY;
                readyProcesses.add(currentProcess);
                currentProcess = null;
                counter = 0;
            } else {
                return;
            }
        } else {
            counter = 0;
        }

        if (readyProcesses.size() > 0) {
            currentProcess = readyProcesses.get(0);
            currentProcess.state = Process.ProcessState.RUNNING;
            readyProcesses.remove(0);
        }

        state = currentProcess == null ? ProcessorState.IDLE : ProcessorState.BUSY;

        super.increaseWaitingTime();
    }
}
