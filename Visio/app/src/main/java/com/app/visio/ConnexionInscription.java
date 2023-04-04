package com.app.visio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Inscription c= (Inscription) getSupportFragmentManager().findFragmentById(R.id.fragContainer);
        Button b=c.getView().findViewById(R.id.seconnecter);
        b.setOnClickListener((v)->clicSeconnecter(v,"seconnecter"));
    }

    private void clicSeconnecter(View v, String onglet) {

        if(onglet.compareTo("seconnecter")==0){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragContainer, Connexion.class, null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            Button c=getSupportFragmentManager().findFragmentById(R.id.fragContainer).getView().findViewById(R.id.sinscrire);
            c.setOnClickListener((V)->clicSeconnecter(V, "inscription"));
        }else if(onglet.compareTo("inscription")==0){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragContainer, Inscription.class, null)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            Button c=getSupportFragmentManager().findFragmentById(R.id.fragContainer).getView().findViewById(R.id.seconnecter);
            c.setOnClickListener((V)->clicSeconnecter(V,"seconnecter"));
        }


    }
}