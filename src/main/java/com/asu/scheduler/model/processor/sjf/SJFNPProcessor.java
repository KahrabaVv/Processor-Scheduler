package com.asu.scheduler.model.processor.sjf;

import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;

public class SJFNPProcessor extends Processor {

    public SJFNPProcessor() {
        super(ProcessorType.SJF_NP);
    }

    @Override
    public void execute() {
        // Algorithm for SJF without Preemption
        if (currentProcess != null) {
            currentProcess.decrementRemainingTime();
            if (currentProcess.getRemainingTime() == 0) {
                terminatedProcesses.add(currentProcess);
                currentProcess = null;
            } else {
                return;
            }
        }

        Process shortestProcess = null;
        if (readyProcesses.size() > 0) {
            shortestProcess = readyProcesses.get(0);
            for (Process process : readyProcesses) {
                if (process.getRemainingTime() < shortestProcess.getRemainingTime()) {
                    shortestProcess = process;
                }
            }
            currentProcess = shortestProcess;
            currentProcess.state = Process.ProcessState.RUNNING;
            readyProcesses.remove(shortestProcess);
        }

        state = currentProcess == null ? ProcessorState.IDLE : ProcessorState.BUSY;

        super.increaseWaitingTime();
    }
}
