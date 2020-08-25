package br.com.fivecontacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.fivecontacts.R;
import br.com.fivecontacts.models.User;

public class NovoActivity extends AppCompatActivity {

    EditText nomeEditText;
    EditText loginEditText;
    EditText senhaEditText;
    EditText emailEditText;
    SwitchCompat manterSwitch;
    Button criarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);

        nomeEditText = findViewById(R.id.nomeEditText);
        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        emailEditText = findViewById(R.id.emailEditText);
        manterSwitch = findViewById(R.id.manterSwitch);
        criarButton = findViewById(R.id.alterarButton);

        criarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeEditText.getText().toString();
                String login = loginEditText.getText().toString();
                String senha = senhaEditText.getText().toString();
                String email = emailEditText.getText().toString();
                boolean manterLogado = manterSwitch.isChecked();

                SharedPreferences salvaUser =
                        getSharedPreferences("usuarioPadrao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = salvaUser.edit();

                editor.putString("nome", nome);
                editor.putString("login", login);
                editor.putString("senha", senha);
                editor.putString("email", email);
                editor.putBoolean("manterLogado", manterLogado);
                editor.commit();

                Intent intent =
                        new Intent(NovoActivity.this, PickContactsActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
}