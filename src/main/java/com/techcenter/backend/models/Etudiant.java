package com.techcenter.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "étudiants")
public class Etudiant {

    @Id
    private String id;
    private String cin ;
    private String motdepasse ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String date_de_naissance ;
    private String adresse ;
    private String num_tel ;
    private String type;


    public Etudiant() {
        this.type="etudiant";

    }

    public Etudiant(String cin, String mot_de_passe) {
        this.cin = cin;
        this.motdepasse = mot_de_passe;
    }


    public Etudiant(String cin, String mot_de_passe, String nom, String prenom, String email, String date_de_naissance, String adresse, String num_tel) {
        this.cin = cin;
        this.motdepasse = mot_de_passe;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.num_tel = num_tel;
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
        return motdepasse;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.motdepasse = mot_de_passe;
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

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


