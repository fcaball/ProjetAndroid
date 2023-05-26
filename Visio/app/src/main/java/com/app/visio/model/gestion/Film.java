package com.app.visio.model.gestion;

public class Film {
    int idF;
    String titre;
    String genre1;
    String genre2;
    String genre3;
    boolean courtmetrage;
    String lien;
    int auteur;

    public int getIdF() {
        return idF;
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

    public boolean isCourtmetrage() {
        return courtmetrage;
    }

    public String getLien() {
        return lien;
    }

    public int getAuteur() {
        return auteur;
    }

    public Film(int idF, String titre, String genre1, String genre2, String genre3, boolean courtmetrage, String lien, int auteur) {
        this.idF = idF;
        this.titre = titre;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
        this.courtmetrage = courtmetrage;
        this.lien = lien;
        this.auteur = auteur;
    }

}
