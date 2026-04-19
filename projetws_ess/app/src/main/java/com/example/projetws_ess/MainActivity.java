package com.example.projetws_ess;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nom_ess, prenom_ess;
    private Spinner ville_ess;
    private RadioButton m_ess, f_ess;
    private Button add_ess;
    private Button btn_voir_liste;
    private RequestQueue requestQueue_ess;

    private static final String insertUrl_ess = "http://10.0.2.2/projet_ess/ws/createEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom_ess = findViewById(R.id.nom_ess);
        prenom_ess = findViewById(R.id.prenom_ess);
        ville_ess = findViewById(R.id.ville_ess);
        m_ess = findViewById(R.id.m_ess);
        f_ess = findViewById(R.id.f_ess);
        add_ess = findViewById(R.id.add_ess);

        btn_voir_liste = findViewById(R.id.btn_voir_liste);
        btn_voir_liste.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListEtudiantActivity.class);
            startActivity(intent);
        });

        requestQueue_ess = Volley.newRequestQueue(this);
        add_ess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == add_ess) {
            envoyerEtudiant_ess();
        }
    }

    private void envoyerEtudiant_ess() {
        StringRequest request_ess = new StringRequest(Request.Method.POST, insertUrl_ess,
                response -> {
                    Toast.makeText(MainActivity.this, "Serveur : " + response, Toast.LENGTH_LONG).show();
                    Log.d("RESPONSE_ESS", response);
                },
                error -> {
                    Toast.makeText(MainActivity.this, "Erreur réseau : " + error.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("VOLLEY_ESS", "Erreur : " + error.getMessage());
                }) {

            @Override
            protected Map<String, String> getParams() {
                String sexe = m_ess.isChecked() ? "homme" : "femme";
                Map<String, String> params = new HashMap<>();

                params.put("nom_ess", nom_ess.getText().toString());
                params.put("prenom_ess", prenom_ess.getText().toString());
                params.put("ville_ess", ville_ess.getSelectedItem().toString());
                params.put("sexe_ess", sexe);

                return params;
            }
        };
        requestQueue_ess.add(request_ess);
    }
}