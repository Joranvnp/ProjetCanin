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
public class Club {
    private int idClub;
    private String nomClub;

    
    public Club(int unId, String unNom)
    {
        this.idClub = unId;
        this.nomClub = unNom;

    }
    
    public int getId()
    {
        return idClub;
    }
    
    public String getNom()
    {
        return nomClub;
    }

    public void setNom(String unNom)
    {
        this.nomClub = unNom;
    }

    public void unsave() throws SQLException {
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

        String requete = "SELECT id FROM club WHERE nom = '"+ this.nomClub +"'";

        ResultSet res = stmt.executeQuery(requete);

        if(res.next()){
            int idclub = res.getInt("id");
            this.idClub =  idclub;
            stmt.close();
        }else{
            stmt.close();
            String sql = "INSERT INTO club(nom) values ('"+ this.nomClub  +"')";
            PreparedStatement ps = connexion.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                this.idClub = generatedKey;
            }
        }
    }
    

}
