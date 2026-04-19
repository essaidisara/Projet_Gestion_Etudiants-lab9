<?php
// On indique qu'on renvoie du texte au format JSON
header('Content-Type: application/json; charset=utf-8');

// 1. Connexion avec le VRAI nom de la base : school1_ess
$conn = mysqli_connect("localhost", "root", "", "school1_ess");

if (!$conn) {
    die(json_encode(["erreur" => "Erreur de connexion à la base de données"]));
}

// 2. Requête sur le VRAI nom de la table : etudiant_ess
$requete = "SELECT * FROM etudiant_ess";
$resultat = mysqli_query($conn, $requete);

$etudiants = array();

if ($resultat) {
    while($row = mysqli_fetch_assoc($resultat)) {
        $etudiants[] = $row;
    }
}

// On renvoie le résultat au format JSON
echo json_encode($etudiants);

mysqli_close($conn);
?>