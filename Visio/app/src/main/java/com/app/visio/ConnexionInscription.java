package com.app.visio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnexionInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_inscription);
        getSupportFragmentManager().executePendingTransactions();

    }

    @Override
    protected void onStart() {
        super.onStart();
       clicSeconnecter("inscription");
    }

    private void clicSeconnecter(String onglet) {

        if(onglet.compareTo("seconnecter")==0){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragContainer, Connexion.class, null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            Button c=getSupportFragmentManager().findFragmentById(R.id.fragContainer).getView().findViewById(R.id.sinscrire);
            c.setOnClickListener((V)->clicSeconnecter( "inscription"));
            Connexion f= (Connexion) getSupportFragmentManager().findFragmentById(R.id.fragContainer);
            Button continuer=f.getView().findViewById(R.id.continuer);
            continuer.setOnClickListener(V ->f.onClickContinuer(V,this));
        }else if(onglet.compareTo("inscription")==0){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragContainer, Inscription.class, null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();

            Inscription f= (Inscription) getSupportFragmentManager().findFragmentById(R.id.fragContainer);

            Button c=f.getView().findViewById(R.id.seconnecter);
            c.setOnClickListener((V)->clicSeconnecter("seconnecter"));

            Button continuer=f.getView().findViewById(R.id.continuer);
            continuer.setOnClickListener(V ->f.onClickContinuer(V,this));
            EditText mail =f.getView().findViewById(R.id.mail);

            mail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Cette méthode est appelée avant que le texte soit modifié
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Cette méthode est appelée pendant que l'utilisateur écrit dans l'EditText
                    mail.setTextColor(Color.BLACK);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Cette méthode est appelée après que le texte a été modifié
                }
            });

        }


    }

    @Override
    public void onBackPressed() {

    }
}