package com.iril.hp.test1.model;

public class CountryCodesMetrics {
    private int origin;
    private int destination;
    private long count;
    private double averageDuration;

    public CountryCodesMetrics(int origin, int destination, long count, double averageDuration) {
        this.origin = origin;
        this.destination = destination;
        this.count = count;
        this.averageDuration = averageDuration;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(double averageDuration) {
        this.averageDuration = averageDuration;
    }
}