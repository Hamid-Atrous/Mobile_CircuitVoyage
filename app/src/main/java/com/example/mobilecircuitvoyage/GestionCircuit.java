package com.example.mobilecircuitvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GestionCircuit extends AppCompatActivity implements View.OnClickListener{

    Button blister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_circuit);
        ajouterEvents();
    }

    public void ajouterEvents()
   {
       blister = (Button) findViewById(R.id.blister);
       blister.setOnClickListener(this);
//        benreg = (Button) findViewById(R.id.benreg);
//        benreg.setOnClickListener(this);
//        bmodifier = (Button) findViewById(R.id.bmodifier);
//        bmodifier.setOnClickListener(this);
//        benlever = (Button) findViewById(R.id.benlever);
//        benlever.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.blister:
                lister();
                break;
//            case R.id.benreg:
//                enregistrer();
//                break;
//            case R.id.bmodifier:
//                modifier();
//                break;
//            case R.id.benlever:
//                enlever();
//                break;
        }
    }

    public void lister(){
        Intent IntLister = new Intent(GestionCircuit.this, ListerActivity.class);
        startActivity(IntLister);
    }

}