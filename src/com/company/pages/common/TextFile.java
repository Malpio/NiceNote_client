package com.company.pages.common;

import java.io.Serializable;

public class TextFile implements Serializable {
    private final Integer id;
    private String fileName;

    public TextFile(Integer id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
