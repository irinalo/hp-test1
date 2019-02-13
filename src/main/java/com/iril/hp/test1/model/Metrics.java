package com.iril.hp.test1.model;

import java.util.List;
import java.util.Map;

public class Metrics {
    private String fileName;
    private long missingFields;
    private long invalidFields;
    private long blankContent;
    private List<CountryCodesMetrics> countryCodesMetricsList;
    private double ko_ok;
    private Map<String, Integer> wordFrequency;

    public Metrics(String fileName, long missingFields, long invalidFields, long blankContent,
        List<CountryCodesMetrics> countryCodesMetricsList, double ko_ok,
        Map<String, Integer> wordFrequency) {
        this.fileName = fileName;
        this.missingFields = missingFields;
        this.invalidFields = invalidFields;
        this.blankContent = blankContent;
        this.countryCodesMetricsList = countryCodesMetricsList;
        this.ko_ok = ko_ok;
        this.wordFrequency = wordFrequency;
    }

    public Metrics() {

        super();
    }

    public List<CountryCodesMetrics> getCountryCodesMetricsList() {
        return countryCodesMetricsList;
    }

    public void setCountryCodesMetricsList(List<CountryCodesMetrics> countryCodesMetricsList) {
        this.countryCodesMetricsList = countryCodesMetricsList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(long missingFields) {
        this.missingFields = missingFields;
    }

    public long getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(long invalidFields) {
        this.invalidFields = invalidFields;
    }

    public long getBlankContent() {
        return blankContent;
    }

    public void setBlankContent(long blankContent) {
        this.blankContent = blankContent;
    }

    public double getKo_ok() {
        return ko_ok;
    }

    public void setKo_ok(double ko_ok) {
        this.ko_ok = ko_ok;
    }

    public Map<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    public void setWordFrequency(Map<String, Integer> wordFrequency) {
        this.wordFrequency = wordFrequency;
    }
}