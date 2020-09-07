package br.com.fivecontacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.fivecontacts.R;
import br.com.fivecontacts.models.User;

public class AlterarUsuarioActivity extends AppCompatActivity {

    EditText nomeEditText;
    EditText loginEditText;
    EditText senhaEditText;
    EditText emailEditText;
    SwitchCompat manterSwitch;
    Button alterarButton;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_usuario);

        nomeEditText = findViewById(R.id.nomeEditText);
        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        emailEditText = findViewById(R.id.emailEditText);
        manterSwitch = findViewById(R.id.manterSwitch);
        alterarButton = findViewById(R.id.alterarButton);

        Intent quemChamou = this.getIntent();
        if (quemChamou!= null){
            Bundle params = quemChamou.getExtras();
            if (params!=null){
                user = (User) params.getSerializable("usuario");
                if (user!=null){
                    nomeEditText.setText(user.getNome());
                    loginEditText.setText(user.getLogin());
                    senhaEditText.setText(user.getSenha());
                    emailEditText.setText(user.getEmail());
                    manterSwitch.setChecked(false);
                }
            }
        }


        final SharedPreferences alteraUsuario =
                getSharedPreferences("usuarioPadrao", Activity.MODE_PRIVATE);
        nomeEditText.setText(alteraUsuario.getString("nome", ""));
        loginEditText.setText(alteraUsuario.getString("login", ""));
        senhaEditText.setText(alteraUsuario.getString("senha", ""));
        emailEditText.setText(alteraUsuario.getString("email", ""));
        manterSwitch.setChecked(alteraUsuario.getBoolean("manterLogado", false));

        alterarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeEditText.getText().toString();
                String login = loginEditText.getText().toString();
                String senha =  senhaEditText.getText().toString();
                String email = emailEditText.getText().toString();
                boolean manterLogado = manterSwitch.isChecked();

                SharedPreferences.Editor editor = alteraUsuario.edit();
                editor.putString("nome", nome);
                editor.putString("login", login);
                editor.putString("senha", senha);
                editor.putString("email", email);
                editor.putBoolean("manterLogado", manterLogado);
                editor.commit();

                Intent intent =
                        new Intent(AlterarUsuarioActivity.this,
                               ListaDeContatos_ListViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}