package com.example.personapp.utility;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

public class AudioRecorder {

    final MediaRecorder recorder = new MediaRecorder();
    public final String path;


    public AudioRecorder(String path, Context context) {
        this.path= context.getExternalCacheDir().getAbsolutePath() + "/" +"audio"+ ".3gp";
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private String sanitizePath(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (!path.contains(".")) {
            path += ".3gp";
        }
        return Environment.getStorageDirectory().getAbsolutePath()
                + path;
    }

    public void start() throws IOException {

        // make sure the directory we plan to store the recording in exists
        File directory = new File(path).getParentFile();

        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created.");
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(path);
        recorder.prepare();
        recorder.start();
    }

    public void stop() throws IOException {
        recorder.stop();
        recorder.release();
    }

    public void playRecording(String path) throws IOException {
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(path);
        mp.prepare();
        mp.start();
        mp.setVolume(10, 10);
    }

    public Uri  getRecordedAudio()
    {
      Uri uri=Uri.fromFile(new File(path));
      return  uri;
    }
}
