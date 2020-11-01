package com.techcenter.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "formateurs")
public class Formateur {

    @Id
    private String id;
    private String cin ;
    private String mot_de_passe ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String date_de_naissance ;
    private String adresse ;
    private List<String> num_tel ;
    private List<Spécialité> liste_des_spécialités;

    public Formateur() {
    }

    public Formateur(String cin, String mot_de_passe) {
        this.cin = cin;
        this.mot_de_passe = mot_de_passe;
    }

    public Formateur(String cin, String mot_de_passe, String nom, String prenom, String email, String date_de_naissance, String adresse, List<String> num_tel, List<Spécialité> liste_des_spécialités) {
        this.cin = cin;
        this.mot_de_passe = mot_de_passe;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.liste_des_spécialités = liste_des_spécialités;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNom() {
        return nom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<String> getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(List<String> num_tel) {
        this.num_tel = num_tel;
    }

    public List<Spécialité> getListe_des_spécialités() {
        return liste_des_spécialités;
    }

    public void setListe_des_spécialités(List<Spécialité> liste_des_spécialités) {
        this.liste_des_spécialités = liste_des_spécialités;
    }
}