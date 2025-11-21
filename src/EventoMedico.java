import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventoMedico {
    private LocalDate data;
    private String tipo;
    private String descricao;
    private String veterinario;

    public EventoMedico(LocalDate data, String tipo, String descricao, String veterinario) {
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.veterinario = veterinario;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getVeterinario() {
        return veterinario;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[%s] %s - %s (Dr. %s)",
                data.format(formatter), tipo, descricao, veterinario);
    }
}