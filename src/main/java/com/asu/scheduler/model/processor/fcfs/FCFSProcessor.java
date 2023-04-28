package com.asu.scheduler.model.processor.fcfs;

import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;

public class FCFSProcessor extends Processor {

    public FCFSProcessor() {
        super(ProcessorType.FCFS);
    }

    @Override
    public void execute() {
        if (currentProcess != null) {
            currentProcess.decrementRemainingTime();

            if (currentProcess.getRemainingTime() == 0) {
                terminatedProcesses.add(currentProcess);
                currentProcess = null;

                if (readyProcesses.size() > 0) {
                    currentProcess = readyProcesses.remove(0);
                    currentProcess.state = Process.ProcessState.RUNNING;
                }
            }

        } else {
            if (readyProcesses.size() > 0) {
                currentProcess = readyProcesses.remove(0);
                currentProcess.state = Process.ProcessState.RUNNING;
            }
        }
        state = currentProcess == null ? ProcessorState.IDLE : ProcessorState.BUSY;

        super.increaseWaitingTime();
    }
}
