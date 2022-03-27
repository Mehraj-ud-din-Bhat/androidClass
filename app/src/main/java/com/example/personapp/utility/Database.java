package com.example.personapp.utility;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.personapp.models.Message;
import com.example.personapp.models.User;
import com.example.personapp.ui.ChatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Database   {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference userRef;
    private Context context;
    StorageReference storageReference;

    public  Database(Context mContext) {
      this.context=mContext;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("chats");
        userRef=firebaseDatabase.getReference().child("users");
        storageReference= FirebaseStorage.getInstance().getReference();
    }


   public void doLogin(User user)
    {
       userRef.child(user.getPhoneNumber()).setValue(user);
    }



    public  void sendMessage(String chatChannel,Message message) {

        databaseReference.child(chatChannel).push().setValue(message);

    }

    public  void uploadFileToStorage(Uri audio, ChatActivity activity)
    {
        StorageReference ref = storageReference.child("audios/"+ UUID.randomUUID().toString());

        ref.putFile(audio).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {

                       activity.onFileUploaded(uri.toString());
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       activity.onError("Failed to get Url");
                   }
               });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                activity.onError("Failed to UPLOAD FILE");
            }
        });


    }





    private  void  getChannel(String chatChannel,Message message)
    {


    }




    public  DatabaseReference getChatRefrence()
    {

        return  this.databaseReference;
    }



    public  void setMeOnline()
    {

        userRef.child(SharedPref.getCurrentuser(context)).child("isOnline").setValue(1);

    }


    public  void setMeOffline()
    {

        userRef.child(SharedPref.getCurrentuser(context)).child("isOnline").setValue(0);

    }




    public DatabaseReference  isOnline(String user)
    {
      return   userRef.child(user).child("isOnline");

    }


    public  DatabaseReference isTyping(String user)
    {
      return  userRef.child(user).child("isTyping");

    }

    public  void  updateTypingStatus(int status)
    {

        userRef.child(SharedPref.getCurrentuser(context)).child("isTyping").setValue(status);
    }


    public  DatabaseReference getUsers()
    {
      return   userRef ;
    }





}
