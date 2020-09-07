package br.com.fivecontacts.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import br.com.fivecontacts.R;
import br.com.fivecontacts.models.Contato;
import br.com.fivecontacts.models.User;

public class ListaDeContatos_ListViewActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, UIEducacionalPermissao.NoticeDialogListener {

    User user;
    ListView contatosListView;
    String[] itens;
    String[] numeros;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contatos);

        contatosListView = findViewById(R.id.contatosListView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        Intent quemChamou = this.getIntent();
        if (quemChamou != null) {
            Bundle params = quemChamou.getExtras();
            if (params != null) {
                User u1 = (User) params.getSerializable("usuario");
                if (u1 != null) {

                }
            }
        }

        preencherListaDeContatos();
    }

    private void preencherListaDeContatos() {


        SharedPreferences recuperarContatos = getSharedPreferences("listaContatos", Activity.MODE_PRIVATE);
        int numContatos = recuperarContatos.getInt("numContatos", 0);
        final ArrayList<Contato> contatos = new ArrayList<>();


        String obj1 = recuperarContatos.getString("contato1", "");
        if (!obj1.equals("")) {
            try {
                Contato contato;
                Log.v("Ed", "Recuperando contato1");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(obj1.getBytes(StandardCharsets.ISO_8859_1.name()));
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                contato = (Contato) objectInputStream.readObject();
                if (contato != null) {
                    contatos.add(contato);
                    Log.i("Ed", "adicionou contato1");
                } else {
                    Log.i("Ed", "nulo");
                }
            } catch (Exception e) {
                Log.v("Ed", "deu merda");
            }
        }

//        for (int i = 0; i <= numContatos; i++) {
//            String obj = recuperarContatos.getString("contato" + i + 1, "");
//            if (obj.compareTo("") != 0) try {
//                ByteArrayInputStream byteArrayInputStream =
//                        new ByteArrayInputStream(obj.getBytes(StandardCharsets.ISO_8859_1.name()));
//                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//                contato = (Contato) objectInputStream.readObject();
//                if (contato != null) {
//                    contatos.add(contato);
//                    Log.i("Ed", "adicionou " + i);
//                } else {
//                    Log.i("Ed", "nulo");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        Log.v("Ed", "contatos : " + contatos.size());
        if (contatos != null) {
            final String[] nomesSP, telsSP;
            nomesSP = new String[5];
            telsSP = new String[5];

            if (contatos.get(0) != null) {
                Object[] contato1Dados = contatos.toArray();
                nomesSP[0] = (String) contato1Dados[1];
                telsSP[0] = (String) contato1Dados[0];
            }
            if (contatos.get(1) != null) {
                Object[] contato2Dados = contatos.toArray();
                nomesSP[0] = (String) contato2Dados[1];
                telsSP[0] = (String) contato2Dados[0];
            }
            if (contatos.get(2) != null) {
                Object[] contato3Dados = contatos.toArray();
                nomesSP[0] = (String) contato3Dados[1];
                telsSP[0] = (String) contato3Dados[0];
            }
            if (contatos.get(3) != null) {
                Object[] contato4Dados = contatos.toArray();
                nomesSP[0] = (String) contato4Dados[1];
                telsSP[0] = (String) contato4Dados[0];
            }
            if (contatos.get(4) != null) {
                Object[] contato5Dados = contatos.toArray();
                nomesSP[0] = (String) contato5Dados[1];
                telsSP[0] = (String) contato5Dados[0];
            }


            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomesSP);
            contatosListView.setAdapter(adapter);
            contatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (checarPermissaoPhone()) {
                        Uri uri = Uri.parse(contatos.get(i).getNumero());
                        Intent ligarIntent = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(ligarIntent);
                    }
                }
            });
        } else {
            Toast.makeText(this, "Nulo", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean checarPermissaoPhone() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            String[] permissoes = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this, permissoes, 1212);
        }
        return false;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bottomNavigationView.setSelectedItemId(R.id.anvLigar);
    }

    @Override
    public void onDialogPositiveClick(int codigo) {
        Log.v("edvardo", "Clicou no OK");
    }
}