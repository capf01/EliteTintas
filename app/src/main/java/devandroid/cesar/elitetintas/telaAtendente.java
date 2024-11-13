package devandroid.cesar.elitetintas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devandroid.cesar.elitetintas.R;
import devandroid.cesar.elitetintas.telaatendente.Atendente;
import devandroid.cesar.elitetintas.telaatendente.AtendenteAdapter;

public class telaAtendente extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AtendenteAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Handler handler;
    private Runnable autoScrollRunnable;
    private int currentPosition = 0;
    private final int SCROLL_DELAY = 3000; // Intervalo de tempo (em ms) entre cada rotação

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atendente);

        // Configuração da RecyclerView com scroll vertical
        recyclerView = findViewById(R.id.recyclerViewAtendentes);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Lista de atendentes
        List<Atendente> atendentes = new ArrayList<>();
        atendentes.add(new Atendente("José Ribamar", "Gerente", "Contato: +55 92 9349-9712", R.drawable.joseribamar));
        atendentes.add(new Atendente("Graciano Serrao", "Atendente", "Contato: +55 92 9199-7110", R.drawable.graciano));
        atendentes.add(new Atendente("Fernanda", "Atendente", "Contato: +55 92 8418-0181", R.drawable.fernanda));
        atendentes.add(new Atendente("Isabel", "Supervisora", "Contato: +55 92 9963-0187", R.drawable.isabel));

        // Configuração do adaptador
        adapter = new AtendenteAdapter(atendentes);
        recyclerView.setAdapter(adapter);

        // Configuração para a rotação automática na direção vertical
        handler = new Handler(Looper.getMainLooper());
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentPosition == adapter.getItemCount() - 1) {
                    currentPosition = 0; // Retorna ao início quando atinge o último item
                } else {
                    currentPosition++;
                }
                recyclerView.smoothScrollToPosition(currentPosition);
                handler.postDelayed(this, SCROLL_DELAY); // Reagendar a execução após o intervalo
            }
        };
        handler.postDelayed(autoScrollRunnable, SCROLL_DELAY); // Inicia o carrossel
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(autoScrollRunnable); // Remove o handler quando a atividade é destruída
    }
}
