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
public class Classe {

    private int idClasse;
    private String libelleClasse;

    private int nombre;
    
    public Classe(int unidClasse,String unLibelle,int lnombre)
    {

        this.libelleClasse = unLibelle;
        this.idClasse = unidClasse;
        this.nombre = lnombre;
    }




    public String getLibelle()
    {
        return libelleClasse;
    }
    public int getid()
    {
        return idClasse;
    }
    
    public void setLibelle(String unLibelle)
    {
        this.libelleClasse = unLibelle;
    }

    public void AjoutClasse() throws SQLException {
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

        ResultSet res = stmt.executeQuery("SELECT id,count(libelle) as libelle FROM classe WHERE libelle = '"+libelleClasse+"';");


        if(res.next()){
            int lid = res.getInt("id");
            this.idClasse = lid;
            stmt.close();
        }else{
            stmt.close();
            String sql = "INSERT INTO classe (libelle, nbExo) VALUES ('"+libelleClasse+"', '"+nombre+"')";
            PreparedStatement ps = connexion.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                this.idClasse = generatedKey;
            }
        }


    }
}
