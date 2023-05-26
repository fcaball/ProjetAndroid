package com.app.visio.model.gestion;

public class ElmtListeFilm {
    int idELF;
    int idC;
    boolean Like;
    int idF;

    public ElmtListeFilm(int idELF, int idC, boolean like, int idF) {
        this.idELF = idELF;
        this.idC = idC;
        Like = like;
        this.idF = idF;
    }

    public int getIdELF() {
        return idELF;
    }

    public int getIdC() {
        return idC;
    }

    public boolean isLike() {
        return Like;
    }

    public int getIdF() {
        return idF;
    }
}
