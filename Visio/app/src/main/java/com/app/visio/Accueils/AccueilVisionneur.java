package com.app.visio.Accueils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.visio.R;
import com.app.visio.model.gestion.Compte;
import com.app.visio.model.gestion.ElmtListeFilm;
import com.app.visio.model.gestion.ElmtListeSerie;
import com.app.visio.model.gestion.Film;
import com.app.visio.model.gestion.Routeur;
import com.app.visio.model.gestion.Serie;
import com.app.visio.model.gestion.Visionneur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccueilVisionneur extends AppCompatActivity {
    Compte C;
    Visionneur V;
    ArrayList<Serie> seriesandanim;
    ArrayList<Film> filmsandcourtmetrages;
    ArrayList<ElmtListeSerie> seriesandanimInListe;
    ArrayList<ElmtListeFilm> filmsandcourtmetragesInListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_visionneur);
        C = new Compte(Integer.valueOf(this.getIntent().getStringExtra("Idc")));
        Routeur r = new Routeur(this);
        r.execute("getVisionneur", String.valueOf(C.getIdC()));
        filmsandcourtmetrages = new ArrayList<>();
        filmsandcourtmetragesInListe = new ArrayList<ElmtListeFilm>();
        seriesandanim = new ArrayList<>();
        seriesandanimInListe = new ArrayList<ElmtListeSerie>();
        Routeur r1 = new Routeur(this);
        r1.execute("getListeDeVisionnage", String.valueOf(C.getIdC()));
