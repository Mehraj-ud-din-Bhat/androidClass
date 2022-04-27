package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentContainer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.personapp.R;
import com.example.personapp.databinding.ActivityMainViewBinding;
import com.example.personapp.ui.fragments.FragmentInfo;
import com.example.personapp.ui.fragments.SettingFragment;

public class MainView extends AppCompatActivity {


      ActivityMainViewBinding binding;
      FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mFragmentManager=getSupportFragmentManager();


       // startActivity(new Intent(this,WebView.class));
        initViews();
        loadFragment(new FragmentInfo());

        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new SettingFragment());
            }
        });

        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FragmentInfo());
            }
        });






    }

public  void  loadSetting()
{
    loadFragment(new SettingFragment());
}




    void  initViews()
   {
       binding.toolbarMain.toolBartitle.setText("Global News");
       binding.toolbarMain.back.setVisibility(View.GONE);


   }


   void  loadFragment(Fragment fragment)
   {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {

               FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
               fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
               fragmentTransaction.replace(R.id.container, fragment);
               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();

           }
       });
   }






}