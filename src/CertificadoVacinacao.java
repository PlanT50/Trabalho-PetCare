import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CertificadoVacinacao {
    private Animal animal;
    private LocalDate dataEmissao;

    public CertificadoVacinacao(Animal animal) {
        this.animal = animal;
        this.dataEmissao = LocalDate.now();
    }

    public void gerarCertificado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<Vacina> vacinas = animal.getVacinas();

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          CERTIFICADO DE VACINAÇÃO - PETCARE               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\nData de Emissão: " + dataEmissao.format(formatter));
        System.out.println("\n--- DADOS DO ANIMAL ---");
        System.out.println("Nome: " + animal.getNome());
        System.out.println("Espécie: " + animal.getEspecie());
        System.out.println("Idade: " + animal.getIdade() + " ano(s)");
        System.out.println("\n--- DADOS DO PROPRIETÁRIO ---");
        System.out.println("Nome: " + animal.getProprietario().getNome());
        System.out.println("Contato: " + animal.getProprietario().getContatoFormatado());

        System.out.println("\n--- VACINAS APLICADAS ---");
        if (vacinas.isEmpty()) {
            System.out.println("Nenhuma vacina registrada.");
        } else {
            System.out.println("┌────────────────────┬──────────────┬──────────────┬──────────┐");
            System.out.println("│ Vacina             │ Aplicação    │ Próxima Dose │ Status   │");
            System.out.println("├────────────────────┼──────────────┼──────────────┼──────────┤");

            for (Vacina vacina : vacinas) {
                String proximaDose = vacina.getProximaDose() != null
                        ? vacina.getProximaDose().format(formatter)
                        : "N/A";

                System.out.printf("│ %-18s │ %-12s │ %-12s │ %-8s │%n",
                        truncate(vacina.getNome(), 18),
                        vacina.getDataAplicacao().format(formatter),
                        proximaDose,
                        vacina.getStatus());
            }
            System.out.println("└────────────────────┴──────────────┴──────────────┴──────────┘");
        }
    }

    private String truncate(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }
}