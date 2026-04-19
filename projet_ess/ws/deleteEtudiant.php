<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id_ess'];
    
    // Connexion avec le VRAI nom de la base
    $conn = mysqli_connect("localhost", "root", "", "school1_ess");
    
    // Suppression dans la VRAIE table
    $requete = "DELETE FROM etudiant_ess WHERE id_ess = '$id'";
    
    if (mysqli_query($conn, $requete)) {
        echo "Succès";
    } else {
        echo "Erreur";
    }
    mysqli_close($conn);
}
?>