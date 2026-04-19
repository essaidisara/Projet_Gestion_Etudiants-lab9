<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id_ess'];
    $nom = $_POST['nom_ess'];
    $prenom = $_POST['prenom_ess'];
    
    // Connexion
    $conn = mysqli_connect("localhost", "root", "", "school1_ess");
    
    // Mise à jour
    $requete = "UPDATE etudiant_ess SET nom_ess = '$nom', prenom_ess = '$prenom' WHERE id_ess = '$id'";
    
    if (mysqli_query($conn, $requete)) {
        echo "Succès";
    } else {
        echo "Erreur";
    }
    mysqli_close($conn);
}
?>