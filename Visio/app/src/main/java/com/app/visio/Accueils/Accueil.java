package com.app.visio.Accueils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.visio.R;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        String userType=this.getIntent().getStringExtra("UserType");
        TextView t= findViewById(R.id.textView2);
        t.setText(t.getText()+" "+userType);
    }


    @Override
    public void onBackPressed() {

    }
}