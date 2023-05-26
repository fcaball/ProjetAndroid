package com.app.visio.model.requetage;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessSerie {

    public boolean createSerie(String titre, String genre1, String genre2, String genre3, String synopsis,boolean anime,int idAuteur) {
        APIrequest d= new APIrequest();
        String StringAnime="";
        if(anime){
            StringAnime="true";
        }else{
            StringAnime="false";
        }
        String params="{\"titre\":\""+titre+"\",\"genre1\":\""+genre1+"\",\"genre2\":\""+genre2+"\",\"genre3\":\""+genre3+"\",\"synopsis\":\""+synopsis+"\",\"anime\":\""+StringAnime+"\",\"auteur\":\""+idAuteur+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/series/creerSerie.php",params);
        donnees=donnees.replaceAll("\"","");
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String res=donnees.split(",")[0];
        return res.compareTo("true")==0;

    }

    public boolean updateSerie(String titre, String genre1, String genre2, String genre3, String synopsis,boolean anime,int idS) {
        APIrequest d= new APIrequest();
        String StringAnime="";
        if(anime){
            StringAnime="true";
        }else{
            StringAnime="false";
        }

        String params="{\"titre\":\""+titre+"\",\"genre1\":\""+genre1+"\",\"genre2\":\""+genre2+"\",\"genre3\":\""+genre3+"\",\"synopsis\":\""+synopsis+"\",\"anime\":\""+StringAnime+"\",\"idS\":\""+idS+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/series/updateSerie.php",params);
        donnees=donnees.replaceAll("\"","");
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String res=donnees.split(",")[0];
        return res.compareTo("true")==0;

    }


    public boolean deleteSerie(int idS) {
        APIrequest d= new APIrequest();

        String params="{\"idS\":\""+idS+"\"}";
        String donnees=d.deserializePost("https://fabiencaballero.fr/apiREST/series/deleteSerie.php",params);
        donnees=donnees.replaceAll("\"","");
        donnees=donnees.replaceAll("\\[","");
        donnees=donnees.replaceAll("\\]","");
        String res=donnees.split(",")[0];
        return res.compareTo("true")==0;

    }
    public JSONObject getSeriesOfAuthor(String Auteur) {
        APIrequest d= new APIrequest();

        String donnees=d.deserializeGet("https://fabiencaballero.fr/apiREST/series/lireSeriesOfAuthor.php?Auteur="+Auteur);

        JSONObject res = null;
        try {
            res=new JSONObject(donnees);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }
}

