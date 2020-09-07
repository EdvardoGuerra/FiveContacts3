package br.com.fivecontacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fivecontacts.R;
import br.com.fivecontacts.models.User;

public class MainActivity extends AppCompatActivity {

    EditText loginEditText;
    EditText senhaEditText;
    Button entrarButton;
    Button novoButton;
    TextView esqueceuTextView;
//    boolean primeiraVezUser = true;
//    boolean primeiraVezSenha = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (montarObjetoSemUsuarioLogar()){
            User user = montarObjetoUser();
            Intent intent = new Intent(this, ListaDeContatos_ListViewActivity.class);
            intent.putExtra("usuario", user);
            startActivity(intent);
            finish();
        }

        iniciarElementos();

        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences temUser =
                        getSharedPreferences("usuarioPadrao", Activity.MODE_PRIVATE);

                String nomeSalvo = temUser.getString("nome", "");
                String loginSalvo = temUser.getString("login", "");
                String senhaSalva = temUser.getString("senha", "");
                String emailSalvo = temUser.getString("email", "");

                if ((loginSalvo != null) && (senhaSalva != null)) {
                    String senha = senhaEditText.getText().toString();
                    String login = loginEditText.getText().toString();

                    if ((loginSalvo.compareTo(login) == 0) && (senhaSalva.compareTo(senha) == 0)) {
                        User user = montarObjetoUser();
                        Intent intent = new Intent(MainActivity.this, ListaDeContatos_ListViewActivity.class);
                        intent.putExtra("usuario", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Login e Senha Incorretos",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Login e Senha Nulos",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        novoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NovoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void iniciarElementos() {
        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        entrarButton = findViewById(R.id.alterarButton);
        novoButton = findViewById(R.id.novoButton);
        esqueceuTextView = findViewById(R.id.esqueceuTextView);
        esqueceuTextView.setPaintFlags(esqueceuTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private User montarObjetoUser() {
        SharedPreferences temUser =
                getSharedPreferences("usuarioPadrao", Activity.MODE_PRIVATE);
        String nomeSalvo = temUser.getString("nome", "");
        String loginSalvo = temUser.getString("login", "");
        String senhaSalva = temUser.getString("senha", "");
        String emailSalvo = temUser.getString("email", "");
        boolean manterLogadoSalvo = temUser.getBoolean("manterLogado", false);
        User user = new User(nomeSalvo, loginSalvo, senhaSalva, emailSalvo);
        return user;
    }

    private boolean montarObjetoSemUsuarioLogar() {
        SharedPreferences temUser =
                getSharedPreferences("usuarioPadrao", Activity.MODE_PRIVATE);
        return temUser.getBoolean("manterLogado", false);
    }

}