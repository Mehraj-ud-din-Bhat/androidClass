package com.example.personapp.models;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
   public String messageText;
 public    Date date;
 public    String senderName;
    public Message(String messageText, Date date, String senderName) {
        this.messageText = messageText;
        this.date = date;
        this.senderName=senderName;
    }

    public Message() {
    }
}
