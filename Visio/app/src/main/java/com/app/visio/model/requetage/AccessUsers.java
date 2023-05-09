package com.app.visio.model.requetage;

import java.io.IOException;
import java.util.ArrayList;

public class AccessUsers {

    public String verifyConnexion(String login, String mdp) {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/getAUser.php?login="+login+"&mdp="+mdp);
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        donnees=donnees.replaceAll("\"","");
        String[] res=donnees.split(",");
        if(res[0].compareTo("True")==0){
            return res[1];
        }

        return "Aucun";
    }

    public String createUser(String nom,String prenom, String dtn, String email, String login, String mdp,String userType, String genre1OuFb,String genre2OuInsta, String genre3OuLnkdn) {

        APIrequest d= new APIrequest();
        String StringUsetype="";
        if(userType.compareTo("VIDÉASTE")==0){
            StringUsetype="Videaste";
        }else{
            StringUsetype="Visionneur";
        }

        String params="{\"nom\":\""+nom+"\",\"prenom\":\""+prenom+"\",\"dtn\":\""+dtn+"\",\"email\":\""+email+"\",\"login\":\""+login+"\",\"mdp\":\""+mdp+"\",\"type\":\""+StringUsetype+"\",\"genre1\":\""+genre1OuFb+"\",\"genre2\":\""+genre2OuInsta+"\",\"genre3\":\""+genre3OuLnkdn+"\",\"fb\":\""+genre1OuFb+"\",\"insta\":\""+genre2OuInsta+"\",\"lnkdn\":\""+genre3OuLnkdn+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/createUser.php",params);
        donnees=donnees.replaceAll("\"","");

        return donnees+";"+StringUsetype;
    }


    public String verifyLogin(String login) {

        APIrequest d= new APIrequest();

        String params="{\"login\":\""+login+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/verifyLogin.php",params);
        donnees=donnees.replaceAll("\"","");

        return donnees;
    }


}
