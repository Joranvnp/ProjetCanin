/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APCLUBCANIN;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jvanpeene
 */
public class Inscription {
    private int idInscription;
    private ArrayList<Evaluer> lesEvaluations;

    private int idconc;
    private int idcond;
    private int idcla;
    private int idchien;

    private Chien unChien;
    private Concours unConcour;
    private Conducteur unConducteur;
    private Classe uneClasse;

    public Inscription(int lidconc, int lidcond,int lidcla, int lidchien){
        this.idconc = lidconc;
        this.idcond=lidcond;
        this.idcla = lidcla;
        this.idchien = lidchien;

    }
    public Inscription(int unId)
    {
        this.idInscription = unId;
        this.setLesEvaluations(new ArrayList<Evaluer>());
    }

    public void ajouterEvaluation(Evaluer eval){
        this.getLesEvaluations().add(eval);
    }

    public int getId()
    {
        return idInscription;
    }

    public ArrayList<Evaluer> getLesEvaluations() {
        return lesEvaluations;
    }

    public void setLesEvaluations(ArrayList<Evaluer> lesEvaluations) {
        this.lesEvaluations = lesEvaluations;
    }

    public Chien getChien()
    {
        return this.unChien;
    }

    public Concours getConcour()
    {
        return this.unConcour;
    }

    public Conducteur getConducteur()
    {
        return this.unConducteur;
    }

    public Classe getClasse()
    {
        return this.uneClasse;
    }
    public void linscription() throws SQLException {
        String URL = "jdbc:mysql://172.19.0.3:3306/AppClub";
        //String URL = "jdbc:mysql://localhost:3306/AppClub";
        // login et mdp de la base
        //String LOGIN = "root";
        String LOGIN = "phpmyadmin";
        String PASSWORD = "0550002D";
        //String PASSWORD = "";

        Connection connexion = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        // Objet statement : représente une instruction SQL
        Statement stmt = connexion.createStatement();

        stmt.execute("INSERT INTO inscription(id_concours,id_conducteur,id_classe,id_chien) values ('"+idconc+"','"+idcond+"','"+idcla+"','"+idchien+"')");




    }
}
