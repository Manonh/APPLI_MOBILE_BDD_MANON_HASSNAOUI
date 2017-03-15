package com.example.manon.appli_mobile_bdd_manon_hassnaoui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class creation extends AppCompatActivity {

    private EditText name;
    private EditText desc;
    private Button valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        //on récupère les données des edittext name et description
        name=(EditText) findViewById(R.id.editName);
        desc=(EditText) findViewById(R.id.editDescription);
        valid=(Button) findViewById(R.id.add);

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result();
            }
        });
    }

    private void result(){
        Intent intent = new Intent();

        intent.putExtra("name", name.getText().toString());
        intent.putExtra("desc", desc.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
