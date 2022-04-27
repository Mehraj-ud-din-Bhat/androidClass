package com.example.personapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.personapp.R;
import com.example.personapp.ui.MainView;

public class FragmentInfo extends Fragment {

   Button buttonSetting;

    public FragmentInfo() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_info, container, false);
        buttonSetting=v.findViewById(R.id.settings);
         buttonSetting.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ((MainView)getActivity()).loadSetting();
             }
         });

        return  v;


    }
}