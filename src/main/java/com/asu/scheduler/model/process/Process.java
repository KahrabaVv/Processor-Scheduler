package com.asu.scheduler.model.process;

import com.asu.scheduler.model.GlobalStopWatch;
import javafx.scene.paint.Color;

public class Process {

    public enum ProcessState {
        READY, RUNNING, TERMINATED
    }

    // Static variable to keep track of the number of processes
    public static int pidCounter = 1;

    // Process ID
    int pid;

    // Arrival time of the process
    int arrivalTime;

    // Burst time of the process
    int burstTime;

    // Priority of the process
    int priority;

    // Waiting time of the process
    int waitingTime;

    // Turn around time of the process
    int turnAroundTime;

    // Remaining time of the process
    int remainingTime;

    // Finish time is the time when the process is terminated
    int finishTime;

    // Color of the process in the Gantt Chart
    public Color color;

    // State of the process

    public ProcessState state;


    // Constructors
    public Process(int arrivalTime, int burstTime) {
        this.pid = pidCounter++;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }

    public Process(int arrivalTime, int burstTime, int priority) {
        this.pid = pidCounter++;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }

    // Getters and Setters
    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPid() {
        return pid;
    }

    public int getPriority() {
        return priority;
    }

    public void decrementRemainingTime() {
        remainingTime--;

        if (remainingTime == 0) {
            state = ProcessState.TERMINATED;
        }

        finishTime = GlobalStopWatch.getTime();
        turnAroundTime = finishTime - arrivalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void incrementWaitingTime() {
        waitingTime++;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    @Override
    public String toString() {
        return "P(" + pid + ") " + "AT: " + arrivalTime + " BT: " + burstTime + " RT: " + remainingTime + " WT: " + waitingTime + " TT: " + turnAroundTime + " FT: " + finishTime;
    }
}