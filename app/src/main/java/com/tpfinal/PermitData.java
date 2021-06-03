package com.tpfinal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"error"})
public class PermitData {

    private String errorMessage;

    private CategoryAge categorieAge;

    private PermisType typePermis;

    private String nom;

    private String prenom;

    private Date dateExpiration;

    private String codeQRBase64;

    public String getErrorMessage() {
        return errorMessage;
    }

    public CategoryAge getCategorieAge() {
        return categorieAge;
    }

    public PermisType getTypePermis() {
        return typePermis;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public String getCodeQRBase64() {
        return codeQRBase64;
    }
}
