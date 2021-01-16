package com.techcenter.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "sessions")
public class Session {

    @Id
    private String id;
    private String nom_du_session ;
    private String date_de_début ;
    private String date_de_fin ;
    private String idFormation;
    private String niveau ;
    private float prix ;
    private int nb_places ;
    private int nb_inscrits;
    private List<String> liste_de_formateurs;
    private List<String> liste_des_etudiants;

    public Session(String nom_du_session, String date_de_début, String date_de_fin, int nb_places) {
        this.nom_du_session = nom_du_session;
        this.date_de_début = date_de_début;
        this.date_de_fin = date_de_fin;
        this.nb_places = nb_places;
    }

    public Session() {
        liste_des_etudiants = new ArrayList<String>();
        liste_de_formateurs = new ArrayList<String>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_du_session() {
        return nom_du_session;
    }

    public void setNom_du_session(String nom_du_session) {
        this.nom_du_session = nom_du_session;
    }

    public String getDate_de_début() {
        return date_de_début;
    }

    public void setDate_de_début(String date_de_début) {
        this.date_de_début = date_de_début;
    }

    public String getDate_de_fin() {
        return date_de_fin;
    }

    public void setDate_de_fin(String date_de_fin) {
        this.date_de_fin = date_de_fin;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public int getNb_inscrits() {
        return nb_inscrits;
    }

    public void setNb_inscrits(int nb_inscrits) {
        this.nb_inscrits = nb_inscrits;
    }

    public List<String> getListe_de_formateurs() {
        return liste_de_formateurs;
    }

    public void setListe_de_formateurs(List<String> liste_de_formateurs) {
        this.liste_de_formateurs = liste_de_formateurs;
    }

    public List<String> getListe_des_etudiants() {
        return liste_des_etudiants;
    }

    public void setListe_des_etudiants(List<String> liste_des_etudiants) {
        this.liste_des_etudiants = liste_des_etudiants;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }
}
