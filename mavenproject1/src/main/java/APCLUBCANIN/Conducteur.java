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
public class Conducteur {

    private int idConducteur;
    private String nomconducteur;
    private String prenomconducteur;
    
    
    public Conducteur(int lidConducteur,String lenomconducteur, String leprenomconducteur){

        this.idConducteur = lidConducteur;
        this.nomconducteur = lenomconducteur;
        this.prenomconducteur = leprenomconducteur;
        
    }


    public int getidconducteur(){
        return idConducteur;
    }
    public String getnomconducteur(){
         return nomconducteur;
    }
    public String getprenomconducteur(){
         return prenomconducteur;
    }

    public void setidconducteur( int lidconducteur){
        idConducteur = lidconducteur;
    }
    public void setnomconducteur( String lenomconducteur){
        nomconducteur = lenomconducteur;
    }
    public void setprenomconducteur(String leprenomconducteur){
        
        prenomconducteur = leprenomconducteur;
    }
    public void AjoutConducteur() throws SQLException {
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

        ResultSet res3 = stmt.executeQuery("SELECT id,count(nom) as lnom,count(prenom) as lprenom FROM conducteur WHERE nom = '"+ nomconducteur +"' AND prenom ='"+prenomconducteur+"'");

        res3.next();


        if(res3.getInt("lnom") == 1 && res3.getInt("lprenom") == 1 ){

            int lid = res3.getInt("id");
            this.idConducteur  = lid;
            stmt.close();
        }

        else{
            stmt.close();
            String sql = "INSERT INTO conducteur(nom,prenom) values ('"+nomconducteur+"','"+prenomconducteur+"')";
            PreparedStatement ps = connexion.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                this.idConducteur = generatedKey;
            }
        }

    }
}
