package com.app.visio;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.visio.model.gestion.Requete;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inscription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inscription extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Inscription() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inscription.
     */
    // TODO: Rename and change types and number of parameters
    public static Inscription newInstance(String param1, String param2) {
        Inscription fragment = new Inscription();
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
        return inflater.inflate(R.layout.fragment_inscription, container, false);
    }

    public void onClickContinuer(View v,ConnexionInscription activity) {
        boolean canContinue=true;
        EditText nom = this.getView().findViewById(R.id.nom);
        EditText prenomedittext = this.getView().findViewById(R.id.prenom);
        EditText mail = this.getView().findViewById(R.id.mail);
        EditText dtn = this.getView().findViewById(R.id.dtn);
        EditText login = this.getView().findViewById(R.id.login);
        EditText mdp = this.getView().findViewById(R.id.mdp);
        RadioGroup r = this.getView().findViewById(R.id.radiogroup);

        nom.setHintTextColor(Color.parseColor("#989595"));

        prenomedittext.setHintTextColor(Color.parseColor("#989595"));

        mail.setHintTextColor(Color.parseColor("#989595"));

        login.setHintTextColor(Color.parseColor("#989595"));

        mdp.setHintTextColor(Color.parseColor("#989595"));

        TextView t = this.getView().findViewById(R.id.errormessageforcheckboxes);
        t.setVisibility(View.INVISIBLE);


        if (nom.getText().toString().compareTo("") == 0) {
            nom.setHintTextColor(Color.RED);
            canContinue=false;
        } else if (prenomedittext.getText().toString().compareTo("") == 0) {
            prenomedittext.setHintTextColor(Color.RED);
            canContinue=false;
        } else if (mail.getText().toString().compareTo("") == 0 || !(mail.getText().toString().contains("@")) || !(mail.getText().toString().contains("."))) {
            mail.setHintTextColor(Color.RED);
            mail.setTextColor(Color.RED);
            canContinue=false;
        } else if (r.getCheckedRadioButtonId() == -1) {
            t.setVisibility(View.VISIBLE);
            canContinue=false;
        } else if (login.getText().toString().compareTo("") == 0) {
            login.setHintTextColor(Color.RED);
            canContinue=false;
        } else if (mdp.getText().toString().compareTo("") == 0) {
            mdp.setHintTextColor(Color.RED);
            canContinue=false;
        }
        if(canContinue){
            ProgressBar progressBar = this.getView().findViewById(R.id.progressBar2);
            progressBar.setVisibility(View.VISIBLE);
            Requete lancerRequette= new Requete(activity);

            lancerRequette.execute("verifyLogin",login.getText().toString());

/*
            EnvoiMail Email= new EnvoiMail(activity.getApplicationContext(),"curtix004@gmail.com","alexandre.fleury13@gmail.com","8/Cc2iG4","yo","hey");
            Email.execute();*/

          /*  String host = "mail.fabiencaballero.fr";
            String port = "465";
            String username = "contact@fabiencaballero.fr";
            String password = "c2$&YY9tY3_YY9G2wY";

            // Créer une propriété pour la session
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");

            // Créer une session avec authentification
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Créer un nouveau message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alexandre.fleury13@gmail.com"));
                message.setSubject("RÉAGIS PAS");
                message.setText("Fait le cri le plus bizarre possible !");

                // Envoyer le message
                Transport.send(message);

                System.out.println("yoooo Le message a été envoyé avec succès.");
            } catch (Exception ex) {
                System.out.println("yoooo Une erreur s'est produite : " + ex.getMessage());
                ex.printStackTrace();
            }
*/



           /* Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_username@example.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
            message.setSubject("Subject of the message");
            message.setText("Content of the message");*/





        /*    Random random = new Random();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"curtix004.email@destinataire.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation d'adresse");
            intent.putExtra(Intent.EXTRA_TEXT, "Bienvenue chez Visio,\n vous avez plus qu'a confirmer votre adresse mail en tapant sur notre application le code suivant: \n"+random.nextInt(10)+" "+random.nextInt(10)+" "+random.nextInt(10)+" "+random.nextInt(10)+"\n\nCe message est envoyé automatiquement, merci de ne pas y répondre.");
            startActivity(Intent.createChooser(intent, "Envoyer l'e-mail"));*/

          /*  Dialog dialog = new Dialog(activity.getApplicationContext());
            dialog.setContentView(R.layout.confirmation_mail);

            Button positiveButton=(Button) dialog.findViewById(R.id.valider);
            Button renvoyer=(Button) dialog.findViewById(R.id.renvoyer);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            renvoyer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            dialog.show();*/


        }

    }
}