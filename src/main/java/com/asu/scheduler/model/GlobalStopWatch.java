package com.asu.scheduler.model;

public class GlobalStopWatch {
    private static int time = 0;

    public static int getTime() {
        return time;
    }

    public static void incrementTime() {
        time++;
    }

    public static void resetTime() {
        time = 0;
    }
}
