package com.app.visio.model.gestion;

public class ElmtListeSerie {
    int idELS;
    int idC;
    boolean like;
    int saisonActuelle;
    int epActuel;
    int idS;

    public ElmtListeSerie(int idELS, int idC, boolean like, int saisonActuelle, int epActuel, int idS) {
        this.idELS = idELS;
        this.idC = idC;
        this.like = like;
        this.saisonActuelle = saisonActuelle;
        this.epActuel = epActuel;
        this.idS = idS;
    }

    public int getIdELS() {
        return idELS;
    }

    public int getIdC() {
        return idC;
    }

    public boolean isLike() {
        return like;
    }

    public int getSaisonActuelle() {
        return saisonActuelle;
    }

    public int getEpActuel() {
        return epActuel;
    }

    public int getIdS() {
        return idS;
    }
}
