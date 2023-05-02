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
public class Chien {
    private String fapac;
    private String nomchien;
    private String race;
    private String datenaissance;
    private String sexe;
    private String ct;

    private int idClub;

    //private Club unClub;
    
    public Chien(String lefapac,String lenomchien, String larace,String lesexe, String ladatenaissance, String lect, int lidClub){
        this.fapac = lefapac;
        this.nomchien = lenomchien;
        this.race = larace;
        this.datenaissance = ladatenaissance;
        this.sexe = lesexe;
        this.ct = lect;
        this.idClub = lidClub;
    }
    
    public String getfapac(){
         return fapac;
    }
    public String getnomchien(){
         return nomchien;
    }
    public String getrace(){
         return race;
    }
    public String getdatenaissance(){
         return datenaissance;
    }
    public String getsexe(){
         return sexe;
    }
    public String getct(){
         return ct;
    }
    public void setfapac(String lefapac){
        fapac = lefapac;
    }
    public void setnomchien( String lenomchien){
        nomchien = lenomchien;
    }
    public void setrace(String larace){
        
        race = larace;
    }
    public void setdatenaissance(String ladatenaissance){
        datenaissance = ladatenaissance;
    }
    public void setsexe(String lesexe){
        sexe = lesexe;
    }
    public void setct(String lect){
        ct = lect;
    }

   /* public Club getClub()
    {
        return this.unClub;
    }*/

    public void lajout() throws SQLException {
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

        String requete = "SELECT COUNT(FAPAC) as lefapac FROM chien WHERE FAPAC = '"+fapac+"'";

        ResultSet res = stmt.executeQuery(requete);

        res.next();

        if(res.getInt("lefapac") == 0)
        {


            stmt.execute("INSERT INTO chien(FAPAC,prenom,race,sexe,dateNaissance,CT,id) values ('"+fapac.split("\\.")[0]+"','"+nomchien+"','"+race+"','"+sexe+"','"+datenaissance+"','"+ct.split("\\.")[0]+"','"+idClub+"')");
        }



    }


}

