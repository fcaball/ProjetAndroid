package com.app.visio.Accueils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.visio.R;

public class AccueilVisionneur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_visionneur);
        liste();
    }

    public void liste(){
        ConstraintLayout cs = this.findViewById(R.id.contrainte);
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(400, 600));
        layout.setBackgroundColor(Color.BLUE);
        layout.setId((int)1);
        cs.addView(layout);

        TextView type = new TextView(this);
        type.setLayoutParams(new LinearLayout.LayoutParams(400, 600));
        type.setId((int)2);
        type.setTextColor(Color.WHITE);
        type.setText("Type : Film");
        cs.addView(type);

        TextView genre = new TextView(this);
        genre.setLayoutParams(new LinearLayout.LayoutParams(400, 600));
        genre.setId((int)3);
        genre.setTextColor(Color.WHITE);
        genre.setText("Genre : Conte");
        cs.addView(genre);

        TextView synopsis = new TextView(this);
        synopsis.setLayoutParams(new LinearLayout.LayoutParams(400, 600));
        synopsis.setId((int)4);
        synopsis.setTextColor(Color.WHITE);
        synopsis.setText("Synopsis : Il était une fois ...");
        cs.addView(synopsis);

        ConstraintSet CS = new ConstraintSet() ;
        CS.clone(cs);

        TextView listeVisionnage = this.findViewById(R.id.listevisionnage);

        CS.connect(layout.getId(), ConstraintSet.TOP, listeVisionnage.getId(), ConstraintSet.BOTTOM, 20);
        CS.connect(layout.getId(), ConstraintSet.LEFT, listeVisionnage.getId(), ConstraintSet.LEFT, 0);
        CS.connect(type.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 20);
        CS.connect(type.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 20);
        CS.connect(genre.getId(), ConstraintSet.TOP, type.getId(), ConstraintSet.TOP, 40);
        CS.connect(genre.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 20);
        CS.connect(synopsis.getId(), ConstraintSet.TOP, genre.getId(), ConstraintSet.TOP, 40);
        CS.connect(synopsis.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 20);
        CS.applyTo(cs);
    }

    @Override
    public void onBackPressed() {

    }
}