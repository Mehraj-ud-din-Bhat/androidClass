package com.example.personapp.models;

public class Topic {
    String topicName;
     boolean isSelected=false;


    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
