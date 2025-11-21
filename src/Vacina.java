import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Vacina {
    private String nome;
    private LocalDate dataAplicacao;
    private LocalDate proximaDose;
    private Animal animal;
    private String lote;

    public Vacina(String nome, LocalDate dataAplicacao, LocalDate proximaDose, Animal animal, String lote) {
        this.nome = nome;
        this.dataAplicacao = dataAplicacao;
        this.proximaDose = proximaDose;
        this.animal = animal;
        this.lote = lote;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public LocalDate getProximaDose() {
        return proximaDose;
    }

    public void setProximaDose(LocalDate proximaDose) {
        this.proximaDose = proximaDose;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getLote() {
        return lote;
    }

    public boolean necessitaLembrete() {
        if (proximaDose == null) return false;
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), proximaDose);
        return diasRestantes <= 7 && diasRestantes >= 0;
    }

    public long diasParaProximaDose() {
        if (proximaDose == null) return -1;
        return ChronoUnit.DAYS.between(LocalDate.now(), proximaDose);
    }

    public String getStatus() {
        if (proximaDose == null) {
            return "Completo";
        }
        long dias = diasParaProximaDose();
        if (dias < 0) {
            return "Atrasado";
        } else if (dias <= 7) {
            return "Próximo";
        } else {
            return "Em dia";
        }
    }

    public void exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n===== DETALHES DA VACINA =====");
        System.out.println("Nome: " + nome);
        System.out.println("Paciente: " + animal.getNome());
        System.out.println("Data de Aplicação: " + dataAplicacao.format(formatter));
        System.out.println("Lote: " + lote);
        if (proximaDose != null) {
            System.out.println("Próxima Dose: " + proximaDose.format(formatter));
            System.out.println("Status: " + getStatus());
        } else {
            System.out.println("Próxima Dose: Não necessária");
        }
    }
}