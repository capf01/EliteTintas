package devandroid.cesar.elitetintas.telaatendente;

public class Atendente {
    private String nome;
    private String cargo;
    private String contato;
    private int fotoResId; // Referência do recurso da imagem

    // Construtor
    public Atendente(String nome, String cargo, String contato, int fotoResId) {
        this.nome = nome;
        this.cargo = cargo;
        this.contato = contato;
        this.fotoResId = fotoResId;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getContato() {
        return contato;
    }

    public int getFotoResId() {
        return fotoResId;
    }
}