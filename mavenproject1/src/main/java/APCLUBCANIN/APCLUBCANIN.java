/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package APCLUBCANIN;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;



/**
 *
 * @author kmudler
 */
public class APCLUBCANIN {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://172.19.0.3:3306/AppClub";
        //String URL = "jdbc:mysql://localhost:3306/AppClub";
        // login et mdp de la base

        //String LOGIN = "root";
        String LOGIN = "phpmyadmin";
        String PASSWORD = "0550002D";
        //String PASSWORD = "";

        try
        {
            Connection connexion = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            // Objet statement : représente une instruction SQL
            Statement stmt = connexion.createStatement();


            FileInputStream file = new FileInputStream(new File("D:\\_Classeur pointages classe 1_.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFCell cell = sheet.getRow(3).getCell(4);
            String licence = sheet.getRow(8).getCell(31).toString();
            String ID = sheet.getRow(4).getCell(28).toString();
            String regional = sheet.getRow(6).getCell(5).toString();
            String proprietaire = sheet.getRow(7).getCell(6).toString();
            String licenceP = sheet.getRow(7).getCell(31).toString();


            //Information Club
            String nomClub = sheet.getRow(5).getCell(5).toString();
            Club unClub = new Club(-1,nomClub);

            // vérif si le Club a besoin d'être créé, sinon récup de son ID dans l'objet
            unClub.unsave();

            // Information sur le juge
            String juge = sheet.getRow(35).getCell(0).toString();
            String nomJuge = juge.split(" ")[0];
            String prenomJuge = juge.split(" ")[1];
            Juge leJuge = new Juge(-1, nomJuge, prenomJuge);

            // vérif si le juge a besoin d'être créé, sinon récup de son ID dans l'objet
            leJuge.save();


            //Information Chien
            String nomDuChien = cell.toString();
            String fapac = sheet.getRow(3).getCell(31).toString();
            String race = sheet.getRow(4).getCell(3).toString();
            String sexe = sheet.getRow(4).getCell(16).toString();
            String datenaissance = sheet.getRow(4).getCell(19).toString();
            String ct = sheet.getRow(4).getCell(24).toString();

            String nouvdatenaissance = formatdate(datenaissance);

            String requete = "SELECT id FROM club WHERE nom = '"+ nomClub +"'";

            ResultSet club = stmt.executeQuery(requete);
            club.next();

            int idClub = club.getInt("id");


            Chien unChien = new Chien(fapac,nomDuChien,race,sexe,nouvdatenaissance,ct,idClub);

            unChien.lajout();

            //Information conducteur
            String conducteur = sheet.getRow(8).getCell(6).toString();

            String nomConducteur = conducteur.split(" ")[1];
            String prenomConducteur = conducteur.split(" ")[2];

            Conducteur unConducteur = new Conducteur(-1,nomConducteur,prenomConducteur);
            unConducteur.AjoutConducteur();


            // Informations sur le concours
            String nomconcours = sheet.getRow(0).getCell(5).toString();
            String date = sheet.getRow(3).getCell(36).toString();

            // construction de l'object concours
            String dateDebut = formatdate(date.split(" ")[0]); // Date sous la forme jj/mm/aaaa
            String dateFin = formatdate(date.split(" ")[2]);

            String req = "SELECT id FROM juge WHERE nom = '"+ nomJuge +"' AND prenom = '"+prenomJuge+"'";
            ResultSet res1 = stmt.executeQuery(req);
            res1.next();

            int lid = res1.getInt("id");

            Concours leConcours = new Concours(-1, nomconcours.replace("'", "\\'"), dateDebut, dateFin,lid);

            leConcours.AjoutConcours();

            //Informations sur la classe
            String classe = sheet.getRow(0).getCell(21).toString();

            int nbExo = 0;
            //Objet classe
            Classe uneClasse = new Classe(-1,classe,nbExo);
            uneClasse.AjoutClasse();


            System.out.println(classe + " chien: " +nomDuChien + "N fapac :" + fapac + " conduit par : " + conducteur +  " ("+licence+")");

            System.out.println("Race du chien : " + race);
            System.out.println("Le sexe du chien : " + sexe);
            System.out.println("Le ct du chien : " + ct);
            System.out.println("Né le : " + datenaissance);
            System.out.println("ID : " + ID);
            System.out.println("Régionale : " + regional);
            System.out.println("Popriétaire : " + proprietaire + " ("+licenceP+")");
            System.out.println("Juge : " + juge);
            System.out.println("Concours nom : " + nomconcours);
            System.out.println("dateDeb : " + date.split(" ")[0]);
            System.out.println("dateFin : " + date.split(" ")[2]);


            boolean fin = false;

            String exo;
            double note;
            double coeff;
            String commentaire;

            do {
                 exo = sheet.getRow(14 + nbExo).getCell(0).toString();
                if (exo != "") {
                     note = Double.parseDouble(sheet.getRow(14 + nbExo).getCell(12).toString().replace(',', '.'));
                     coeff = Double.parseDouble(sheet.getRow(14 + nbExo).getCell(15).toString().replace(',', '.'));
                     commentaire = sheet.getRow(14 + nbExo).getCell(21).toString();
                    nbExo += 1;

                    //Insertion dans la classe exercice
                    PreparedStatement reqe;
                    reqe = connexion.prepareStatement("SELECT count(*) as nb FROM exercice WHERE libelle = ? and coef = ?");
                    reqe.setString(1, exo);
                    reqe.setDouble(2, coeff);
                    ResultSet res7 = reqe.executeQuery();
                    res7.next();

                    if(res7.getInt("nb") == 0){
                        String env = "SELECT id FROM classe WHERE libelle = '"+classe+"'";
                        ResultSet rslt = stmt.executeQuery(env);
                        rslt.next();

                        int uid = rslt.getInt("id");

                        Exercice unExercice = new Exercice(exo.replace("'", "\\'"),coeff,uid);
                        unExercice.AjoutExercice();
                    }
                }
                else {
                    fin = true;
                }
            } while (fin == false);


            String am = "UPDATE classe SET nbExo = '"+nbExo+"'";
           stmt.executeUpdate(am);





            //inscription
            String re5 = "SELECT id FROM concours WHERE nom = '"+ nomconcours.replace("'", "\\'") +"'";

            ResultSet res5 = stmt.executeQuery(re5);

            res5.next();

            int idcon = res5.getInt("id");

            String requete2 = "SELECT id FROM conducteur WHERE nom = '"+ nomConducteur +"' AND prenom ='"+prenomConducteur+"'";

            ResultSet res6 = stmt.executeQuery(requete2);

            res6.next();
            int idcond = res6.getInt("id");

            String requete3 = "SELECT id FROM classe WHERE libelle  = '"+classe+"'";

            ResultSet res7 = stmt.executeQuery(requete3);

            res7.next();
            int idcla = res7.getInt("id");

            String requete4 = "SELECT FAPAC FROM chien WHERE FAPAC = '"+fapac+"'";

            ResultSet res8 = stmt.executeQuery(requete4);

            res8.next();

            int idfa = res8.getInt("FAPAC");

            ResultSet ins = stmt.executeQuery("SELECT count(*) as nb FROM inscription WHERE id_concours = (SELECT id_concours FROM concours WHERE nom = '" + nomconcours.replace("'", "\\'") + "') AND id_chien = '"+ fapac.split("\\.")[0] +"'");
            ins.next();
            if (ins.getInt("nb") == 0) {

                Inscription uneInscription = new Inscription(idcon,idcond,idcla,idfa);
                uneInscription.linscription();
            }

            int i = 0;

            boolean rfin = false;

            do {
                exo = sheet.getRow(14 + i).getCell(0).toString();
                if (exo != "") {
                    note = Double.parseDouble(sheet.getRow(14 + i).getCell(12).toString().replace(',', '.'));
                    commentaire = sheet.getRow(14 + i).getCell(21).toString();
                    i += 1;

                    //Insertion dans la classe evaluer
                    String reque3 = "SELECT id FROM exercice WHERE libelle = '"+ exo.replace("'", "\\'") +"'";

                    ResultSet resu7 = stmt.executeQuery(reque3);

                    resu7.next();
                    int idd = resu7.getInt("id");

                    String reque4 = "SELECT id FROM inscription WHERE id_chien = '" + fapac.split("\\.")[0]+"'";

                    ResultSet resu8 = stmt.executeQuery(reque4);

                    resu8.next();
                    int id = resu8.getInt("id");

                    Evaluer unevaluation = new Evaluer(idd,id,note,commentaire);
                    unevaluation.Eval();
                }
                else {
                    rfin = true;
                }
            } while (rfin == false);


            file.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERREUR");

        }


    }
    static String formatdate(String ldate){
        return ldate.split("/")[2]+"-"+ ldate.split("/")[1]+"-"+ldate.split("/")[0];

    }


}
