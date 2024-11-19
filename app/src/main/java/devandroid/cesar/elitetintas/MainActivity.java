package devandroid.cesar.elitetintas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailField, senhaField;
    private Button loginButton, registerButton;
    private TextView forgotPasswordLink;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Inicializar Firebase Authentication
            auth = FirebaseAuth.getInstance();

            // Inicializar os campos
            emailField = findViewById(R.id.usernameField);
            senhaField = findViewById(R.id.passwordField);
            loginButton = findViewById(R.id.loginButton);
            registerButton = findViewById(R.id.registerButton);
            forgotPasswordLink = findViewById(R.id.forgotPasswordLink);

            // Verificar se os componentes foram inicializados
            Log.d("MainActivity", "usernameField: " + emailField);
            Log.d("MainActivity", "passwordField: " + senhaField);

            // Configurar evento de clique no botão de login
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailField.getText().toString().trim();
                    String senha = senhaField.getText().toString().trim();

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
                        Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    auth.signInWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, ClienteFormActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Erro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });

            // Configurar o botão de cadastro
            registerButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            });

            // Configurar recuperação de senha
            forgotPasswordLink.setOnClickListener(v -> {
                String email = emailField.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Insira seu e-mail para recuperação", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "E-mail de recuperação enviado.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Erro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            });
        } catch (Exception e) {
            Log.e("MainActivity", "Erro na inicialização: " + e.getMessage());
        }
    }
}
