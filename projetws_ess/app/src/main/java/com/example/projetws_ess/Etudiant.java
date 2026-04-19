package com.example.projetws_ess;

public class Etudiant {

    private int id_ess;
    private String nom_ess;
    private String prenom_ess;
    private String ville_ess;
    private String sexe_ess;

    // Constructeur vide (souvent nécessaire pour que Gson puisse créer l'objet depuis le JSON)
    public Etudiant() {
    }

    // Constructeur avec paramètres
    public Etudiant(int id_ess, String nom_ess, String prenom_ess, String ville_ess, String sexe_ess) {
        this.id_ess = id_ess;
        this.nom_ess = nom_ess;
        this.prenom_ess = prenom_ess;
        this.ville_ess = ville_ess;
        this.sexe_ess = sexe_ess;
    }

    // --- GETTERS ---

    public int getId_ess() {
        return id_ess;
    }

    public String getNom_ess() {
        return nom_ess;
    }

    public String getPrenom_ess() {
        return prenom_ess;
    }

    public String getVille_ess() {
        return ville_ess;
    }

    public String getSexe_ess() {
        return sexe_ess;
    }

    // --- SETTERS ---

    public void setId_ess(int id_ess) {
        this.id_ess = id_ess;
    }

    public void setNom_ess(String nom_ess) {
        this.nom_ess = nom_ess;
    }

    public void setPrenom_ess(String prenom_ess) {
        this.prenom_ess = prenom_ess;
    }

    public void setVille_ess(String ville_ess) {
        this.ville_ess = ville_ess;
    }

    public void setSexe_ess(String sexe_ess) {
        this.sexe_ess = sexe_ess;
    }
}