<?php
class Connexion {
    private $connexion_ess;

    public function __construct() {
        try {
            // Connexion à la base de données (modifiée à l'étape 1)
            $this->connexion_ess = new PDO("mysql:host=localhost;dbname=school1_ess;charset=utf8", "root", "");
            $this->connexion_ess->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e_ess) {
            die('Erreur : ' . $e_ess->getMessage());
        }
    }

    public function getConnexion_ess() {
        return $this->connexion_ess;
    }
}
?>