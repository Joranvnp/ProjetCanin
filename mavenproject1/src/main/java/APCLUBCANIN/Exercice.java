/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APCLUBCANIN;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author kmudler
 */
public class Exercice {
    //private int idexercice;
    private String libelle;
    private double coef;
    private int idClasse;

    private ArrayList<Evaluer> lesEvaluations;
    private Classe uneCLasse;

    public Exercice(String lelibelle, double lecoef,int lidClasse){
        //this.idexercice = lidexercice;
        this.libelle = lelibelle;
        this.coef = lecoef;
        this.idClasse = lidClasse;
        this.setLesEvaluations(new ArrayList<Evaluer>());
    }
    
    /*public int getidexercice(){
         return idexercice;
    }*/
    public String getlibelle(){
         return libelle;
    }
    public double getcoef(){
         return coef;
    }
    
    public void setlibelle( String lelibelle){
        libelle = lelibelle;
    }
    public void setcoef(double lecoef){
        
        coef = lecoef;
    }

    public void ajouterEvaluation(Evaluer eval){
        this.getLesEvaluations().add(eval);
    }

    public ArrayList<Evaluer> getLesEvaluations() {
        return lesEvaluations;
    }

    public void setLesEvaluations(ArrayList<Evaluer> lesEvaluations) {
        this.lesEvaluations = lesEvaluations;
    }

    public Classe getClasse()
    {
        return this.uneCLasse;
    }

    public void AjoutExercice() throws SQLException {
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

        ResultSet res = stmt.executeQuery("SELECT count(libelle) as libelle FROM exercice WHERE libelle = '"+libelle+"';");

        res.next();

        if(res.getInt("libelle") == 0){

            stmt.execute("INSERT INTO exercice (libelle, coef , id_classe) VALUES ('"+libelle+"', '"+coef+"','"+idClasse+"')");
        }


    }
}
