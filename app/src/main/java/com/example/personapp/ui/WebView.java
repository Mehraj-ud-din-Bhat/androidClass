package com.example.personapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.personapp.R;
import com.example.personapp.databinding.ActivityMainViewBinding;
import com.example.personapp.databinding.ActivityWebViewBinding;

public class WebView extends AppCompatActivity {

    ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String url=getIntent().getStringExtra("url");
        String title=getIntent().getStringExtra("title");
       WebSettings settings= binding.webView.getSettings();
       settings.setJavaScriptEnabled(true);
       binding.webView. setWebChromeClient(new WebChromeClient());
       settings.setDomStorageEnabled(true);
       binding.webView.loadUrl(url);

    }
}