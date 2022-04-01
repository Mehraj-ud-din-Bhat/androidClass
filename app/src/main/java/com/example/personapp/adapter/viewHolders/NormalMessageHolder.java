package com.example.personapp.adapter.viewHolders;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Message;
import com.example.personapp.utility.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NormalMessageHolder  extends RecyclerView.ViewHolder {

    TextView messageText;
    TextView messageTime;
    TextView messageUser;

    public NormalMessageHolder(@NonNull View itemView) {
        super(itemView);
        messageText=itemView.findViewById(R.id.messageText);
        messageTime=itemView.findViewById(R.id.messageTime);
        messageUser=itemView.findViewById(R.id.messageUser);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void bind(Message message, Context context)
    {

              String userName= SharedPref.getCurrentuser(context.getApplicationContext());
              if(message.senderName!=null) {
                  if (message.senderName.equals(userName)) {
                      messageText.setTextColor(context.getColor(R.color.blue));
                      messageText.setGravity(Gravity.RIGHT);
                      messageTime.setGravity(Gravity.RIGHT);
                      messageUser.setGravity(Gravity.RIGHT);
                  } else {
                      messageText.setTextColor(context.getColor(R.color.black));
                      messageText.setGravity(Gravity.LEFT);
                      messageTime.setGravity(Gravity.LEFT);
                      messageUser.setGravity(Gravity.LEFT);
                  }
                  messageText.setText(message.messageText);
                  messageTime.setText(getMessageTime(message.date));
              }
             // messageUser.setText(m);

    }

    public  String  getMessageTime(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String formattedDate= sdf.format(date);
        return  formattedDate;
    }


}
