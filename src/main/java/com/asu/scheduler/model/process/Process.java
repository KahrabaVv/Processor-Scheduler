package com.asu.scheduler.model.process;

public class Process {

    public enum ProcessState {
        READY, RUNNING, TERMINATED
    }

    int pid;
    int arrivalTime;
    int burstTime;
    int priority;
    int waitingTime;
    int turnAroundTime;
    int completionTime;
    public int remainingTime;
    int responseTime;

    public ProcessState state;

    public static int pidCounter = 1;

    // Constructor

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

    public int getPID() {
        return pid;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public ProcessState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "P(" + pid + ") " + "AT: " + arrivalTime + " BT: " + burstTime + " RT: " + remainingTime;
    }
}
