package com.iril.hp.test1.model;

import java.util.Map;


public class Kpis {
    private long noOfFiles;
    private long noOfRows;
    private long noOfCalls;
    private long noOfMessages;
    private int noOfOriginCC;
    private int noOfDestinationCC;
    private   Map<String, Long> processingTime;

    public Kpis(long noOfFiles, long noOfRows, long noOfCalls, long noOfMessages, int noOfOriginCC,
        int noOfDestinationCC,
        Map<String, Long> processingTime) {
        this.noOfFiles = noOfFiles;
        this.noOfRows = noOfRows;
        this.noOfCalls = noOfCalls;
        this.noOfMessages = noOfMessages;
        this.noOfOriginCC = noOfOriginCC;
        this.noOfDestinationCC = noOfDestinationCC;
        this.processingTime = processingTime;
    }

    public long getNoOfFiles() {
        return noOfFiles;
    }

    public void setNoOfFiles(long noOfFiles) {
        this.noOfFiles = noOfFiles;
    }

    public long getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(long noOfRows) {
        this.noOfRows = noOfRows;
    }

    public long getNoOfCalls() {
        return noOfCalls;
    }

    public void setNoOfCalls(long noOfCalls) {
        this.noOfCalls = noOfCalls;
    }

    public long getNoOfMessages() {
        return noOfMessages;
    }

    public void setNoOfMessages(long noOfMessages) {
        this.noOfMessages = noOfMessages;
    }

    public int getNoOfOriginCC() {
        return noOfOriginCC;
    }

    public void setNoOfOriginCC(int noOfOriginCC) {
        this.noOfOriginCC = noOfOriginCC;
    }

    public int getNoOfDestinationCC() {
        return noOfDestinationCC;
    }

    public void setNoOfDestinationCC(int noOfDestinationCC) {
        this.noOfDestinationCC = noOfDestinationCC;
    }

    public Map<String, Long> getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Map<String, Long> processingTime) {
        this.processingTime = processingTime;
    }
}