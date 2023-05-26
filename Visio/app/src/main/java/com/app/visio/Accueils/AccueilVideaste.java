package com.app.visio.Accueils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.visio.ConnexionInscription;
import com.app.visio.Header;
import com.app.visio.R;
import com.app.visio.model.gestion.Film;
import com.app.visio.model.gestion.Routeur;
import com.app.visio.model.gestion.Serie;
import com.app.visio.model.gestion.Videaste;
import com.app.visio.model.gestion.Compte;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class AccueilVideaste extends AppCompatActivity {
    Compte C;
    Videaste V;
    ArrayList<Film> filmsoucourtmetragepublies;
    ArrayList<Serie> seriesouanimespublies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_videaste);
        C = new Compte(Integer.valueOf(this.getIntent().getStringExtra("Idc")));
        filmsoucourtmetragepublies = new ArrayList<>();
        seriesouanimespublies = new ArrayList<>();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Header h = (Header) this.getSupportFragmentManager().findFragmentById(R.id.header);
        ImageButton compte = h.getView().findViewById(R.id.compte);
        compte.setOnClickListener((v) -> onClickCompte(v));
        updateAll();
    }

    public void UpdateMyContenuFilm(JSONObject o) {

        JSONArray filmsArray = new JSONArray();
        try {
            if (o != null) {
                filmsArray = o.getJSONArray("films");
            }

            for (int i = 0; i < filmsArray.length(); i++) {
                JSONObject filmObj = null;
                try {
                    filmObj = filmsArray.getJSONObject(i);
                    String idF = filmObj.getString("idF");
                    String titre = filmObj.getString("titre");
                    String genre1 = filmObj.getString("genre1");
                    String genre2 = filmObj.getString("genre2");
                    String genre3 = filmObj.getString("genre3");
                    String courtmetrage = filmObj.getString("courtmetrage");
                    String lien = filmObj.getString("lien");

                    Film f = new Film(Integer.valueOf(idF), titre, genre1, genre2, genre3, (courtmetrage.compareTo("1") == 0), lien, C.getIdC());
                    filmsoucourtmetragepublies.add(f);
                } catch (JSONException e) {
                }
            }


        } catch (JSONException e) {
        }


//        AfficherAll();

    }

    private void AfficherAll() {

        int i = 0;

        while (i < filmsoucourtmetragepublies.size() + seriesouanimespublies.size()) {

            ConstraintLayout cs = this.findViewById(R.id.ensemble);
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
            if (i < filmsoucourtmetragepublies.size()) {
                if (filmsoucourtmetragepublies.get(i).isCourtmetrage() == false) {
                    String customColor = "#94A2D1";
                    int color = Color.parseColor(customColor);
                    layout.setBackgroundColor(color);
                } else {
                    String customColor = "#CE7DC6";
                    int color = Color.parseColor(customColor);
                    layout.setBackgroundColor(color);
                }
            } else if (i >= filmsoucourtmetragepublies.size()) {
                if (seriesouanimespublies.get(i - filmsoucourtmetragepublies.size()).isAnime() == false) {
                    String customColor = "#A2CC93";
                    int color = Color.parseColor(customColor);
                    layout.setBackgroundColor(color);
                } else {
                    String customColor = "#E9A6A2";
                    int color = Color.parseColor(customColor);
                    layout.setBackgroundColor(color);
                }
            }
            layout.setId((int) 10 * i + 3);
            cs.addView(layout);

            ImageButton modifier = new ImageButton(this);
            //com.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            modifier.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            modifier.setImageResource(R.drawable.modifier);
            modifier.setScaleX(0.25f);
            modifier.setScaleY(0.25f);
            modifier.setId((int) 10 * i + 1);
            modifier.setOnClickListener((v) -> onClickUpdate(v));
            cs.addView(modifier);

            ImageButton corbeille = new ImageButton(this);
            //com.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            corbeille.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            corbeille.setImageResource(R.drawable.corbeille);
            corbeille.setScaleX(0.25f);
            corbeille.setScaleY(0.25f);
            corbeille.setId((int) 10 * i + 2);

//            corbeille.setOnClickListener((v) -> onClickDelete(v));

            cs.addView(corbeille);

            TextView titre = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            titre.setId((int) 10 * i + 4);
            titre.setTextColor(Color.BLACK);
            if (i < filmsoucourtmetragepublies.size()) {
                titre.setText(filmsoucourtmetragepublies.get(i).getTitre());
            } else if (i >= filmsoucourtmetragepublies.size()) {
                titre.setText(seriesouanimespublies.get(i - filmsoucourtmetragepublies.size()).getTitre());
            }
            cs.addView(titre);

            TextView genre1 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre1.setId((int) 10 * i + 5);
            genre1.setTextColor(Color.BLACK);
            if (i < filmsoucourtmetragepublies.size()) {
                genre1.setText(filmsoucourtmetragepublies.get(i).getGenre1());
            } else if (i >= filmsoucourtmetragepublies.size()) {
                genre1.setText(seriesouanimespublies.get(i - filmsoucourtmetragepublies.size()).getGenre1());
            }
            cs.addView(genre1);

            TextView genre2 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre2.setId((int) 10 * i + 6);
            genre2.setTextColor(Color.BLACK);
            if (i < filmsoucourtmetragepublies.size()) {
                genre2.setText(filmsoucourtmetragepublies.get(i).getGenre2());
            } else if (i >= filmsoucourtmetragepublies.size()) {
                genre2.setText(seriesouanimespublies.get(i - filmsoucourtmetragepublies.size()).getGenre2());
            }
            cs.addView(genre2);

            TextView genre3 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre3.setId((int) 10 * i + 7);
            genre3.setTextColor(Color.BLACK);
            if (i < filmsoucourtmetragepublies.size()) {
                genre3.setText(filmsoucourtmetragepublies.get(i).getGenre3());
            } else if (i >= filmsoucourtmetragepublies.size()) {
                genre3.setText(seriesouanimespublies.get(i - filmsoucourtmetragepublies.size()).getGenre3());
            }
            cs.addView(genre3);


            ConstraintSet CS = new ConstraintSet();
            CS.clone(cs);

            if (i % 2 == 0) {
                CS.connect(layout.getId(), ConstraintSet.TOP, cs.getId(), ConstraintSet.TOP, 100 + 500 * i / 2);
                CS.connect(layout.getId(), ConstraintSet.LEFT, cs.getId(), ConstraintSet.LEFT, 100);
            } else if (i % 2 == 1) {
                CS.connect(layout.getId(), ConstraintSet.TOP, cs.getId(), ConstraintSet.TOP, 100 + 500 * (i - 1) / 2);
                CS.connect(layout.getId(), ConstraintSet.LEFT, cs.getId(), ConstraintSet.LEFT, 600);
            }

            CS.connect(titre.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 150);
            CS.connect(titre.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre1.getId(), ConstraintSet.TOP, titre.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre2.getId(), ConstraintSet.TOP, genre1.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre3.getId(), ConstraintSet.TOP, genre2.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre3.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(modifier.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, -100);
            CS.connect(modifier.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 100);

            CS.connect(corbeille.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, -100);
            CS.connect(corbeille.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 200);
            CS.applyTo(cs);

            i++;
        }

    }

    public void onClickCompte(View v) {
        LinearLayout deco = this.findViewById(R.id.buttonDeco);
        if (deco.getVisibility() == View.VISIBLE) {
            deco.setVisibility(View.INVISIBLE);
        } else {
            deco.setVisibility(View.VISIBLE);
        }
    }


    public void onClickButtonAjouterContenu(View v) {
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.choixtype_contenu);
        Button c = d.findViewById(R.id.continuer3);
        RadioGroup g = d.findViewById(R.id.choixtypeGroup);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton r = d.findViewById(g.getCheckedRadioButtonId());
                d.dismiss();
                DialogdeSaisieDinfos(String.valueOf(r.getText()));
            }
        });
        d.show();
    }

    public void DialogdeSaisieDinfos(String type) {
        Dialog d = new Dialog(this);
        switch (type) {
            case "Film":
                d.setContentView(R.layout.ajout_film);
                break;
            case "Court-métrage":
                d.setContentView(R.layout.ajout_courtmetrage);
                break;
            case "Série":
                d.setContentView(R.layout.ajout_serie);
                break;
            case "Animé":
                d.setContentView(R.layout.ajout_anime);

                break;
        }
        Button b = d.findViewById(R.id.continuer2);
        b.setOnClickListener((v) -> AjoutContenu(d, type));
        d.show();
    }

    public void AjoutContenu(Dialog d, String type) {
        Routeur r = new Routeur(this);
        EditText titre = d.findViewById(R.id.inputTitre);
        EditText genre1 = d.findViewById(R.id.inputGenre1);
        EditText genre2 = d.findViewById(R.id.inputGenre2);
        EditText genre3 = d.findViewById(R.id.inputGenre3);
        EditText lien = d.findViewById(R.id.inputLien);

        switch (type) {
            case "Film":
                r.execute("ajoutFilm", titre.getText().toString(), genre1.getText().toString(), genre2.getText().toString(), genre3.getText().toString(), lien.getText().toString(), String.valueOf(C.getIdC()));
                break;
            case "Court-métrage":
                r.execute("ajoutCourtmetrage", titre.getText().toString(), genre1.getText().toString(), genre2.getText().toString(), genre3.getText().toString(), lien.getText().toString(), String.valueOf(C.getIdC()));
                break;
            case "Série":
                r.execute("ajoutSerie", titre.getText().toString(), genre1.getText().toString(), genre2.getText().toString(), genre3.getText().toString(), lien.getText().toString(), String.valueOf(C.getIdC()));

                break;
            case "Animé":
                r.execute("ajoutAnime", titre.getText().toString(), genre1.getText().toString(), genre2.getText().toString(), genre3.getText().toString(), lien.getText().toString(), String.valueOf(C.getIdC()));

                break;
        }
        d.dismiss();

    }

    public void onClickDeconnexion(View v) {
        try {
            JSONObject infosconnexion = new JSONObject("{}");
            Writer output = null;
            File file = new File(this.getApplicationContext().getFilesDir(), "connexion.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(infosconnexion.toString());
            output.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, ConnexionInscription.class));
    }

    public void onClickDelete(View v) {

//        int idInArrayList = v.getId();
//
//        idInArrayList = idInArrayList - (idInArrayList % 10);
//        Routeur Rout = new Routeur(this);
        System.out.println("yoooo");
        /*if(idInArrayList>filmsoucourtmetragepublies.size()-1){
            Rout.execute("deleteSerie", String.valueOf(seriesouanimespublies.get(idInArrayList-filmsoucourtmetragepublies.size()).getIdS()));

        }else{
            Rout.execute("deleteFilm", String.valueOf(filmsoucourtmetragepublies.get(idInArrayList).getIdF()));

        }*/
    }

    public void onClickUpdate(View v) {

        Dialog d = new Dialog(this);
        d.setContentView(R.layout.choixtype_contenu);
        Button c = d.findViewById(R.id.continuer3);
        RadioGroup g = d.findViewById(R.id.choixtypeGroup);
        int idFilmInArrayList = v.getId();
        idFilmInArrayList = idFilmInArrayList - (idFilmInArrayList % 10);
        idFilmInArrayList--;
        EditText titre = d.findViewById(R.id.inputTitre);
        EditText genre1 = d.findViewById(R.id.inputGenre1);
        EditText genre2 = d.findViewById(R.id.inputGenre2);
        EditText genre3 = d.findViewById(R.id.inputGenre3);
        EditText lien = d.findViewById(R.id.inputLien);
        titre.setText(filmsoucourtmetragepublies.get(idFilmInArrayList).getTitre());
        genre1.setText(filmsoucourtmetragepublies.get(idFilmInArrayList).getGenre1());
        genre2.setText(filmsoucourtmetragepublies.get(idFilmInArrayList).getGenre2());
        genre3.setText(filmsoucourtmetragepublies.get(idFilmInArrayList).getGenre3());
        lien.setText(filmsoucourtmetragepublies.get(idFilmInArrayList).getLien());
        Routeur Rout = new Routeur(this);

        int finalIdFilmInArrayList = idFilmInArrayList;
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton r = d.findViewById(g.getCheckedRadioButtonId());
                d.dismiss();
                DialogdeSaisieDinfos(String.valueOf(r.getText()));
                EditText titre = d.findViewById(R.id.inputTitre);
                EditText genre1 = d.findViewById(R.id.inputGenre1);
                EditText genre2 = d.findViewById(R.id.inputGenre2);
                EditText genre3 = d.findViewById(R.id.inputGenre3);
                EditText lien = d.findViewById(R.id.inputLien);

                Rout.execute("updateFilm", titre.getText().toString(), genre1.getText().toString(), genre2.getText().toString(), genre3.getText().toString(), lien.getText().toString(), String.valueOf(filmsoucourtmetragepublies.get(finalIdFilmInArrayList).getIdF()));
                Rout.execute("getFilmsOfAuthor", String.valueOf(C.getIdC()));
            }
        });
        d.show();
    }

    public void UpdateMyContenuSerie(JSONObject o) {

        JSONArray seriesArray = new JSONArray();
        try {
            if (o != null) {
                seriesArray = o.getJSONArray("series");
            }
            for (int i = 0; i < seriesArray.length(); i++) {
                JSONObject serieObj = null;

                serieObj = seriesArray.getJSONObject(i);

                String idS = serieObj.getString("idS");
                String titre = serieObj.getString("titre");
                String genre1 = serieObj.getString("genre1");
                String genre2 = serieObj.getString("genre2");
                String genre3 = serieObj.getString("genre3");
                String anime = serieObj.getString("anime");
                String synopsis = serieObj.getString("synopsis");

                Serie s = new Serie(Integer.valueOf(idS), titre, genre1, genre2, genre3, (anime.compareTo("1") == 0), synopsis, C.getIdC());
                seriesouanimespublies.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AfficherAll();
    }

    public void updateAll() {
        filmsoucourtmetragepublies.clear();
        seriesouanimespublies.clear();

        Routeur r = new Routeur(this);
        r.execute("getFilmsOfAuthor", String.valueOf(C.getIdC()));
        Routeur r1 = new Routeur(this);
        r1.execute("getSeriesOfAuthor", String.valueOf(C.getIdC()));
    }
}