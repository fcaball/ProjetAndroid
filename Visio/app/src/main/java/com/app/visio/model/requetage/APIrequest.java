package com.app.visio.model.requetage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIrequest {

    public String deserializeGet(String path) {

        URL url;
        HttpURLConnection connection=null;
        String donnees="";
        //Remplacement des espaces par %20 pour éviter erreur de requêtes
        path=path.replace(" ", "%20");

        try {
            //création de l'url avec notre path
            url = new URL(path);
            //ouverture de la connexion
            connection = (HttpURLConnection) url.openConnection();

            //paramétrage de la requête
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json ; charset=UTF-8");

            //envoi de la requete et définition d'un lecteur sur l'entrée d'écoute de notre connection
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //La ligne courante
            String inputLine;

            //Le contenu de la reponse GET (Java)
            StringBuffer content = new StringBuffer();

            /* Pour chaque ligne dans la reponse GET */
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            donnees=content.toString();
            //Ferme BufferedReader
            in.close();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        donnees=changementAccents(donnees);
        connection.disconnect();

        return donnees;

    }

    public String deserializePut(String path,String params) {
        URL url;
        HttpURLConnection connection;
        String donnees="";
        try {
            //création de l'url avec notre path
            url = new URL(path);
            //ouverture de la connexion
            connection = (HttpURLConnection) url.openConnection();

            //paramétrage de la requête
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type","application/json ; utf-8");
            //définition du body
            //dire que l'on définit le flux de sorie de la connexion
            connection.setDoOutput(true);
            //création d'un objet flux de sortie qui correspond à celui de la connection
            OutputStream os = connection.getOutputStream();
            //changement des paramètres en tableau de bytes
            byte[] input = params.getBytes("utf-8");
            //on écrit les paramètres dans le flux de sortie pour qu'il les prennent en compte
            os.write(input, 0, input.length);

            //envoi de la requete et définition d'un lecteur sur l'entrée d'écoute de notre connection
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //La ligne courante
            String inputLine;

            //Le contenu de la reponse PUT (Java)
            StringBuffer content = new StringBuffer();

            /* Pour chaque ligne dans la reponse GET */
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            donnees=content.toString();
            //Ferme BufferedReader
            in.close();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return changementAccents(donnees);

    }

    public String deserializePost(String path,String params) {
        URL url;
        HttpURLConnection connection;
        String donnees="";
        try {
            //création de l'url avec notre path
            url = new URL(path);
            //ouverture de la connexion
            connection = (HttpURLConnection) url.openConnection();

            //paramétrage de la requête
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json ; utf-8");
            //définition du body
            //dire que l'on définit le flux de sorie de la connexion
            connection.setDoOutput(true);
            //création d'un objet flux de sortie qui correspond à celui de la connection
            OutputStream os = connection.getOutputStream();
            //changement des paramètres en tableau de bytes
            byte[] input = params.getBytes("utf-8");
            //on écrit les paramètres dans le flux de sortie pour qu'il les prennent en compte
            os.write(input, 0, input.length);

            //envoi de la requete et définition d'un lecteur sur l'entrée d'écoute de notre connection
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //La ligne courante
            String inputLine;

            //Le contenu de la reponse POST (Java)
            StringBuffer content = new StringBuffer();

            /* Pour chaque ligne dans la reponse GET */
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            donnees=content.toString();
            //Ferme BufferedReader
            in.close();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return this.changementAccents(donnees);

    }
    private String changementAccents(String s) {
        s=s.replace("\\u00e9", "é");
        s=s.replace("\\u00c9", "É");
        s=s.replace("\\u00e8", "è");
        s=s.replace("\\u00c8", "È");
        s=s.replace("\\u00ea", "ê");
        s=s.replace("\\u00ca", "Ê");
        s=s.replace("\\u00e0", "à");
        s=s.replace("\\u00c0", "À");
        s=s.replace("\\u00e7", "ç");
        s=s.replace("\\u00c7", "Ç");
        s=s.replace("\\u00c2", "Â");


        return s;
    }

    public String deserializeDelete(String path, String params) {
        URL url;
        HttpURLConnection connection;
        String donnees="";
        try {
            //création de l'url avec notre path
            url = new URL(path);
            //ouverture de la connexion
            connection = (HttpURLConnection) url.openConnection();

            //paramétrage de la requête
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type","application/json ; utf-8");
            //définition du body
            //dire que l'on définit le flux de sorie de la connexion
            connection.setDoOutput(true);
            //création d'un objet flux de sortie qui correspond à celui de la connection
            OutputStream os = connection.getOutputStream();
            //changement des paramètres en tableau de bytes
            byte[] input = params.getBytes("utf-8");
            //on écrit les paramètres dans le flux de sortie pour qu'il les prennent en compte
            os.write(input, 0, input.length);

            //envoi de la requete et définition d'un lecteur sur l'entrée d'écoute de notre connection
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //La ligne courante
            String inputLine;

            //Le contenu de la reponse POST (Java)
            StringBuffer content = new StringBuffer();

            /* Pour chaque ligne dans la reponse GET */
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            donnees=content.toString();
            //Ferme BufferedReader
            in.close();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return donnees;

    }

}
