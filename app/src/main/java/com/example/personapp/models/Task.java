package com.example.personapp.models;

public class Task {
   public String taskDescription;
  public   String taskName;
  // CRUD : CREATE READ UPDATE DELETE

    public Task(String taskName,String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskName = taskName;
    }

    public Task() {
    }
}
