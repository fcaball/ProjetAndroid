package com.app.visio.model.gestion;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.visio.Accueils.Accueil;
import com.app.visio.Accueils.AccueilVideaste;
import com.app.visio.Accueils.AccueilVisionneur;
import com.app.visio.Connexion;
import com.app.visio.Inscription;
import com.app.visio.R;
import com.app.visio.model.requetage.AccessFilm;
import com.app.visio.model.requetage.AccessSerie;
import com.app.visio.model.requetage.AccessUsers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Routeur extends AsyncTask<String, Void, Object> {
    AppCompatActivity v;
    String var;

    public Routeur(AppCompatActivity view) {
        this.v = view;
    }

    @Override
    protected void onPreExecute() {

    }


    @Override
    protected Object doInBackground(String... strings) {
        AccessUsers userAcces = new AccessUsers();
        AccessFilm filmAcces = new AccessFilm();
        AccessSerie serieAcces = new AccessSerie();
        var = strings[0];
        switch (strings[0]) {
            case "verifyConnexion":
                return userAcces.verifyConnexion(strings[1], strings[2]);

            case "createUser":
                return userAcces.createUser(strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], strings[8], strings[9], strings[10]);

            case "verifyLogin":
                return userAcces.verifyLogin(strings[1]);
            case "sendmail":
                userAcces.sendMail(strings[1], strings[2]);
                return "";
            case "ajoutFilm":
                return filmAcces.createFilm(strings[1], strings[2],strings[3],strings[4],strings[5],false,Integer.valueOf(strings[6]));
            case "deleteFilm":
                return filmAcces.deleteFilm(Integer.parseInt(strings[1]));
            case "deleteSerie":
                return serieAcces.deleteSerie(Integer.parseInt(strings[1]));
            case "updateFilm":
                return filmAcces.updateFilm(strings[1], strings[2],strings[3],strings[4],strings[5],false,Integer.valueOf(strings[6]));
            case "ajoutCourtmetrage":
                return filmAcces.createFilm(strings[1], strings[2],strings[3],strings[4],strings[5],true,Integer.valueOf(strings[6]));
            case "ajoutAnime":
                return serieAcces.createSerie(strings[1], strings[2],strings[3],strings[4],strings[5],true,Integer.valueOf(strings[6]));
            case "ajoutSerie":
                return serieAcces.createSerie(strings[1], strings[2],strings[3],strings[4],strings[5],false,Integer.valueOf(strings[6]));
            case "getFilmsOfAuthor":
                return filmAcces.getFilmsOfAuthor(strings[1]);
            case "getSeriesOfAuthor":
                return serieAcces.getSeriesOfAuthor(strings[1]);
            case "getVisionneur":
                return userAcces.getVisionneur(strings[1]);
            case "getAll" :
                return userAcces.getAll();
            case "getListeDeVisionnage":
                return userAcces.getListeDeVisionnage(strings[1]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (var.compareTo("verifyConnexion") == 0) {
            String[] result = (String[]) o;
            Connexion connexion = (Connexion) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);
            EditText login=connexion.getView().findViewById(R.id.login);
            EditText mdp=connexion.getView().findViewById(R.id.mdp);
            if (!(result[0].compareTo("Aucun") == 0)) {
                //----------------ENREGISTREMENT DE LA CONNEXION------------
                try {
                    JSONObject infosconnexion=new JSONObject("{\"login\":\""+login.getText().toString()+"\",\"mdp\":\""+mdp.getText().toString()+"\"}");
                    Writer output = null;
                    File file = new File(v.getApplicationContext().getFilesDir(),"connexion.json");
                    output = new BufferedWriter(new FileWriter(file));
                    output.write(infosconnexion.toString());
                    output.close();
                } catch (JSONException | IOException e) {
                    throw new RuntimeException(e);
                }
                //----------------------------------------------------------

                if (result[0].compareTo("Visionneur") == 0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVisionneur.class);
                    intent.putExtra("Idc", result[1]);
                    v.startActivity(intent);
                } else if (result[0].compareTo("Videaste") == 0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVideaste.class);
                    intent.putExtra("Idc", result[1]);

                    v.startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getApplicationContext(), Accueil.class);
                    intent.putExtra("Idc", result[1]);
                    intent.putExtra("UserType", result);
                    v.startActivity(intent);
                }
            } else {

                ProgressBar progressBar = connexion.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
                TextView error = connexion.getView().findViewById(R.id.ErrorMessage);
                error.setVisibility(View.VISIBLE);
            }
        }
        else if (var.compareTo("createUser") == 0) {
            String[] result = (String[]) o;
            String type = result[2];
            String res = result[0];

            if (res.compareTo("true") == 0) {
                if (type.compareTo("Visionneur") == 0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVisionneur.class);
                    intent.putExtra("Idc", result[1]);

                    v.startActivity(intent);
                } else if (type.compareTo("Videaste") == 0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVideaste.class);
                    intent.putExtra("Idc", result[1]);

                    v.startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getApplicationContext(), Accueil.class);
                    intent.putExtra("Idc", result[1]);

                    intent.putExtra("UserType", result);
                    v.startActivity(intent);
                }

            } else {
                Inscription connexion = (Inscription) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);
                ProgressBar progressBar = connexion.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
                TextView error = connexion.getView().findViewById(R.id.ErrorMessage);
                error.setText("une erreur est survenue veuillez rééssayez ultérieurement");
                error.setVisibility(View.VISIBLE);
            }
        }
        else if (var.compareTo("verifyLogin") == 0) {
            String result = (String) o;

            if (result.compareTo("login_duplicate") == 0) {
                Inscription connexion = (Inscription) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);
                ProgressBar progressBar = connexion.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
                TextView error = connexion.getView().findViewById(R.id.ErrorMessage);
                error.setText("Ce nom d'utilisateur est déja utilisé");
                error.setVisibility(View.VISIBLE);
            } else {

                String digits = "0123456789";

                Random random = new Random();
                StringBuilder codeBuilder = new StringBuilder(4);

                for (int i = 0; i < 4; i++) {
                    // Génère un index aléatoire dans la plage des chiffres possibles
                    int randomIndex = random.nextInt(digits.length());
                    // Récupère le chiffre correspondant à l'index aléatoire
                    char randomDigit = digits.charAt(randomIndex);
                    // Ajoute le chiffre au code
                    codeBuilder.append(randomDigit);
                }
                Inscription inscr = (Inscription) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);
                EditText mail = inscr.getView().findViewById(R.id.mail);

                Routeur r = new Routeur(v);
                r.execute("sendmail", mail.getText().toString(), codeBuilder.toString());

                Dialog dialog = new Dialog(v);
                dialog.setContentView(R.layout.confirmation_mail);

                Button positiveButton = (Button) dialog.findViewById(R.id.valider);
                Button renvoyer = (Button) dialog.findViewById(R.id.renvoyer);
                TextView erreur = dialog.findViewById(R.id.erreurCode);
                EditText code = dialog.findViewById(R.id.code);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (code.getText().toString().compareTo(codeBuilder.toString()) == 0) {
                            dialog.dismiss();
                            AfterMailConfirmation();
                        } else {
                            erreur.setVisibility(View.VISIBLE);
                        }
                    }
                });

                renvoyer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Routeur r1=new Routeur(v);
                        r1.execute("sendmail", mail.getText().toString(), codeBuilder.toString());
                    }
                });

                dialog.show();


            }
        }
        else if(var.compareTo("ajoutFilm")==0 || var.compareTo("ajoutCourtmetrage")==0 || var.compareTo("ajoutSerie")==0 || var.compareTo("ajoutAnime")==0){
            boolean res= (boolean) o;

            if(res){
                Toast.makeText(v.getApplicationContext(),"L'ajout a été effectué",Toast.LENGTH_LONG).show();
                AccueilVideaste V= (AccueilVideaste) v;

                V.updateAll();
            }else{
                Toast.makeText(v.getApplicationContext(),"L'ajout n'a pas été effectué",Toast.LENGTH_LONG).show();

            }
        }

        else if(var.compareTo("getFilmsOfAuthor")==0){
            AccueilVideaste a= (AccueilVideaste) v;
            a.UpdateMyContenuFilm((JSONObject) o);
        }  else if(var.compareTo("getSeriesOfAuthor")==0){
            AccueilVideaste a= (AccueilVideaste) v;
            a.UpdateMyContenuSerie((JSONObject) o);
        } else if(var.compareTo("getVisionneur")==0){
            AccueilVisionneur a= (AccueilVisionneur) v;
            a.setVisionneur((JSONObject) o);
        }else if(var.compareTo("deleteFilm")==0 || var.compareTo("deleteSerie")==0){
            AccueilVideaste a= (AccueilVideaste) v;
            a.updateAll();

        }else if(var.compareTo("getAll")==0 ){
            AccueilVisionneur a= (AccueilVisionneur) v;
            a.updateAll((JSONObject) o);

        }else if(var.compareTo("getListeDeVisionnage")==0 ){
            AccueilVisionneur a= (AccueilVisionneur) v;
            a.updateListeVisionnage((JSONObject) o);

        }
    }

    private void AfterMailConfirmation() {
        Inscription context = (Inscription) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);

        EditText nom = context.getView().findViewById(R.id.nom);
        EditText prenomedittext = context.getView().findViewById(R.id.prenom);
        EditText mail = context.getView().findViewById(R.id.mail);
        EditText dtn = context.getView().findViewById(R.id.dtn);
        EditText login = context.getView().findViewById(R.id.login);
        EditText mdp = context.getView().findViewById(R.id.mdp);
        RadioGroup r = context.getView().findViewById(R.id.radiogroup);
        RadioButton type = context.getView().findViewById(r.getCheckedRadioButtonId());


        Routeur lancerRequette = new Routeur(v);


        Dialog dialog = new Dialog(v);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ProgressBar progressBar = context.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        Button continuer1 = new Button(context.getContext());
        final String[] fbOugenre1 = new String[1];
        final String[] instaOugenre2 = new String[1];
        final String[] lkdnOugenre3 = new String[1];
        List<CheckBox> checkBoxList = new ArrayList<>();

        if (type.getText().toString().compareTo("VIDÉASTE") == 0) {
            dialog.setContentView(R.layout.reseaux_videaste);
            continuer1 = dialog.findViewById(R.id.continu);


        } else if (type.getText().toString().compareTo("VISIONNEUR") == 0) {

            dialog.setContentView(R.layout.genres_visionneur);
            continuer1 = dialog.findViewById(R.id.continuV);


            // Ajouter les CheckBox à la liste
            checkBoxList.add(dialog.findViewById(R.id.genre1));
            checkBoxList.add(dialog.findViewById(R.id.genre2));
            checkBoxList.add(dialog.findViewById(R.id.genre3));
            checkBoxList.add(dialog.findViewById(R.id.genre4));
            checkBoxList.add(dialog.findViewById(R.id.genre5));
            checkBoxList.add(dialog.findViewById(R.id.genre6));
            checkBoxList.add(dialog.findViewById(R.id.autre));
            // nombre maximum de cases à cocher sélectionnées
            int MAX_CHECKBOXES_SELECTED = 3;

            // compteur initialisée à 0
            final int[] countSelectedCheckboxes = {0};
            for (CheckBox c : checkBoxList) {
                c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // Vérifier si le nombre maximum de cases à cocher sélectionnées n'a pas été atteint
                            if (countSelectedCheckboxes[0] < MAX_CHECKBOXES_SELECTED) {
                                // Incrémenter la variable de comptage
                                countSelectedCheckboxes[0]++;
                            } else {
                                // Désélectionner la case à cocher si le nombre maximum de cases à cocher sélectionnées est atteint
                                c.setChecked(false);
                            }
                        } else {
                            // Décrémenter la variable de comptage si la case à cocher est désélectionnée
                            countSelectedCheckboxes[0]--;
                        }
                    }
                });

            }

        }


        Dialog conditions = new Dialog(v);
        conditions.setContentView(R.layout.conditions_videaste);
        Button continuer2 = conditions.findViewById(R.id.continu1);


        conditions.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ProgressBar progressBar = context.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        continuer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ProgressBar progressBar = context.getView().findViewById(R.id.progressBar2);
                if (type.getText().toString().compareTo("VIDÉASTE") == 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    EditText editfbOugenre1 = dialog.findViewById(R.id.fb);

                    EditText editinstaOugenre2 = dialog.findViewById(R.id.insta);
                    EditText editlkdnOugenre3 = dialog.findViewById(R.id.lnkdn);
                    fbOugenre1[0] = editfbOugenre1.getText().toString();

                    instaOugenre2[0] = editinstaOugenre2.getText().toString();

                    lkdnOugenre3[0] = editlkdnOugenre3.getText().toString();

                    lancerRequette.execute("createUser", nom.getText().toString(), prenomedittext.getText().toString(), dtn.getText().toString(), mail.getText().toString(), login.getText().toString(), mdp.getText().toString(), type.getText().toString(), fbOugenre1[0], instaOugenre2[0], lkdnOugenre3[0]);
                    conditions.dismiss();
                }
            }
        });


        continuer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.getText().toString().compareTo("VIDÉASTE") == 0) {
                    conditions.show();
                } else {
                    List<CheckBox> checkedCheckBoxes = new ArrayList<>();

                    for (CheckBox checkBox : checkBoxList) {
                        if (checkBox.isChecked()) {
                            checkedCheckBoxes.add(checkBox);
                        }
                    }
                    if (checkedCheckBoxes.size() == 3) {
                        fbOugenre1[0] = checkedCheckBoxes.get(0).getText().toString();
                        instaOugenre2[0] = checkedCheckBoxes.get(1).getText().toString();
                        lkdnOugenre3[0] = checkedCheckBoxes.get(2).getText().toString();

                        lancerRequette.execute("createUser", nom.getText().toString(), prenomedittext.getText().toString(), dtn.getText().toString(), mail.getText().toString(), login.getText().toString(), mdp.getText().toString(), type.getText().toString(), fbOugenre1[0], instaOugenre2[0], lkdnOugenre3[0]);
                        dialog.dismiss();
                    } else {
                        TextView t = dialog.findViewById(R.id.info);
                        t.setTextColor(Color.RED);
                    }
                }


            }
        });


        dialog.show();


    }

}
