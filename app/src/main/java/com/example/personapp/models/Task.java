package com.example.personapp.models;

public class Task {
  public      String taskName;
    public    String taskDescription;

    public Task(String taskName) {
        this.taskName = taskName;
        taskDescription="";

    }

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }
}
