package com.app.visio.model.gestion;

public class Visionneur {
    String Genre1;
    String Genre2;
    String Genre3;
    boolean blocked;

    public Visionneur(String genre1, String genre2, String genre3, boolean blocked) {
        Genre1 = genre1;
        Genre2 = genre2;
        Genre3 = genre3;
        this.blocked = blocked;
    }

    public String getGenre1() {
        return Genre1;
    }

    public String getGenre2() {
        return Genre2;
    }

    public String getGenre3() {
        return Genre3;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
