package com.app.visio.model.requetage;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessFilm {

    public boolean createFilm(String titre, String genre1, String genre2, String genre3, String lien,boolean courtmetrage,int idAuteur) {
        APIrequest d= new APIrequest();
        String StringCourtmetrage="";
        if(courtmetrage){
            StringCourtmetrage="true";
        }else{
            StringCourtmetrage="false";
        }

        String params="{\"titre\":\""+titre+"\",\"genre1\":\""+genre1+"\",\"genre2\":\""+genre2+"\",\"genre3\":\""+genre3+"\",\"lien\":\""+lien+"\",\"courtmetrage\":\""+StringCourtmetrage+"\",\"Auteur\":\""+idAuteur+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/films/creerFilm.php",params);
        donnees=donnees.replaceAll("\"","");
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String res=donnees.split(",")[0];
        return res.compareTo("true")==0;

    }

    public boolean updateFilm(String titre, String genre1, String genre2, String genre3, String lien,boolean courtmetrage,int idF) {
        APIrequest d= new APIrequest();
        String StringCourtmetrage="";
        if(courtmetrage){
            StringCourtmetrage="true";
        }else{
            StringCourtmetrage="false";
        }

        String params="{\"titre\":\""+titre+"\",\"genre1\":\""+genre1+"\",\"genre2\":\""+genre2+"\",\"genre3\":\""+genre3+"\",\"lien\":\""+lien+"\",\"courtmetrage\":\""+StringCourtmetrage+"\",\"idF\":\""+idF+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/films/updateFilm.php",params);
        donnees=donnees.replaceAll("\"","");
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String res=donnees.split(",")[0];
        return res.compareTo("true")==0;

    }


    public boolean deleteFilm(int idF) {
        APIrequest d= new APIrequest();

        String params="{\"idF\":\""+idF+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/films/deleteFilm.php",params);
        donnees=donnees.replaceAll("\"","");
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String res=donnees.split(",")[0];
        return res.compareTo("true")==0;

    }
    public JSONObject getFilmsOfAuthor(String Auteur) {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/films/lireFilmOfAuthor.php?Auteur="+Auteur);
        JSONObject res = null;
        try {
             res=new JSONObject(donnees);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }
}

