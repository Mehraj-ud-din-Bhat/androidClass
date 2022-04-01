package com.example.personapp.models;

public class MessageFile {
    String fileType;
    String url;
    String duration;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

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
