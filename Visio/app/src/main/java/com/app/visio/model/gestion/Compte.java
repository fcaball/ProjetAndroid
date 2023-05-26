package com.app.visio.model.gestion;

public class Compte {
    int idC;
    String nom;
    String prenom;
    String mail;
    String datedenaissance;
    String login;
    String mdp;

    public Compte(int idC) {
        this.idC = idC;
    }

    public String getNom() {
        return nom;
    }

    public int getIdC() {
        return idC;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(String datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
