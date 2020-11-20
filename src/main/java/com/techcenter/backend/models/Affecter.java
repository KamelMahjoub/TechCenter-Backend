package com.techcenter.backend.models;

public class Affecter {

    private String titreFormation;
    private String nomFormateur;
    private int sessionFormation;

    public String getTitreFormation() {
        return titreFormation;
    }
    public void setTitreFormation(String titreFormation) {
        this.titreFormation = titreFormation;
    }
    public String getNomFormateur() {
        return nomFormateur;
    }
    public void setNomFormateur(String nomFormateur) {
        this.nomFormateur = nomFormateur;
    }
    public int getSessionFormation() {
        return sessionFormation;
    }
    public void setSessionFormation(int saisonFormation) {
        this.sessionFormation = saisonFormation;
    }



}