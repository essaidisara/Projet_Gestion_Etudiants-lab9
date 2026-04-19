<?php
include_once '../classes/Etudiant.php';
include_once '../connexion/Connexion.php';
include_once '../dao/IDao.php';

class EtudiantService implements IDao {
    private $db_connexion_ess;

    public function __construct() { 
        $this->db_connexion_ess = new Connexion(); 
    }

    public function create_ess($o_ess) {
        $query_ess = "INSERT INTO etudiant_ess (nom_ess, prenom_ess, ville_ess, sexe_ess)
                      VALUES (:nom, :prenom, :ville, :sexe)";
                      
        $stmt_ess = $this->db_connexion_ess->getConnexion_ess()->prepare($query_ess);
        $stmt_ess->execute([
            ':nom'    => $o_ess->getNom_ess(),
            ':prenom' => $o_ess->getPrenom_ess(),
            ':ville'  => $o_ess->getVille_ess(),
            ':sexe'   => $o_ess->getSexe_ess()
        ]);
    }

    public function findAllApi_ess() {
        $req_ess = $this->db_connexion_ess->getConnexion_ess()->query("SELECT * FROM etudiant_ess");
        return $req_ess->fetchAll(PDO::FETCH_ASSOC);
    }

    // Déclaration obligatoire des méthodes de l'interface
    public function delete_ess($o_ess) {}
    public function update_ess($o_ess) {}
    public function findAll_ess() {}
    public function findById_ess($id_ess) {}
}
?>