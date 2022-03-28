package com.example.personapp.adapter.viewHolders;

import static android.view.Gravity.RIGHT;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Message;
import com.example.personapp.utility.SharedPref;

public class AudioMessageHolder extends RecyclerView.ViewHolder {
    ImageView audioIcon;
    TextView messageTime;
    TextView messageUser;
    public AudioMessageHolder(@NonNull View itemView) {
        super(itemView);
        audioIcon=itemView.findViewById(R.id.icon_audio);
        messageTime=itemView.findViewById(R.id.messageTime);
        messageUser=itemView.findViewById(R.id.messageUser);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void bind(Message message, Context context)
    {

        String userName= SharedPref.getCurrentuser(context.getApplicationContext());
        if(message.senderName.equals(userName))
        {
           // messageText.setTextColor(context.getColor(R.color.blue));
            //messageText.setGravity(Gravity.RIGHT);
            messageTime.setGravity(RIGHT);
            messageUser.setGravity(RIGHT);
            audioIcon.setForegroundGravity(RIGHT);
        }else {
           // messageText.setTextColor(context.getColor(R.color.black));
          //  messageText.setGravity(Gravity.LEFT);
            messageTime.setGravity(Gravity.LEFT);
            messageUser.setGravity(Gravity.LEFT);
        }

      //  messageText.setText(message.messageText);
        audioIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             playAudio(message.file.getUrl(),context);
            }
        });


    }

    private  void  playAudio(String url,Context context)
    {
        try {
            Uri uri = Uri.parse(url);
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(context, uri);
            player.prepare();
            player.start();
        } catch(Exception e) {

            System.out.println(e.toString());
        }
    }
}
