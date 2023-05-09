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

import androidx.appcompat.app.AppCompatActivity;

import com.app.visio.Accueils.Accueil;
import com.app.visio.Accueils.AccueilVideaste;
import com.app.visio.Accueils.AccueilVisionneur;
import com.app.visio.Connexion;
import com.app.visio.Inscription;
import com.app.visio.R;
import com.app.visio.model.requetage.AccessUsers;

import java.util.ArrayList;
import java.util.List;


public class Requete extends AsyncTask<String, Void, Object> {
    AppCompatActivity v;
    String var;

    public Requete(AppCompatActivity view) {
        this.v = view;
    }

    @Override
    protected void onPreExecute() {

    }


    @Override
    protected Object doInBackground(String... strings) {
        AccessUsers userAcces = new AccessUsers();
        var = strings[0];
        switch (strings[0]) {
            case "verifyConnexion":
                return userAcces.verifyConnexion(strings[1], strings[2]);

            case "createUser":
                return userAcces.createUser(strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], strings[8], strings[9], strings[10]);

            case "verifyLogin":
                return userAcces.verifyLogin(strings[1]);


        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (var.compareTo("verifyConnexion") == 0) {
            String result = (String) o;
            if (!(result.compareTo("Aucun") == 0)) {
                if (result.compareTo("Visionneur")==0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVisionneur.class);
                    v.startActivity(intent);
                } else if (result.compareTo("Videaste")==0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVideaste.class);
                    v.startActivity(intent);
                }else {
                    Intent intent = new Intent(v.getApplicationContext(), Accueil.class);
                    intent.putExtra("UserType", result);
                    v.startActivity(intent);
                }
            } else {
                Connexion connexion = (Connexion) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);
                ProgressBar progressBar = connexion.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
                TextView error = connexion.getView().findViewById(R.id.ErrorMessage);
                error.setVisibility(View.VISIBLE);

            }
        } else if (var.compareTo("createUser") == 0) {
            String result = (String) o;
            String type = result.split(";")[1];
            result = result.split(";")[0];

            if (result.compareTo("true") == 0) {
                if (type.compareTo("Visionneur")==0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVisionneur.class);
                    v.startActivity(intent);
                } else if (type.compareTo("Videaste")==0) {
                    Intent intent = new Intent(v.getApplicationContext(), AccueilVideaste.class);
                    v.startActivity(intent);
                }else {
                    Intent intent = new Intent(v.getApplicationContext(), Accueil.class);
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
        } else if (var.compareTo("verifyLogin") == 0) {
            String result = (String) o;

            if (result.compareTo("login_duplicate") == 0) {
                Inscription connexion = (Inscription) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);
                ProgressBar progressBar = connexion.getView().findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.INVISIBLE);
                TextView error = connexion.getView().findViewById(R.id.ErrorMessage);
                error.setText("Ce nom d'utilisateur est déja utilisé");
                error.setVisibility(View.VISIBLE);
            } else {


                Inscription context = (Inscription) v.getSupportFragmentManager().findFragmentById(R.id.fragContainer);

                EditText nom = context.getView().findViewById(R.id.nom);
                EditText prenomedittext = context.getView().findViewById(R.id.prenom);
                EditText mail = context.getView().findViewById(R.id.mail);
                EditText dtn = context.getView().findViewById(R.id.dtn);
                EditText login = context.getView().findViewById(R.id.login);
                EditText mdp = context.getView().findViewById(R.id.mdp);
                RadioGroup r = context.getView().findViewById(R.id.radiogroup);
                RadioButton type = context.getView().findViewById(r.getCheckedRadioButtonId());


                Requete lancerRequette = new Requete(v);


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
    }

}
