package com.example.mobilecircuitvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListerActivity extends AppCompatActivity {

    private TextView textView;
    ListView listeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister);

        initWidgets();
        getDataFromMainActivity();
        setEvenementButton();
        lister();
    }

    /**
     *Get all reference widgets
     */
    private void initWidgets()
    {
        textView = (TextView) findViewById(R.id.txtDisplayContent);
        listeView = (ListView) findViewById(R.id.listeView);
    }

    /**
     * Retour à la page precedente
     */
    private void setEvenementButton()
    {
        Button b = (Button)findViewById(R.id.btnRetourner);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListerActivity.this.finish();//Sans retour de données
            }
        });
/*        Button btnSnackbar = (Button)findViewById(R.id.btnSnackbar);
        btnSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "content here", Snackbar.LENGTH_LONG).setAction("action", null).show();
                Intent showTel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(514) 546 0521"));
                startActivity(showTel);
            }
        });*/
    }

    /*
    * EXTRACT DATA FROM GestionCircuit.Java
    */
    private void getDataFromMainActivity()
    {
        Intent intent  = getIntent();
        //GET TEXT FROM ACTIVITY 1
        String titre = intent.getExtras().getString("titre");
        //SET CONTENT TEXT IN TO WIDGET
        textView.setText(titre);
    }

    public void lister() {

        final ArrayList<HashMap<String, Object>> tabMembres = new ArrayList<HashMap<String, Object>>();

        String url = "http://10.0.2.2/PROJETS/MobileCircuitVoyage/PHP/membreControleurJSON.php";

        StringRequest requete = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                                Log.d("RESULTAT", response);
                                int i,j;
                                JSONArray jsonResponse = new JSONArray(response);
                                HashMap<String, Object> map;
                                String msg = jsonResponse.getString(0);

                            if(msg.equals("OK"))
                            {
                                JSONObject unMembre;
                                for(i = 1; i < jsonResponse.length(); i++)
                                {
                                    unMembre = jsonResponse.getJSONObject(i);
                                    map = new HashMap<String, Object>();
                                    map.put("idMembre", unMembre.getString("idMembre"));
                                    map.put("nom", unMembre.getString("nom"));
                                    map.put("prenom", unMembre.getString("prenom"));
                                    map.put("courriel", unMembre.getString("courriel"));
                                    map.put("telephone", unMembre.getString("telephone"));
                                    tabMembres.add(map);
                                }

                                SimpleAdapter monAdapter = new SimpleAdapter (ListerActivity.this, tabMembres, R.layout.lister_membre_map,
                                        new String[] { "idMembre", "nom", "prenom", "courriel", "telephone"},
                                        new int[] {R.id.idMembre, R.id.nom, R.id.prenom, R.id.courriel, R.id.telephone});
                                listeView.setAdapter(monAdapter);
                            }
                            else{}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ListerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // Les parametres pour POST
                params.put("action", "lister");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(requete);//Si Volley rouge clique Volley et choisir add dependency on module volley
    }



}//ENd class
