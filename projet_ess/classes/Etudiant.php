<?php
class Etudiant {
    private $id_ess, $nom_ess, $prenom_ess, $ville_ess, $sexe_ess;

    public function __construct($id_ess, $nom_ess, $prenom_ess, $ville_ess, $sexe_ess) {
        $this->id_ess = $id_ess;
        $this->nom_ess = $nom_ess;
        $this->prenom_ess = $prenom_ess;
        $this->ville_ess = $ville_ess;
        $this->sexe_ess = $sexe_ess;
    }
    
    // Getters adaptés
    public function getNom_ess() { return $this->nom_ess; }
    public function getPrenom_ess() { return $this->prenom_ess; }
    public function getVille_ess() { return $this->ville_ess; }
    public function getSexe_ess() { return $this->sexe_ess; }
}
?>