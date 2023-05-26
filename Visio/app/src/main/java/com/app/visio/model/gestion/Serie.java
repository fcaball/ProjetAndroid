package com.app.visio.model.gestion;

public class Serie {
    int idS;
    String titre;
    String genre1;
    String genre2;
    String genre3;
    boolean anime;
    String lien;
    String synopsis;
    int auteur;

    public Serie(int idS, String titre, String genre1, String genre2, String genre3, boolean anime, String synopsis, int auteur) {
        this.idS = idS;
        this.titre = titre;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
        this.anime = anime;
        this.synopsis = synopsis;
        this.auteur = auteur;
    }

    public int getIdS() {
        return idS;
    }

    public String getTitre() {
        return titre;
    }

    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getGenre3() {
        return genre3;
    }

    public boolean isAnime() {
        return anime;
    }

    public String getLien() {
        return lien;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getAuteur() {
        return auteur;
    }
}
