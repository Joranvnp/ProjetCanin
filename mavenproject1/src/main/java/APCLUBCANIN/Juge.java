/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APCLUBCANIN;

import java.sql.*;

/**
 *
 * @author jvanpeene
 */
public class Juge {
    private int idJuge;
    private String nomJuge;
    private String prenomJuge;

    public Juge(int unId, String unNom, String unPrenom)
    {
        this.idJuge = unId;
        this.nomJuge = unNom;
        this.prenomJuge = unPrenom;
    }
    
    public int getId()
    {
        return idJuge;
    }
    
    public String getNom()
    {
        return nomJuge;
    }
    
    public String getPrenom()
    {
        return prenomJuge;
    }
    
    public void setNom(String unNom)
    {
        this.nomJuge = unNom;
    }
    
    public void setPrenom(String unPrenom)
    {
        this.prenomJuge = unPrenom;
    }

    public void save() throws SQLException {
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

        String requete = "SELECT id FROM juge WHERE nom = '"+ this.nomJuge +"' AND prenom = '"+this.prenomJuge+"'";

        ResultSet res = stmt.executeQuery(requete);

        if(res.next()){
            int lid = res.getInt("id");
            this.idJuge = lid;
            stmt.close();
        }else{
            stmt.close();
            String sql = "INSERT INTO juge(nom,prenom) values ('"+ this.nomJuge+"','"+ this.prenomJuge+"')";
            PreparedStatement ps = connexion.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                this.idJuge = generatedKey;

            }
        }

    }
}
