/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APCLUBCANIN;

import java.sql.*;

/**
 *
 * @author kmudler
 */
public class Evaluer {

    private int id;
    private int idcomm;
     private double note;
    private String commentaire;
    private Inscription uneInscription;
    private Exercice unExercice;

    public Evaluer(int lid,int lidcomm,double lanote,String lecommentaire){
        this.id = lid;
        this.idcomm = lidcomm;
        this.note = lanote;
        this.commentaire = lecommentaire;        
    }
    
    public double getnote(){
         return note;
    }
    public String getcommentaire(){
         return commentaire;
    }
    public void setnote(double lanote){
        note = lanote;
    }
    public void setcommentaire( String lecommentaire){
        commentaire = lecommentaire;
    }

    public void Eval() throws SQLException {
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


        stmt.execute("INSERT INTO evaluer(id,id_inscription,note,commentaire) values ('"+id+"','"+idcomm+"','"+note+"','"+commentaire.replace("'", "\\'")+"')");




    }
    
}
