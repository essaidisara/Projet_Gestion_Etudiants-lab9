package com.example.projetws_ess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListEtudiantActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EtudiantAdapter adapter;
    private RequestQueue requestQueue;

    private static final String URL_LOAD = "http://10.0.2.2/projet_ess/ws/loadEtudiant.php";
    private static final String URL_DELETE = "http://10.0.2.2/projet_ess/ws/deleteEtudiant.php";
    private static final String URL_UPDATE = "http://10.0.2.2/projet_ess/ws/updateEtudiant.php"; // Nouvelle URL pour la modification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        recyclerView = findViewById(R.id.recyclerViewEtudiants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(this);

        chargerEtudiants();
    }

    // 1. CHARGER LA LISTE
    private void chargerEtudiants() {
        StringRequest request = new StringRequest(Request.Method.GET, URL_LOAD,
                response -> {
                    try {
                        Type type = new TypeToken<List<Etudiant>>(){}.getType();
                        List<Etudiant> listeEtudiants = new Gson().fromJson(response, type);

                        adapter = new EtudiantAdapter(listeEtudiants, etudiant -> {
                            afficherPopupOptions(etudiant);
                        });
                        recyclerView.setAdapter(adapter);
                    } catch (Exception e) {
                        Toast.makeText(this, "Erreur de format de données", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Erreur de chargement", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(request);
    }

    // 2. POPUP D'OPTIONS
    private void afficherPopupOptions(Etudiant etudiant) {
        String[] options = {"Modifier", "Supprimer"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options - " + etudiant.getNom_ess());
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                afficherDialogModification(etudiant); // On ouvre la fenêtre de modification
            } else {
                confirmerSuppression(etudiant);
            }
        });
        builder.show();
    }

    // 3. FENÊTRE DE MODIFICATION
    private void afficherDialogModification(Etudiant etudiant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier l'étudiant");

        // Création des champs de texte
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        final EditText editNom = new EditText(this);
        editNom.setText(etudiant.getNom_ess());
        editNom.setHint("Nom");
        layout.addView(editNom);

        final EditText editPrenom = new EditText(this);
        editPrenom.setText(etudiant.getPrenom_ess());
        editPrenom.setHint("Prénom");
        layout.addView(editPrenom);

        builder.setView(layout);

        builder.setPositiveButton("Enregistrer", (dialog, which) -> {
            String nouveauNom = editNom.getText().toString().trim();
            String nouveauPrenom = editPrenom.getText().toString().trim();

            if (!nouveauNom.isEmpty() && !nouveauPrenom.isEmpty()) {
                modifierEtudiantEnBase(etudiant, nouveauNom, nouveauPrenom);
            } else {
                Toast.makeText(this, "Les champs ne peuvent pas être vides", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Annuler", null);
        builder.show();
    }

    // 4. ENVOI DE LA MODIFICATION AU SERVEUR
    private void modifierEtudiantEnBase(Etudiant etudiant, String nom, String prenom) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE,
                response -> {
                    Toast.makeText(this, "Étudiant modifié !", Toast.LENGTH_SHORT).show();
                    chargerEtudiants(); // On recharge la liste
                },
                error -> Toast.makeText(this, "Erreur de modification", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_ess", String.valueOf(etudiant.getId_ess()));
                params.put("nom_ess", nom);
                params.put("prenom_ess", prenom);
                return params;
            }
        };
        requestQueue.add(request);
    }

    // 5. ALERTE DE CONFIRMATION AVANT SUPPRESSION
    private void confirmerSuppression(Etudiant etudiant) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Voulez-vous vraiment supprimer cet étudiant ?")
                .setPositiveButton("Oui, supprimer", (dialog, which) -> supprimerEtudiant(etudiant))
                .setNegativeButton("Annuler", null)
                .show();
    }

    // 6. SUPPRESSION EN BACKEND ET ACTUALISATION
    private void supprimerEtudiant(Etudiant etudiant) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_DELETE,
                response -> {
                    Toast.makeText(this, "Étudiant supprimé !", Toast.LENGTH_SHORT).show();
                    chargerEtudiants(); // On recharge la liste pour voir la suppression
                },
                error -> Toast.makeText(this, "Erreur de suppression", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_ess", String.valueOf(etudiant.getId_ess()));
                return params;
            }
        };
        requestQueue.add(request);
    }
}