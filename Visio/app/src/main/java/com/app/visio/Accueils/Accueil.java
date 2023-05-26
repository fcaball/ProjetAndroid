package com.app.visio.Accueils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.app.visio.Connexion;
import com.app.visio.ConnexionInscription;
import com.app.visio.Header;
import com.app.visio.R;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Header f= (Header) getSupportFragmentManager().findFragmentById(R.id.fragAccueilAno);
        ImageButton b=f.getView().findViewById(R.id.compte);
        b.setOnClickListener((v)->onclickCompte());

    }

    private void onclickCompte() {

        Button b=this.findViewById(R.id.button);
        if(b.getVisibility()==View.INVISIBLE){
            b.setVisibility(View.VISIBLE);

        }else{
            b.setVisibility(View.INVISIBLE);

        }
    }

    public  void onclickSeconnecter(View v){
        startActivity(new Intent(this,ConnexionInscription.class));
    }


    @Override
    public void onBackPressed() {

    }


}