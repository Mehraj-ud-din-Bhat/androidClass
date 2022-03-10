package com.example.personapp.adapter;


import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Message;
import com.example.personapp.models.User;
import com.example.personapp.ui.ChatActivity;
import com.example.personapp.ui.UsersActivity;
import com.example.personapp.utility.SharedPref;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    List<User> userList;
    UsersActivity context;


    public UsersAdapter(List<User> userList, UsersActivity context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_user, parent, false);
        UserHolder viewHolder = new UserHolder(contactView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder( UserHolder holder, int position) {
          if(holder!=null)
          {
              if(userList.get(position).getPhoneNumber()!=null && userList.get(position).getPhoneNumber().length()>0)
              {
                  holder.userPhone.setText(userList.get(position).getPhoneNumber());
              }

              holder.userPhone.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      context.onClickedOnUser(userList.get(position));
                  }
              });

          }
    }


    public  String  getMessageTime(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String formattedDate= sdf.format(date);
        return  formattedDate;
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

 public   void  refresh(ArrayList<User> userArrayList)
   {
       this.userList=userArrayList;
       notifyDataSetChanged();
   }



    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class UserHolder extends RecyclerView.ViewHolder {
            TextView userPhone;
        public UserHolder(View itemView) {
            super(itemView);
             userPhone=itemView.findViewById(R.id.phoneNumber);


        }
    }
}
