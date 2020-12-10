package com.techcenter.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "formations")
public class Formation {

    @Id
    private String id;
    private String titre ;
    private String description;
    private String niveau;
    private String image;
    private List<String> matiéres;
    private List<Session> listeDesSessions;



    public Formation() {
    }

    public Formation(String titre, String description, String niveau, List<String> matiéres) {
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
        this.matiéres = matiéres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<String> getMatiéres() {
        return matiéres;
    }

    public void setMatiéres(List<String> matiéres) {
        this.matiéres = matiéres;
    }
    public List<Session> getListeDesSession() {
        return listeDesSessions;
    }

    public void setListeDesSession(List<Session> listeDesSession) {
        this.listeDesSessions = listeDesSession;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
