package com.app.visio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();
    }

    private void startTimer() {
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageconnexion();
            }
        }, 3000); // 5000 millisecondes = 5 secondes
    }

    private void pageconnexion() {
       startActivity(new Intent(this,ConnexionInscription.class));
    }
}