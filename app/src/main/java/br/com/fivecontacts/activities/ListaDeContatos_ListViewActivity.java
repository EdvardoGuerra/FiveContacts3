package br.com.fivecontacts.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.fivecontacts.R;
import br.com.fivecontacts.models.User;

public class ListaDeContatos_ListViewActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    User user;
    ListView contatosListView;
    String[] itens = {"filha", "filho", "neto"};
    String[] numeros = {"tel:000000003435", "tel:2000348835", "tel:1003435888"};
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contatos);

        contatosListView = findViewById(R.id.contatosListView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        preencherListaDeContatos();

        Intent quemChamou = this.getIntent();
        if (quemChamou != null) {
            Bundle params = quemChamou.getExtras();
            if (params != null) {
                User u1 = (User) params.getSerializable("usuario");
                if (u1 != null) {
                    Intent intent = new Intent(this, AlterarUsuarioActivity.class);
                    intent.putExtra("usuario", user);
                    startActivityForResult(intent, 1111);
                }
            }
        }
    }

    private void preencherListaDeContatos() {
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
        contatosListView.setAdapter(adapter);
        contatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListaDeContatos_ListViewActivity.this,
                        "Na lista", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        if (item.getItemId()==R.id.anvLigar){
//
//        }
        if (item.getItemId() == R.id.anvPerfil) {
            Intent intent = new Intent(this, AlterarUsuarioActivity.class);
            startActivityForResult(intent, 1111);
        }
//        if (item.getItemId()==R.id.anvMudar){
//
//        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bottomNavigationView.setSelectedItemId(R.id.anvLigar);
    }
}