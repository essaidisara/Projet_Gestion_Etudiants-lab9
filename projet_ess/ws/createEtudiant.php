<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../service/EtudiantService.php';
    
    // Récupération sécurisée et explicite au lieu du extract()
    $nom_ess    = $_POST['nom_ess'] ?? '';
    $prenom_ess = $_POST['prenom_ess'] ?? '';
    $ville_ess  = $_POST['ville_ess'] ?? '';
    $sexe_ess   = $_POST['sexe_ess'] ?? '';

    $es_ess = new EtudiantService();
    $es_ess->create_ess(new Etudiant(1, $nom_ess, $prenom_ess, $ville_ess, $sexe_ess));

    header('Content-Type: application/json');
    echo json_encode($es_ess->findAllApi_ess());
}
?>