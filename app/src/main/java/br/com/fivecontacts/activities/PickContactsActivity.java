package br.com.fivecontacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.fivecontacts.R;

public class PickContactsActivity extends AppCompatActivity {

    TextView mensagemTextView;
    Button salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_contacts);

        mensagemTextView = findViewById(R.id.mensagemTextView);
        salvarButton = findViewById(R.id.salvarButton);

        Intent quemChamou = this.getIntent();

        if (quemChamou != null){

        }

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Edvardo", "matando a activity Lista de Contatos");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Edvardo", "matei a activity Lista de Contatos");
    }
}