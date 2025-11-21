import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SistemaNotificacao {

    public void enviarLembreteVacinacao(Proprietario proprietario, Vacina vacina) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        long diasRestantes = vacina.diasParaProximaDose();

        System.out.println("\n========== LEMBRETE DE VACINAÇÃO ==========");
        System.out.println("Para: " + proprietario.getNome());
        System.out.println("Contato: " + proprietario.getContatoFormatado());
        System.out.println("─────────────────────────────────────────────");
        System.out.println("Animal: " + vacina.getAnimal().getNome());
        System.out.println("Vacina: " + vacina.getNome());
        System.out.println("Próxima dose: " + vacina.getProximaDose().format(formatter));

        if (diasRestantes < 0) {
            System.out.println("ATENÇÃO: Vacina ATRASADA em " + Math.abs(diasRestantes) + " dia(s)");
        } else if (diasRestantes == 0) {
            System.out.println("A vacina deve ser aplicada HOJE");
        } else {
            System.out.println("Faltam " + diasRestantes + " dia(s) para a próxima dose");
        }

        System.out.println("\nAgende já sua consulta na PetCare");
        System.out.println("════════════════════════════════════════════\n");
    }

    public void notificarResultadoExame(Exame exame) {
        System.out.println("\n========== RESULTADO DE EXAME DISPONÍVEL ==========");
        System.out.println("Para: " + exame.getAnimal().getProprietario().getNome());
        System.out.println("Contato: " + exame.getAnimal().getProprietario().getContatoFormatado());
        System.out.println("──────────────────────────────────────────────────────");
        System.out.println("Animal: " + exame.getAnimal().getNome());
        System.out.println("Tipo de exame: " + exame.getTipo());
        System.out.println("Data da coleta: " + exame.getData());
        System.out.println("Status: Resultado disponível");
        System.out.println("\nO resultado do exame está disponível para consulta");
        System.out.println("Entre em contato com a PetCare para mais informações");
        System.out.println("═════════════════════════════════════════════════════\n");
    }

    public void verificarVacinasProximas(ArrayList<Animal> animais) {
        System.out.println("\nVerificando vacinas próximas do vencimento...\n");
        boolean encontrouLembretes = false;

        for (Animal animal : animais) {
            for (Vacina vacina : animal.getVacinas()) {
                if (vacina.necessitaLembrete() || vacina.diasParaProximaDose() < 0) {
                    enviarLembreteVacinacao(animal.getProprietario(), vacina);
                    encontrouLembretes = true;
                }
            }
        }

        if (!encontrouLembretes) {
            System.out.println("Nenhuma vacina próxima do vencimento no momento\n");
        }
    }

    public void notificarAgendamento(Agendavel agendamento, Animal animal, String tipo) {
        System.out.println("\n========== CONFIRMAÇÃO DE AGENDAMENTO ==========");
        System.out.println("Tipo: " + tipo);
        System.out.println("Animal: " + animal.getNome());
        System.out.println("Proprietário: " + animal.getProprietario().getNome());
        System.out.println("Data: " + agendamento.getData());
        System.out.println("Hora: " + agendamento.getHora());

        if (animal.getProprietario().isVip()) {
            System.out.println("Atendimento PRIORITÁRIO");
        }

        System.out.println("══════════════════════════════════════════════════\n");
    }
}