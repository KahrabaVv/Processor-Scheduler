package com.nada.sb_test;

import javafx.scene.paint.Color;

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

    public Color color;

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
//        this.pid = pidCounter++;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
//        this.remainingTime = burstTime;
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

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public static void setPidCounter(int pidCounter) {
        Process.pidCounter = pidCounter;
    }

    @Override
    public String toString() {
        return "P(" + pid + ") " + "AT: " + arrivalTime + " BT: " + burstTime + " RT: " + remainingTime;
    }
}
