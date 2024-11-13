package devandroid.cesar.elitetintas.telaatendente;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import devandroid.cesar.elitetintas.R;

public class AtendenteAdapter extends RecyclerView.Adapter<AtendenteAdapter.AtendenteViewHolder> {
    private List<Atendente> atendenteList;

    public AtendenteAdapter(List<Atendente> atendenteList) {
        this.atendenteList = atendenteList;
    }

    @NonNull
    @Override
    public AtendenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atendente, parent, false);
        return new AtendenteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtendenteViewHolder holder, int position) {
        Atendente atendente = atendenteList.get(position);
        holder.nomeTextView.setText(atendente.getNome());
        holder.cargoTextView.setText(atendente.getCargo());
        holder.contatoTextView.setText(atendente.getContato());
        holder.fotoImageView.setImageResource(atendente.getFotoResId());

        // Configura o botão WhatsApp para abrir o aplicativo com o número de contato
        holder.whatsappButton.setOnClickListener(v -> {
            String phoneNumber = atendente.getContato().replace("Contato: ", "").trim();
            openWhatsApp(phoneNumber, v);
        });

        // Configura o botão Agendar para abrir o DatePickerDialog
        holder.agendarButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    v.getContext(),
                    (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        Toast.makeText(v.getContext(), "Data agendada: " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }

    private void openWhatsApp(String phoneNumber, View view) {
        try {
            String url = "https://wa.me/" + phoneNumber;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setPackage("com.whatsapp");

            if (intent.resolveActivity(view.getContext().getPackageManager()) != null) {
                view.getContext().startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "WhatsApp não está instalado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(view.getContext(), "Erro ao abrir o WhatsApp", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return atendenteList.size();
    }

    static class AtendenteViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView, cargoTextView, contatoTextView;
        ImageView fotoImageView;
        Button agendarButton, whatsappButton;

        public AtendenteViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewNomeAtendente);
            cargoTextView = itemView.findViewById(R.id.textViewCargoAtendente);
            contatoTextView = itemView.findViewById(R.id.textViewContatoAtendente);
            fotoImageView = itemView.findViewById(R.id.imageViewFotoAtendente);
            agendarButton = itemView.findViewById(R.id.buttonAgendarAtendimento);
            whatsappButton = itemView.findViewById(R.id.buttonWhatsApp);
        }
    }
}
