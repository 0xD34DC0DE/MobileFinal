package com.tpfinal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"error"})
public class MinisterData {

    private String errorMessage;

    private String numeroAssuranceSocial;

    private String nom;

    private String prenom;

    private String sexe;

    private Integer age;

    private PermisType typePermis;

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getNumeroAssuranceSocial() {
        return numeroAssuranceSocial;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public Integer getAge() {
        return age;
    }

    public PermisType getTypePermis() {
        return typePermis;
    }
}
