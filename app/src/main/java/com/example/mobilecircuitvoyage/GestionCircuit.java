package com.example.mobilecircuitvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GestionCircuit extends AppCompatActivity implements View.OnClickListener{

    Button blister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_circuit);
        ajouterEvents();
    }

    /*
    * Add on click button Lister
    */
    public void ajouterEvents()
   {
       blister = (Button) findViewById(R.id.blister);
       blister.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.blister:
                lister();
                break;
        }
    }

    /*
    * FAIT APPEL À UNE AUTRE ACTIVITÉ
    */
    public void lister()
    {
        Intent intent = new Intent(GestionCircuit.this, ListerActivity.class);
        // SEND TEXT TO ACTIVITY 2
        intent.putExtra("titre", "Liste de Membres");
        // SEND
        startActivity(intent);
    }

}