package com.example.personapp.adapter;



import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.example.personapp.R;
import com.example.personapp.models.Message;
import com.example.personapp.models.User;
import com.example.personapp.utility.SharedPref;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.MessageHolder> {

    List<Message> messageList;
    Context context;


    public ChatsAdapter(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.message_item, parent, false);
         MessageHolder viewHolder = new MessageHolder(contactView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
          if(holder!=null)
          {
              String userName= SharedPref.getCurrentuser(context.getApplicationContext());
              if(messageList.get(position).senderName.equals(userName))
              {
                  holder.messageText.setTextColor(context.getColor(R.color.blue));
                  holder.messageText.setGravity(Gravity.RIGHT);
                  holder.messageTime.setGravity(Gravity.RIGHT);
                  holder.messageUser.setGravity(Gravity.RIGHT);
              }else {
                  holder.messageText.setTextColor(context.getColor(R.color.black));
                  holder.messageText.setGravity(Gravity.LEFT);
                  holder.messageTime.setGravity(Gravity.LEFT);
                  holder.messageUser.setGravity(Gravity.LEFT);
              }

              holder.messageText.setText(messageList.get(position).messageText);
              holder.messageTime.setText(messageList.get(position).getMessageTime());
              holder.messageUser.setText(messageList.get(position).senderName);
          }
    }

   public void refresh(List<Message> messageList)
    {
        this.messageList=messageList;
        notifyDataSetChanged();
    }

    public static String formatChatTime(long chatTime, boolean showHour) {
        if (chatTime < 1) {
            return "00:00";
        }//from w w w.j a v a 2 s  .  com

        long s = chatTime % 60;
        long m = chatTime / 60;
        if (showHour) {
            m = m % 60;
        }

        long h = chatTime / (60 * 60);

        String secStr = (s >= 10 ? ("" + s) : ("0" + s));
        String minStr = (m >= 10 ? ("" + m) : ("0" + m));
        String hourStr = (h >= 10 ? ("" + h) : ("0" + h));

        if (showHour && h != 0) {
            String separHour = hourStr.trim().equals("") ? "" : ":";
            return hourStr + separHour + minStr + ":" + secStr;
        } else {
            return minStr + ":" + secStr;
        }
    }




    // Returns the total count of items in the list


    @Override
    public int getItemCount() {
        return messageList.size();
    }


    public static class MessageHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView messageTime;
            TextView messageUser;
        public MessageHolder(View itemView) {
            super(itemView);
             messageText=itemView.findViewById(R.id.messageText);
             messageTime=itemView.findViewById(R.id.messageTime);
             messageUser=itemView.findViewById(R.id.messageUser);

        }
    }
}
