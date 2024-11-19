package devandroid.cesar.elitetintas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeField, emailField, enderecoField, telefoneField, senhaField, confirmarSenhaField;
    private Button cadastrarButton;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Inicializar Firebase Auth e Firestore
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Inicializar os campos
        nomeField = findViewById(R.id.nomeField);
        emailField = findViewById(R.id.emailField);
        enderecoField = findViewById(R.id.enderecoField);
        telefoneField = findViewById(R.id.telefoneField);
        senhaField = findViewById(R.id.senhaField);
        confirmarSenhaField = findViewById(R.id.confirmarSenhaField);
        cadastrarButton = findViewById(R.id.cadastrarButton);

        // Configurar o botão de cadastro
        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os dados inseridos
                String nome = nomeField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String endereco = enderecoField.getText().toString().trim();
                String telefone = telefoneField.getText().toString().trim();
                String senha = senhaField.getText().toString().trim();
                String confirmarSenha = confirmarSenhaField.getText().toString().trim();

                // Validar os dados
                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(email) || TextUtils.isEmpty(endereco) ||
                        TextUtils.isEmpty(telefone) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(confirmarSenha)) {
                    Toast.makeText(CadastroActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar se as senhas são iguais
                if (!senha.equals(confirmarSenha)) {
                    Toast.makeText(CadastroActivity.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Registrar o usuário no Firebase Authentication
                auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Salvar dados adicionais no Firestore
                                    String userId = auth.getCurrentUser().getUid();
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put("nome", nome);
                                    userMap.put("email", email);
                                    userMap.put("endereco", endereco);
                                    userMap.put("telefone", telefone);

                                    db.collection("usuarios").document(userId).set(userMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                                        finish(); // Fecha a Activity após o cadastro
                                                    } else {
                                                        Toast.makeText(CadastroActivity.this, "Erro ao salvar dados do usuário.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}