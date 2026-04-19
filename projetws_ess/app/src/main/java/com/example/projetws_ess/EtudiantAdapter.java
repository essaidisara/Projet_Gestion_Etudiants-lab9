package com.example.projetws_ess;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.ViewHolder> {

    private List<Etudiant> etudiants;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Etudiant etudiant);
    }

    public EtudiantAdapter(List<Etudiant> etudiants, OnItemClickListener listener) {
        this.etudiants = etudiants;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etudiant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Etudiant etudiant = etudiants.get(position);

        // --- LA CORRECTION EST ICI : String.valueOf() empêche le plantage de l'application ! ---
        holder.idTxt.setText(String.valueOf(etudiant.getId_ess()));

        holder.nomTxt.setText(etudiant.getNom_ess());
        holder.prenomTxt.setText(etudiant.getPrenom_ess());

        // On écoute le clic sur toute la ligne pour ouvrir le popup
        holder.itemView.setOnClickListener(v -> listener.onItemClick(etudiant));
    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTxt, nomTxt, prenomTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTxt = itemView.findViewById(R.id.id_txt);
            nomTxt = itemView.findViewById(R.id.nom_txt);
            prenomTxt = itemView.findViewById(R.id.prenom_txt);
        }
    }
}