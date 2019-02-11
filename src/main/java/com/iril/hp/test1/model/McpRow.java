package com.iril.hp.test1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class McpRow {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private Boolean missingFields;
    private Boolean invalidFields;
    private Boolean blankContent;
    private String statusCode;
    private String messageType;
    private int origin;
    private int destination;
    private long duration;

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

    public String getMessageType() {

        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public McpRow() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(Boolean missingFields) {
        this.missingFields = missingFields;
    }

    public Boolean getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(Boolean invalidFields) {
        this.invalidFields = invalidFields;
    }

    public Boolean getBlankContent() {
        return blankContent;
    }

    public void setBlankContent(Boolean blankContent) {
        this.blankContent = blankContent;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}