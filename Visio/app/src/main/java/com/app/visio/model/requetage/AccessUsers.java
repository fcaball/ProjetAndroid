package com.app.visio.model.requetage;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessUsers {

    public String[] verifyConnexion(String login, String mdp) {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/getAUser.php?login="+login+"&mdp="+mdp);
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        donnees=donnees.replaceAll("\"","");
        String[] res=donnees.split(",");
        if(res[0].compareTo("True")==0){
            return new String[]{res[1], res[2]};
        }

        return new String[]{"Aucun"};
    }

    public String[] createUser(String nom,String prenom, String dtn, String email, String login, String mdp,String userType, String genre1OuFb,String genre2OuInsta, String genre3OuLnkdn) {

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
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String[] res=donnees.split(",");

        return new String[]{res[0],res[1], StringUsetype};
    }


    public String verifyLogin(String login) {

        APIrequest d= new APIrequest();

        String params="{\"login\":\""+login+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/verifyLogin.php",params);
        donnees=donnees.replaceAll("\"","");

        return donnees;
    }


    public void sendMail(String mail, String code) {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/SendMail.php?mail="+mail+"&code="+code);
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        donnees=donnees.replaceAll("\"","");
        String[] res=donnees.split(",");

    }


    public JSONObject getVisionneur(String idC) {
        APIrequest d= new APIrequest();

        String params="{\"idC\":\""+idC+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/getVisionneur.php",params);
        JSONObject res = null;
        try {
            res=new JSONObject(donnees);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Object getAll() {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/lireAll.php");
        JSONObject res = null;
        try {
            res=new JSONObject(donnees);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }

    public Object getListeDeVisionnage(String idC) {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/lireAllVisio.php?idC="+idC);
        JSONObject res = null;
        try {
            res=new JSONObject(donnees);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }
}
