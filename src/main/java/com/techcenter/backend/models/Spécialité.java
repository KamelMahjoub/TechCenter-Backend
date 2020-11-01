package com.techcenter.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "spécialités")
public class Spécialité {

    @Id
    private String id;
    private String nom_du_spécialité;

    public Spécialité(String nom_du_spécialité) {
        this.nom_du_spécialité = nom_du_spécialité;
    }

    public Spécialité() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_du_spécialité() {
        return nom_du_spécialité;
    }

    public void setNom_du_spécialité(String nom_du_spécialité) {
        this.nom_du_spécialité = nom_du_spécialité;
    }
}


