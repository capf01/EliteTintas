package devandroid.cesar.elitetintas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ClienteFormActivity extends AppCompatActivity {

    private EditText tipoTintaField, areaAplicacaoField, nivelResistenciaField, prazoField,
            orcamentoField, revestimentoField, tamanhoAreaField, acabamentoField,
            frequenciaManutencaoField, desafiosAmbientaisField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_form);

        // Inicializar campos
        tipoTintaField = findViewById(R.id.tipoTintaField);
        areaAplicacaoField = findViewById(R.id.areaAplicacaoField);
        nivelResistenciaField = findViewById(R.id.nivelResistenciaField);
        prazoField = findViewById(R.id.prazoField);
        orcamentoField = findViewById(R.id.orcamentoField);
        revestimentoField = findViewById(R.id.revestimentoField);
        tamanhoAreaField = findViewById(R.id.tamanhoAreaField);
        acabamentoField = findViewById(R.id.acabamentoField);
        frequenciaManutencaoField = findViewById(R.id.frequenciaManutencaoField);
        desafiosAmbientaisField = findViewById(R.id.desafiosAmbientaisField);
        submitButton = findViewById(R.id.submitButton);

        // Configurar evento de clique do botão
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Coleta de dados do formulário
                String tipoTinta = tipoTintaField.getText().toString();
                String areaAplicacao = areaAplicacaoField.getText().toString();
                String nivelResistencia = nivelResistenciaField.getText().toString();
                String prazo = prazoField.getText().toString();
                String orcamento = orcamentoField.getText().toString();
                String revestimento = revestimentoField.getText().toString();
                String tamanhoArea = tamanhoAreaField.getText().toString();
                String acabamento = acabamentoField.getText().toString();
                String frequenciaManutencao = frequenciaManutencaoField.getText().toString();
                String desafiosAmbientais = desafiosAmbientaisField.getText().toString();

                // Direcionar para a Activity de atendentes (TelaAtendente)
                Intent intent = new Intent(ClienteFormActivity.this, telaAtendente.class);

                // Enviar dados através do Intent (se necessário)
                intent.putExtra("tipoTinta", tipoTinta);
                intent.putExtra("areaAplicacao", areaAplicacao);
                intent.putExtra("nivelResistencia", nivelResistencia);
                intent.putExtra("prazo", prazo);
                intent.putExtra("orcamento", orcamento);
                intent.putExtra("revestimento", revestimento);
                intent.putExtra("tamanhoArea", tamanhoArea);
                intent.putExtra("acabamento", acabamento);
                intent.putExtra("frequenciaManutencao", frequenciaManutencao);
                intent.putExtra("desafiosAmbientais", desafiosAmbientais);

                startActivity(intent);
            }
        });
    }
}
