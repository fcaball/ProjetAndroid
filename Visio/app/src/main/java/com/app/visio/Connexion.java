package com.app.visio;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.app.visio.model.gestion.Routeur;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Connexion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Connexion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Connexion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Connexion.
     */
    // TODO: Rename and change types and number of parameters
    public static Connexion newInstance(String param1, String param2) {
        Connexion fragment = new Connexion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connexion, container, false);
    }

    public void onClickContinuer(View v, ConnexionInscription activity) {
        boolean canContinue=true;
        EditText login = this.getView().findViewById(R.id.login);
        EditText mdp = this.getView().findViewById(R.id.mdp);

        login.setHintTextColor(Color.parseColor("#989595"));
        mdp.setHintTextColor(Color.parseColor("#989595"));

         if (login.getText().toString().compareTo("") == 0) {
            login.setHintTextColor(Color.RED);
            canContinue=false;
        } else if (mdp.getText().toString().compareTo("") == 0) {
            mdp.setHintTextColor(Color.RED);
            canContinue=false;
        }

         if(canContinue){
             ProgressBar progressBar = this.getView().findViewById(R.id.progressBar2);
             progressBar.setVisibility(View.VISIBLE);
             Routeur lancerRequette= new Routeur(activity);
             lancerRequette.execute("verifyConnexion",login.getText().toString(),mdp.getText().toString());
         }


    }
}