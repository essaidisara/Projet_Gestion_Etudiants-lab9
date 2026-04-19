<?php
interface IDao {
    public function create_ess($o_ess);
    public function delete_ess($o_ess);
    public function update_ess($o_ess);
    public function findAll_ess();
    public function findById_ess($id_ess);
}
?>