//
//


    }


    public void setVisionneur(JSONObject data) {
        try {
            int idC = Integer.parseInt(data.getString("idC"));
            String genre1 = data.getString("genre1");
            String genre2 = data.getString("genre2");
            String genre3 = data.getString("genre3");
            boolean bloque = Boolean.parseBoolean(data.getString("bloque"));
            V = new Visionneur(genre1, genre2, genre3, bloque);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }


    public void AfficherContenus() {
        Routeur r = new Routeur(this);
        r.execute("getAll");

    }

    @Override
    protected void onStart() {
        super.onStart();

        AfficherContenus();
    }


    @Override
    public void onBackPressed() {

    }

    public void updateAll(JSONObject o) {
        JSONArray films = new JSONArray();
        JSONArray series = new JSONArray();
        if (o != null) {
            if (o.has("films")) {
                try {
                    films = o.getJSONArray("films");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < films.length(); i++) {
                    JSONObject filmObj = null;
                    try {
                        filmObj = films.getJSONObject(i);
                        String idF = filmObj.getString("idF");
                        String titre = filmObj.getString("titre");
                        String genre1 = filmObj.getString("genre1");
                        String genre2 = filmObj.getString("genre2");
                        String genre3 = filmObj.getString("genre3");
                        String courtmetrage = filmObj.getString("courtmetrage");
                        String lien = filmObj.getString("lien");
                        Film f = new Film(Integer.valueOf(idF), titre, genre1, genre2, genre3, (courtmetrage.compareTo("1") == 0), lien, C.getIdC());
                        filmsandcourtmetrages.add(f);
                    } catch (JSONException e) {
                    }
                }
            }
            if (o.has("series")) {
                try {
                    series = o.getJSONArray("series");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < series.length(); i++) {
                    JSONObject serieObj = null;

                    try {
                        serieObj = series.getJSONObject(i);

                        String idS = serieObj.getString("idS");
                        String titre = serieObj.getString("titre");
                        String genre1 = serieObj.getString("genre1");
                        String genre2 = serieObj.getString("genre2");
                        String genre3 = serieObj.getString("genre3");
                        String anime = serieObj.getString("anime");
                        String synopsis = serieObj.getString("synopsis");

                        Serie s = new Serie(Integer.valueOf(idS), titre, genre1, genre2, genre3, (anime.compareTo("1") == 0), synopsis, C.getIdC());
                        seriesandanim.add(s);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        }
        liste();
    }


    public void updateListeVisionnage(JSONObject o) {
        JSONArray films = new JSONArray();
        JSONArray series = new JSONArray();
        if (o != null) {
            if (o.has("films")) {
                try {
                    films = o.getJSONArray("films");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < films.length(); i++) {
                    JSONObject filmObj = null;
                    try {
                        filmObj = films.getJSONObject(i);
                        String idELF = filmObj.getString("idELF");
                        String idC = filmObj.getString("idC");
                        String Like = filmObj.getString("Like");
                        String idF = filmObj.getString("idF");

                        ElmtListeFilm f = new ElmtListeFilm(Integer.valueOf(idELF), Integer.parseInt(idC), Like.compareTo("1") == 0, Integer.parseInt(idF));
                        filmsandcourtmetragesInListe.add(f);
                    } catch (JSONException e) {
                    }
                }
            }
            if (o.has("series")) {
                try {
                    series = o.getJSONArray("series");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < series.length(); i++) {
                    JSONObject serieObj = null;

                    try {
                        serieObj = series.getJSONObject(i);

                        String idELS = serieObj.getString("idELS");
                        String idC = serieObj.getString("idC");
                        String like = serieObj.getString("like");
                        String saisonActuelle = serieObj.getString("saisonActuelle");
                        String epActuel = serieObj.getString("epActuel");
                        String idS = serieObj.getString("idS");

                        ElmtListeSerie s = new ElmtListeSerie(Integer.valueOf(idELS), Integer.parseInt(idC), like.compareTo("1") == 0, Integer.parseInt(saisonActuelle), Integer.parseInt(epActuel), Integer.parseInt(idS));
                        seriesandanimInListe.add(s);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
        afficherSelonFiltre(2);

    }

    public void liste() {
        int i = 0;

        while (i < filmsandcourtmetrages.size() + seriesandanim.size()) {
            ConstraintLayout cs = this.findViewById(R.id.ensemble);
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
            if (i < filmsandcourtmetrages.size()) {
                if (filmsandcourtmetrages.get(i).isCourtmetrage() == false) {
                    String customColor = "#94A2D1";
                    int color = Color.parseColor(customColor);
                    layout.setBackgroundColor(color);
                } else {
                    String customColor = "#CE7DC6";
                    int color = Color.parseColor(customColor);
                    layout.setBackgroundColor(color);
                }
            } else if (i >= filmsandcourtmetrages.size()) {
                if (seriesandanim.get(i - filmsandcourtmetrages.size()).isAnime() == false) {
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


            TextView titre = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            titre.setId((int) 10 * i + 4);
            titre.setTextColor(Color.BLACK);
            if (i < filmsandcourtmetrages.size()) {
                titre.setText(filmsandcourtmetrages.get(i).getTitre());
            } else if (i >= filmsandcourtmetrages.size()) {
                titre.setText(seriesandanim.get(i - filmsandcourtmetrages.size()).getTitre());
            }
            cs.addView(titre);

            TextView genre1 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre1.setId((int) 10 * i + 5);
            genre1.setTextColor(Color.BLACK);
            if (i < filmsandcourtmetrages.size()) {
                genre1.setText(filmsandcourtmetrages.get(i).getGenre1());
            } else if (i >= filmsandcourtmetrages.size()) {
                genre1.setText(seriesandanim.get(i - filmsandcourtmetrages.size()).getGenre1());
            }
            cs.addView(genre1);

            TextView genre2 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre2.setId((int) 10 * i + 6);
            genre2.setTextColor(Color.BLACK);
            if (i < filmsandcourtmetrages.size()) {
                genre2.setText(filmsandcourtmetrages.get(i).getGenre2());
            } else if (i >= filmsandcourtmetrages.size()) {
                genre2.setText(seriesandanim.get(i - filmsandcourtmetrages.size()).getGenre2());
            }
            cs.addView(genre2);

            TextView genre3 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre3.setId((int) 10 * i + 7);
            genre3.setTextColor(Color.BLACK);
            if (i < filmsandcourtmetrages.size()) {
                genre3.setText(filmsandcourtmetrages.get(i).getGenre3());
            } else if (i >= filmsandcourtmetrages.size()) {
                genre3.setText(seriesandanim.get(i - filmsandcourtmetrages.size()).getGenre3());
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

            CS.applyTo(cs);
            i++;
        }
    }

    void afficherSelonFiltre() {
        ConstraintLayout cs = this.findViewById(R.id.filtre);

        ArrayList<Film> donneesFilm = new ArrayList<>();
        ArrayList<Serie> donneesSerie = new ArrayList<>();
        ArrayList<Integer> idfsInliste = new ArrayList<>();
        for (int i = 0; i < filmsandcourtmetragesInListe.size(); i++) {
            idfsInliste.add(filmsandcourtmetragesInListe.get(i).getIdF());
        }

        for (int i = 0; i < filmsandcourtmetrages.size(); i++) {
            if (idfsInliste.contains(filmsandcourtmetrages.get(i).getIdF())) {
                donneesFilm.add(filmsandcourtmetrages.get(i));
            }
        }
        for (int i = 0; i < donneesFilm.size(); i++) {
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
            String customColor = "";
            if (!donneesFilm.get(i).isCourtmetrage()) {
                customColor = "#94A2D1";
            } else if (donneesFilm.get(i).isCourtmetrage()) {
                customColor = "#CE7DC6";
            }
            int color = Color.parseColor(customColor);
            layout.setBackgroundColor(color);
            layout.setId((int) 10 * i + 1);
            cs.addView(layout);
            TextView titre = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            titre.setId((int) 10 * i + 4);
            titre.setTextColor(Color.BLACK);
            titre.setText(donneesFilm.get(i).getTitre());
            cs.addView(titre);
            TextView genre1 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre1.setId((int) 10 * i + 5);
            genre1.setTextColor(Color.BLACK);
            genre1.setText(donneesFilm.get(i).getGenre1());
            cs.addView(genre1);
            TextView genre2 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre2.setId((int) 10 * i + 6);
            genre2.setTextColor(Color.BLACK);
            genre2.setText(donneesFilm.get(i).getGenre2());
            cs.addView(genre2);
            TextView genre3 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre3.setId((int) 10 * i + 7);
            genre3.setTextColor(Color.BLACK);
            genre3.setText(donneesFilm.get(i).getGenre2());
            cs.addView(genre3);

            ConstraintSet CS = new ConstraintSet();
            CS.clone(cs);
            CS.connect(layout.getId(), ConstraintSet.TOP, cs.getId(), ConstraintSet.TOP, 150);
            CS.connect(layout.getId(), ConstraintSet.LEFT, cs.getId(), ConstraintSet.LEFT, 100 + 500 * i);

            CS.connect(titre.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 100);
            CS.connect(titre.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre1.getId(), ConstraintSet.TOP, titre.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre2.getId(), ConstraintSet.TOP, genre1.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre3.getId(), ConstraintSet.TOP, genre2.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre3.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.applyTo(cs);
        }

        ArrayList<Integer> idSInliste = new ArrayList<>();
        for (int i = 0; i < seriesandanimInListe.size(); i++) {
            idSInliste.add(seriesandanimInListe.get(i).getIdS());
        }

        for (int i = 0; i < seriesandanim.size(); i++) {
            if (idSInliste.contains(seriesandanimInListe.get(i).getIdS())) {
                donneesSerie.add(seriesandanim.get(i));
            }
        }

        for (int i = 0; i < donneesSerie.size(); i++) {
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
            String customColor = "";
            if (!donneesSerie.get(i).isAnime()) {
                customColor = "#A2CC93";
            } else if (donneesSerie.get(i).isAnime()) {
                customColor = "#E9A6A2";
            }
            int color = Color.parseColor(customColor);
            layout.setBackgroundColor(color);
            layout.setId((int) 10 * i + 1);
            cs.addView(layout);
            TextView titre = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            titre.setId((int) 10 * i + 4);
            titre.setTextColor(Color.BLACK);
            titre.setText(donneesSerie.get(i).getTitre());
            cs.addView(titre);
            TextView genre1 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre1.setId((int) 10 * i + 5);
            genre1.setTextColor(Color.BLACK);
            genre1.setText(donneesSerie.get(i).getGenre1());
            cs.addView(genre1);
            TextView genre2 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre2.setId((int) 10 * i + 6);
            genre2.setTextColor(Color.BLACK);
            genre2.setText(donneesSerie.get(i).getGenre2());
            cs.addView(genre2);
            TextView genre3 = new TextView(this);
            //nb.setLayoutParams(new LinearLayout.LayoutParams(800, 600));
            genre3.setId((int) 10 * i + 7);
            genre3.setTextColor(Color.BLACK);
            genre3.setText(donneesSerie.get(i).getGenre2());
            cs.addView(genre3);

            ConstraintSet CS = new ConstraintSet();
            CS.clone(cs);
            CS.connect(layout.getId(), ConstraintSet.TOP, cs.getId(), ConstraintSet.TOP, 150);
            CS.connect(layout.getId(), ConstraintSet.LEFT, cs.getId(), ConstraintSet.LEFT, 100 + 500 * i);

            CS.connect(titre.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 100);
            CS.connect(titre.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre1.getId(), ConstraintSet.TOP, titre.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre2.getId(), ConstraintSet.TOP, genre1.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.connect(genre3.getId(), ConstraintSet.TOP, genre2.getId(), ConstraintSet.TOP, 40);
            CS.connect(genre3.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 10);

            CS.applyTo(cs);
        }
    }


}



    }