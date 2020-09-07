package br.com.fivecontacts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import br.com.fivecontacts.R;
import br.com.fivecontacts.models.Contato;
import br.com.fivecontacts.models.User;

public class PickContactsActivity extends AppCompatActivity {

    TextView mensagemTextView;
    Button salvarButton;
    ArrayList<Contato> contatos;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_contacts);

        mensagemTextView = findViewById(R.id.mensagemTextView);
        salvarButton = findViewById(R.id.salvarButton);


        //Dados da Intent Anterior
        Intent quemChamou = this.getIntent();
        if (quemChamou != null) {
            Bundle params = quemChamou.getExtras();
            if (params != null) {
                Log.v("Ed", "Recuperando o Usuario");
                user = (User) params.getSerializable("usuario");
                if (user != null) {
                    Log.v("Ed", "Usuario recuperado");
                }
            }
        }


        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contato c1 = new Contato("Pai", "tel:9999999");
                Contato c2 = new Contato("Mãe", "tel:8888888");
                Contato c3 = new Contato("Irmão", "tel:7777777");
                Contato c4 = new Contato("Irmã", "tel:6666666");
                Contato c5 = new Contato("Tia", "tel:5555555");

                SharedPreferences salvaListaContatos = getSharedPreferences("listaContatos", Activity.MODE_PRIVATE);
                final SharedPreferences.Editor editor = salvaListaContatos.edit();
                int numContatos = 5;
                Log.v("Ed", "num contatos = " + numContatos);
                editor.putInt("numContatos", numContatos);

                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(c1);
                    String contatoSerializado = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
                    editor.putString("contato1", contatoSerializado);
                    Log.v("Ed", "c1");

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(c2);
                    contatoSerializado = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
                    editor.putString("contato2", contatoSerializado);
                    Log.v("Ed", "c2");

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(c3);
                    contatoSerializado = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
                    editor.putString("contato3", contatoSerializado);
                    Log.v("Ed", "c3");

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(c4);
                    contatoSerializado = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
                    editor.putString("contato4", contatoSerializado);
                    Log.v("Ed", "c4");

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(c5);
                    contatoSerializado = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
                    editor.putString("contato5", contatoSerializado);
                    Log.v("Ed", "c5");

                    editor.commit();

                } catch (IOException e) {
                    e.printStackTrace();
                }



                Intent intent = new Intent(PickContactsActivity.this, ListaDeContatos_ListViewActivity.class);
                //intent.putExtra("listaContatos", contatos);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Ed", "matando a activity Lista de Contatos");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Ed", "matei a activity Lista de Contatos");
    }
}