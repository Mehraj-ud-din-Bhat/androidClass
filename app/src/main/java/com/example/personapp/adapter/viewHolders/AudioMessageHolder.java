package com.example.personapp.adapter.viewHolders;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Message;
import com.example.personapp.utility.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioMessageHolder extends RecyclerView.ViewHolder {
    ImageView audioIcon;
    TextView messageTime;
    TextView messageUser;
    TextView audioDuration;
    public AudioMessageHolder(@NonNull View itemView) {
        super(itemView);
        audioIcon=itemView.findViewById(R.id.icon_audio);
        messageTime=itemView.findViewById(R.id.messageTime);
        messageUser=itemView.findViewById(R.id.messageUser);
        audioDuration=itemView.findViewById(R.id.audionDuration);
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
            audioDuration.setGravity(RIGHT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = RIGHT;
            audioIcon.setLayoutParams(params);


        }else {
           // messageText.setTextColor(context.getColor(R.color.black));
            audioDuration.setGravity(Gravity.LEFT);
            messageTime.setGravity(Gravity.LEFT);
            messageUser.setGravity(Gravity.LEFT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = LEFT;
            audioIcon.setLayoutParams(params);
        }

      //  messageText.setText(message.messageText);
        audioIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioIcon.setImageDrawable(context.getDrawable(R.drawable.icon_pause));
                playAudio(message.file.getUrl(),context);
            }
        });
        if(message.file.getDuration()!=null)
        {
            audioDuration.setText(message.file.getDuration()+" Min");
        }
        messageTime.setText(getMessageTime(message.date));


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
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    audioIcon.setImageDrawable(context.getDrawable(R.drawable.icon_play));
                }
            });

        } catch(Exception e) {

            System.out.println(e.toString());
        }
    }

    public  String  getMessageTime(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String formattedDate= sdf.format(date);
        return  formattedDate;
    }

}
