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
public class Concours {
    private int idConcours;
    private String nomConcours;
    private String dateDebut;
    private String dateFin;

    private int Idjuge;

    //public Juge unJuge;

    
    public Concours(int unId, String unNom, String uneDateDebut, String uneDateFin,int lidjuge)
    {
        this.idConcours = unId;
        this.nomConcours = unNom;
        this.dateDebut = uneDateDebut;
        this.dateFin = uneDateFin;
        this.Idjuge = lidjuge;
        //this.unJuge = leJuge;
    }
    
    public int getId()
    {
        return idConcours;
    }
    
    public String getNom()
    {
        return nomConcours;
    }
    
    public String getDateDebut()
    {
        return dateDebut;
    }
    
    public String getDateFin()
    {
        return dateFin;
    }
    
    public void setNom(String unNom)
    {
        this.nomConcours = unNom;
    }
    
    public void setDateDebut(String uneDateDebut)
    {
        this.dateDebut = uneDateDebut;
    }
    
    public void setDateFin(String uneDateFin)
    {
        this.dateFin = uneDateFin;
    }

    /*public Juge getJuge()
    {
        return this.unJuge;
    }*/



    public void AjoutConcours() throws SQLException {
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

       ResultSet res = stmt.executeQuery("SELECT id,count(nom) as nom FROM concours WHERE nom = '"+nomConcours+"';");

        res.next();

       if(res.getInt("nom") == 1){
           int lid = res.getInt("id");
           this.idConcours = lid;
           stmt.close();

       }
       else{
            stmt.close();


            String sql = "INSERT INTO concours (nom, dateDeb, dateFin, id_juge) VALUES ('"+nomConcours+"', '"+dateDebut+"', '"+dateFin+"', '"+Idjuge+"')";
            PreparedStatement ps = connexion.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                this.idConcours = generatedKey;
            }
        }


    }
}
