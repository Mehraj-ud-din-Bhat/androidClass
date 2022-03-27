package com.example.personapp.models;

public class MessageFile {
    String fileType;
    String url;

    public MessageFile(String fileType, String url) {
        this.fileType = fileType;
        this.url = url;
    }

    public MessageFile() {
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
