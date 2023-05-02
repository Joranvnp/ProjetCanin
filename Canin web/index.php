<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>


</body>
</html>

<?php
    header("Refresh:3");
    // echo date('H:i:s Y-m-d');

    // $connexion = new \PDO('mysql:host=172.19.0.9;dbname=AppClub;charset=utf8','phpmyadmin', '0550002D');
    $connexion = new \PDO('mysql:host=localhost;dbname=AppClub;charset=utf8','root', '');

    $req = $connexion->query('SELECT chien.prenom as prenomChien, conducteur.nom, conducteur.prenom FROM inscription 
    INNER JOIN chien ON chien.FAPAC = inscription.id_chien INNER JOIN conducteur ON inscription.id_conducteur = conducteur.id');

    $req2 = $connexion->query("SELECT evaluer.note as note, exercice.coef FROM inscription
    INNER JOIN evaluer ON evaluer.id_inscription = inscription.id
    INNER JOIN exercice ON exercice.id = evaluer.id");

    if($req != false)
    {
        $res = $req->fetchAll();
        $res2 = $req2->fetchAll();

        // echo "<table>";
        echo "<br>";


        echo "<table class=\"table\">
                <thead class=\"thead-dark\">
                    <tr>
                        <th scope=\"col\">#</th>
                        <th>Prenom du chien</th>
                        <th>Nom conducteur</th>
                        <th>Prenom conducteur</th>
                        <th>Note<th>
                    </tr>
                </thead>
                <tbody>

          ";

        echo "";

          $i = 0;
          foreach($res as $ligne)
            {
                $i = $i + 1;
                echo "
                    <tr>
                        <th scope=\"row\">$i</th>
                        <td>".$ligne["prenomChien"]."</td>
                        <td>".$ligne["nom"]."</td>
                        <td>".$ligne["prenom"]."</td>
                ";

                $i2 = 0;
                foreach($res2 as $ligne2)
                {
                    $i = $i + 1;
                    echo "
                        <td>".$ligne2["note"]."</td>
                    ";
                }
            }

        echo "      
                    </tr> 
                </tbody>
            </table>
            ";

    } else {
        var_dump($req->errorInfo());
        $req->debugDumpParams();
        echo "erreur";
    }
?>