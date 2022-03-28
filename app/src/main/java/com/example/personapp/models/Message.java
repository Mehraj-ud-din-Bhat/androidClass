package com.example.personapp.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message  implements Serializable {
   public    String messageText;
   public    Date   date;
   public    String senderName;
   public    int messageType;
   public     MessageFile file;


    public Message(String messageText, Date date, String senderName, int messageType) {
        this.messageText = messageText;
        this.date = date;
        this.senderName = senderName;
        this.messageType = messageType;
    }

    public Message() {
    }

    public MessageFile getFile() {
        return file;
    }

    public void setFile(MessageFile file) {
        this.file = file;
    }
}
