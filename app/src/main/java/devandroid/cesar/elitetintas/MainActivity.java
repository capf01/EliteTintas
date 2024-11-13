package devandroid.cesar.elitetintas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Nome do layout que você criou

        // Inicializa os componentes
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // Configura o evento de clique no botão de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                // Validação dos campos
                if (TextUtils.isEmpty(username)) {
                    usernameField.setError("Informe o e-mail");
                } else if (TextUtils.isEmpty(password)) {
                    passwordField.setError("Informe a senha");
                } else {
                    // Validação bem-sucedida: navega para a tela de atendentes
                    Intent intent = new Intent(MainActivity.this, ClienteFormActivity.class);
                    startActivity(intent);
                    finish(); // Fecha a LoginActivity para que o usuário não volte a ela
                }
            }
        });

        // Configura o evento de clique no botão de cadastro (opcional)
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "cadastre", Toast.LENGTH_SHORT).show();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia a CadastrarActivity
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}
