package com.iril.hp.test1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class McpFile {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private Long processTime;

    public McpFile() {
    }

    public McpFile(String fileName, Long processTime) {
        this.fileName = fileName;
        this.processTime = processTime;
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

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }
}