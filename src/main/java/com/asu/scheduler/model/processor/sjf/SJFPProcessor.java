package com.asu.scheduler.model.processor.sjf;

import com.asu.scheduler.model.processor.Processor;
import com.asu.scheduler.model.process.Process;

public class SJFPProcessor extends Processor {

    public SJFPProcessor() {
        super(ProcessorType.SJF_P);
    }

    @Override
    public void execute() {
        // Algorithm for SJF with Preemption (Preemptive Shortest Job First)
        if (currentProcess != null) {
            currentProcess.decrementRemainingTime();
            if (currentProcess.getRemainingTime() == 0) {
                terminatedProcesses.add(currentProcess);
                currentProcess = null;
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
            if (currentProcess != null && shortestProcess.getRemainingTime() >= currentProcess.getRemainingTime()) {
                return;
            } else if (currentProcess != null) {
                currentProcess.state = Process.ProcessState.READY;
                readyProcesses.add(currentProcess);
            }
            currentProcess = shortestProcess;
            currentProcess.state = Process.ProcessState.RUNNING;
            readyProcesses.remove(shortestProcess);
        } else if (currentProcess != null) {
            return;
        }

        state = currentProcess == null ? ProcessorState.IDLE : ProcessorState.BUSY;

        super.increaseWaitingTime();
    }
}
