import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ArrayList<Proprietario> proprietarios = new ArrayList<>();
    private static ArrayList<Animal> animais = new ArrayList<>();
    private static ArrayList<Consulta> consultas = new ArrayList<>();
    private static ArrayList<Cirurgia> cirurgias = new ArrayList<>();
    private static ArrayList<Exame> exames = new ArrayList<>();
    private static ArrayList<Vacina> vacinas = new ArrayList<>();
    private static ArrayList<Pagamento> pagamentos = new ArrayList<>();
    private static SistemaNotificacao notificacao = new SistemaNotificacao();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        DataManager.carregar(proprietarios, animais, consultas, cirurgias, exames, vacinas, pagamentos);

        int opcao = 0;

        while (opcao != 15) {
            exibirMenuPrincipal();

            try {
                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1: cadastrarProprietario(); break;
                    case 2: cadastrarAnimal(); break;
                    case 3: agendarConsulta(); break;
                    case 4: agendarCirurgia(); break;
                    case 5: agendarExame(); break;
                    case 6: registrarVacina(); break;
                    case 7: emitirCertificadoVacinacao(); break;
                    case 8: verificarLembretesVacinacao(); break;
                    case 9: registrarResultadoExame(); break;
                    case 10: verificarExames(); break;
                    case 11: verificarConsultas(); break;
                    case 12: verificarCirurgias(); break;
                    case 13: exibirHistoricoAnimal(); break;
                    case 14: efetuarPagamento(); break;
                    case 15: editarProprietario(); break;
                    case 16: editarAnimal(); break;
                    case 17: excluirProprietario(); break;
                    case 18: excluirAnimal(); break;
                    case 0:
                        DataManager.salvar(proprietarios, animais, consultas, cirurgias, exames, vacinas, pagamentos);
                        System.out.println("Sistema encerrado");
                        System.out.println("\n Sistema PetCare encerrado"); break;
                    default: System.out.println("\n Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n Entrada inválida. Digite apenas números");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("\n Erro: " + e.getMessage());
            }
        }
        scan.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         SISTEMA PETCARE - MENU             ║");
        System.out.println("╚════════════════════════════════════════════╝");

        System.out.println("  CADASTROS");
        System.out.println("  1  - Cadastrar Proprietário");
        System.out.println("  2  - Cadastrar Animal");

        System.out.println("\n  AGENDAMENTOS");
        System.out.println("  3  - Agendar Consulta");
        System.out.println("  4  - Agendar Cirurgia");
        System.out.println("  5  - Agendar Exame Laboratorial");

        System.out.println("\n  VACINAÇÃO");
        System.out.println("  6  - Registrar Vacina");
        System.out.println("  7  - Emitir Certificado de Vacinação");
        System.out.println("  8  - Verificar Lembretes de Vacinação");

        System.out.println("\n  EXAMES E RESULTADOS");
        System.out.println("  9  - Registrar Resultado de Exame");
        System.out.println("  10 - Ver Exames Agendados");

        System.out.println("\n  CONSULTAS");
        System.out.println("  11 - Ver Consultas Agendadas");
        System.out.println("  12 - Ver Cirurgias Agendadas");
        System.out.println("  13 - Ver Histórico Completo de Animal");

        System.out.println("\n  PAGAMENTOS");
        System.out.println("  14 - Efetuar Pagamento");

        System.out.println("\n  EDIÇÃO / EXCLUSÃO");
        System.out.println("  15 - Editar Proprietário");
        System.out.println("  16 - Editar Animal");
        System.out.println("  17 - Excluir Proprietário");
        System.out.println("  18 - Excluir Animal");

        System.out.println("\n  0 - Sair");
        System.out.println("════════════════════════════════════════════");
        System.out.print("Escolha uma opção: ");
    }


    private static void cadastrarProprietario() {
        System.out.println("\n===== CADASTRO DE PROPRIETÁRIO =====");
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        String contato;
        while (true) {
            System.out.print("Contato (apenas números, 10-11 dígitos): ");
            contato = scan.nextLine();
            if (contato.matches("\\d{10,11}")) {
                break;
            }
            System.out.println(" Contato inválido.");
        }

        System.out.println("\nPlanos disponíveis:");
        System.out.println("1 - Core");
        System.out.println("2 - Essential");
        System.out.println("3 - Premium");
        System.out.println("4 - Ultimate");
        System.out.print("Escolha o plano: ");
        int opcPlano = scan.nextInt();
        scan.nextLine();

        String assinatura = switch (opcPlano) {
            case 1 -> "Core";
            case 2 -> "Essential";
            case 3 -> "Premium";
            case 4 -> "Ultimate";
            default -> "Core";
        };

        System.out.print("\nDeseja ser VIP? (S/N): ");
        boolean vip = scan.nextLine().toUpperCase().equals("S");

        Proprietario p = new Proprietario(nome, contato, assinatura, vip);
        proprietarios.add(p);

        System.out.println("\n Proprietário cadastrado com sucesso");
        if (vip) {
            System.out.println(" Status VIP ativado: 10% de desconto + atendimento prioritário");
        }
    }

    private static void cadastrarAnimal() {
        if (proprietarios.isEmpty()) {
            System.out.println("\n Nenhum proprietário cadastrado. Cadastre um proprietário primeiro.");
            return;
        }

        System.out.println("\n===== CADASTRO DE ANIMAL =====");
        System.out.print("Nome do animal: ");
        String nome = scan.nextLine();

        System.out.print("Espécie (ex: Cão, Gato, Coelho): ");
        String especie = scan.nextLine();

        int idade;
        while (true) {
            try {
                System.out.print("Idade (anos): ");
                idade = scan.nextInt();
                scan.nextLine();
                if (idade >= 0) break;
                System.out.println(" Idade não pode ser negativa");
            } catch (InputMismatchException e) {
                System.out.println(" Digite um número válido");
                scan.nextLine();
            }
        }

        System.out.println("\nProprietários disponíveis:");
        for (int i = 0; i < proprietarios.size(); i++) {
            System.out.println(i + " - " + proprietarios.get(i).getNome() +
                    " (" + proprietarios.get(i).getAssinatura() +
                    (proprietarios.get(i).isVip() ? " - VIP)" : ")"));
        }

        System.out.print("Escolha o proprietário: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= proprietarios.size()) {
            System.out.println(" Proprietário inválido");
            return;
        }

        Proprietario escolhido = proprietarios.get(indice);
        Animal animal = new Animal(nome, especie, idade, escolhido);
        escolhido.addAnimal(animal);
        animais.add(animal);

        animal.adicionarEventoMedico(new EventoMedico(
                LocalDate.now(),
                "Cadastro",
                "Animal cadastrado no sistema",
                "Sistema"
        ));

        System.out.println("\n Animal cadastrado com sucesso");
    }

    private static void agendarConsulta() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        System.out.println("\n===== AGENDAR CONSULTA =====");
        Animal animal = selecionarAnimal();
        if (animal == null) return;

        System.out.print("Data (dd/MM/yyyy): ");
        String data = scan.nextLine();

        String hora;
        if (animal.getProprietario().isVip()) {
            System.out.println(" Horários prioritários VIP disponíveis:");
            System.out.println("08:00, 08:30, 09:00, 09:30");
        }
        System.out.print("Hora (HH:mm): ");
        hora = scan.nextLine();

        System.out.print("Nome do veterinário: ");
        String veterinario = scan.nextLine();

        Consulta consulta = new Consulta(data, hora, animal, veterinario);
        animal.adicionarConsulta(consulta);
        consultas.add(consulta);

        animal.adicionarEventoMedico(new EventoMedico(
                LocalDate.now(),
                "Consulta Agendada",
                "Consulta com Dr. " + veterinario,
                veterinario
        ));

        notificacao.notificarAgendamento(consulta, animal, "CONSULTA");
    }

    private static void agendarCirurgia() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        System.out.println("\n===== AGENDAR CIRURGIA =====");
        Animal animal = selecionarAnimal();
        if (animal == null) return;

        System.out.print("Tipo de cirurgia: ");
        String tipo = scan.nextLine();

        System.out.print("Data (dd/MM/yyyy): ");
        String data = scan.nextLine();

        System.out.print("Hora (HH:mm): ");
        String hora = scan.nextLine();

        System.out.print("Veterinário responsável: ");
        String veterinario = scan.nextLine();

        System.out.print("É emergência? (S/N): ");
        boolean emergencia = scan.nextLine().toUpperCase().equals("S");

        Cirurgia cirurgia = new Cirurgia(tipo, data, hora, animal, veterinario, emergencia);
        animal.adicionarCirurgia(cirurgia);
        cirurgias.add(cirurgia);

        animal.adicionarEventoMedico(new EventoMedico(
                LocalDate.now(),
                "Cirurgia Agendada",
                tipo + (emergencia ? " (EMERGÊNCIA)" : ""),
                veterinario
        ));

        notificacao.notificarAgendamento(cirurgia, animal, "CIRURGIA");
    }

    private static void agendarExame() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        System.out.println("\n===== AGENDAR EXAME LABORATORIAL =====");
        Animal animal = selecionarAnimal();
        if (animal == null) return;

        System.out.println("Tipos de exames disponíveis:");
        System.out.println("1 - Hemograma Completo");
        System.out.println("2 - Bioquímica");
        System.out.println("3 - Ultrassom");
        System.out.println("4 - Raio-X");
        System.out.println("5 - Outro");
        System.out.print("Escolha o tipo: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        String tipo = switch (opcao) {
            case 1 -> "Hemograma Completo";
            case 2 -> "Bioquímica";
            case 3 -> "Ultrassom";
            case 4 -> "Raio-X";
            default -> {
                System.out.print("Digite o tipo do exame: ");
                yield scan.nextLine();
            }
        };

        System.out.print("Data (dd/MM/yyyy): ");
        String data = scan.nextLine();

        System.out.print("Hora (HH:mm): ");
        String hora = scan.nextLine();

        Exame exame = new Exame(tipo, data, hora, animal);
        exames.add(exame);

        animal.adicionarEventoMedico(new EventoMedico(
                LocalDate.now(),
                "Exame Agendado",
                tipo,
                "Laboratório"
        ));

        notificacao.notificarAgendamento(exame, animal, "EXAME");
    }

    private static void registrarVacina() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        System.out.println("\n===== REGISTRAR VACINA =====");
        Animal animal = selecionarAnimal();
        if (animal == null) return;

        System.out.println("Vacinas comuns:");
        System.out.println("1 - V8/V10 (Múltipla)");
        System.out.println("2 - Antirrábica");
        System.out.println("3 - Giárdia");
        System.out.println("4 - Leishmaniose");
        System.out.println("5 - Outra");
        System.out.print("Escolha: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        String nomeVacina = switch (opcao) {
            case 1 -> "V8/V10";
            case 2 -> "Antirrábica";
            case 3 -> "Giárdia";
            case 4 -> "Leishmaniose";
            default -> {
                System.out.print("Nome da vacina: ");
                yield scan.nextLine();
            }
        };

        LocalDate dataAplicacao = lerData("Data de aplicação (dd/MM/yyyy): ");
        if (dataAplicacao == null) return;

        System.out.print("Tem próxima dose? (S/N): ");
        LocalDate proximaDose = null;
        if (scan.nextLine().toUpperCase().equals("S")) {
            proximaDose = lerData("Data da próxima dose (dd/MM/yyyy): ");
        }

        System.out.print("Lote da vacina: ");
        String lote = scan.nextLine();

        Vacina vacina = new Vacina(nomeVacina, dataAplicacao, proximaDose, animal, lote);
        animal.adicionarVacina(vacina);

        animal.adicionarEventoMedico(new EventoMedico(
                dataAplicacao,
                "Vacinação",
                nomeVacina + " - Lote: " + lote,
                "Veterinário"
        ));

        System.out.println("\n Vacina registrada com sucesso");
    }

    private static void registrarResultadoExame() {
        if (exames.isEmpty()) {
            System.out.println("\n Nenhum exame cadastrado");
            return;
        }

        System.out.println("\n===== REGISTRAR RESULTADO DE EXAME =====");
        System.out.println("Exames pendentes:");

        ArrayList<Exame> examesPendentes = new ArrayList<>();
        for (int i = 0; i < exames.size(); i++) {
            Exame exame = exames.get(i);
            if (!exame.isResultadoDisponivel()) {
                System.out.println(examesPendentes.size() + " - " + exame.getTipo() +
                        " | " + exame.getAnimal().getNome() + " | " + exame.getData());
                examesPendentes.add(exame);
            }
        }

        if (examesPendentes.isEmpty()) {
            System.out.println("Nenhum exame pendente.");
            return;
        }

        System.out.print("\nEscolha o exame: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= examesPendentes.size()) {
            System.out.println(" Exame inválido");
            return;
        }

        Exame exame = examesPendentes.get(indice);
        System.out.print("Resultado do exame: ");
        String resultado = scan.nextLine();
        exame.setResultado(resultado);

        exame.getAnimal().adicionarEventoMedico(new EventoMedico(
                LocalDate.now(),
                "Resultado de Exame",
                exame.getTipo() + ": " + resultado,
                "Laboratório"
        ));

        notificacao.notificarResultadoExame(exame);
    }

    private static void verificarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("\n Nenhuma consulta agendada.");
            return;
        }

        System.out.println("\n===== CONSULTAS AGENDADAS =====");
        for (Consulta c : consultas) {
            System.out.println("\n────────────────────────────────");
            System.out.println("Animal: " + c.getAnimal().getNome());
            System.out.println("Espécie: " + c.getAnimal().getEspecie());
            System.out.println("Proprietário: " + c.getAnimal().getProprietario().getNome());
            System.out.println("Data: " + c.getData() + " às " + c.getHora());
            System.out.println("Veterinário: " + c.getVeterinario());
            System.out.println("Prioritária: " + (c.isPrioritaria() ? "SIM" : "NÃO"));
        }
    }

    private static void verificarCirurgias() {
        if (cirurgias.isEmpty()) {
            System.out.println("\n Nenhuma cirurgia agendada.");
            return;
        }

        System.out.println("\n===== CIRURGIAS AGENDADAS =====");
        for (Cirurgia c : cirurgias) {
            c.exibirDetalhes();
        }
    }

    private static void verificarExames() {
        if (exames.isEmpty()) {
            System.out.println("\n Nenhum exame cadastrado.");
            return;
        }

        System.out.println("\n===== EXAMES LABORATORIAIS =====");
        for (Exame e : exames) {
            e.exibirDetalhes();
        }
    }

    private static void emitirCertificadoVacinacao() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        Animal animal = selecionarAnimal();
        if (animal == null) return;

        CertificadoVacinacao certificado = new CertificadoVacinacao(animal);
        certificado.gerarCertificado();
    }

    private static void verificarLembretesVacinacao() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        notificacao.verificarVacinasProximas(animais);
    }

    private static void efetuarPagamento() {
        if (proprietarios.isEmpty()) {
            System.out.println("\n Nenhum proprietário cadastrado");
            return;
        }

        System.out.println("\n===== EFETUAR PAGAMENTO =====");
        System.out.println("Proprietários:");
        for (int i = 0; i < proprietarios.size(); i++) {
            System.out.println(i + " - " + proprietarios.get(i).getNome() +
                    (proprietarios.get(i).isVip() ? " VIP" : ""));
        }

        System.out.print("Escolha o proprietário: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= proprietarios.size()) {
            System.out.println(" Proprietário inválido");
            return;
        }

        Proprietario proprietario = proprietarios.get(indice);

        System.out.println("\nServiços:");
        System.out.println("1 - Consulta (R$ 150,00)");
        System.out.println("2 - Cirurgia (R$ 800,00)");
        System.out.println("3 - Exame (R$ 200,00)");
        System.out.println("4 - Vacina (R$ 80,00)");
        System.out.println("5 - Valor personalizado");
        System.out.print("Escolha o serviço: ");
        int opcaoServico = scan.nextInt();
        scan.nextLine();

        double valor;
        String descricao;

        switch (opcaoServico) {
            case 1 -> { valor = 150.00; descricao = "Consulta Veterinária"; }
            case 2 -> { valor = 800.00; descricao = "Cirurgia"; }
            case 3 -> { valor = 200.00; descricao = "Exame Laboratorial"; }
            case 4 -> { valor = 80.00; descricao = "Vacinação"; }
            default -> {
                System.out.print("Descrição do serviço: ");
                descricao = scan.nextLine();
                System.out.print("Valor (R$): ");
                valor = scan.nextDouble();
                scan.nextLine();
            }
        }

        Pagamento pagamento = new Pagamento(proprietario, valor, descricao);
        pagamento.exibirResumo();

        System.out.println("\nFormas de pagamento:");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cartão de Débito");
        System.out.println("3 - Cartão de Crédito");
        System.out.println("4 - PIX");
        System.out.print("Escolha: ");
        int formaPg = scan.nextInt();
        scan.nextLine();

        String forma = switch (formaPg) {
            case 1 -> "Dinheiro";
            case 2 -> "Cartão de Débito";
            case 3 -> "Cartão de Crédito";
            case 4 -> "PIX";
            default -> "Não especificado";
        };

        pagamento.setFormaPagamento(forma);
        pagamento.efetuarPagamento(pagamento.getValorFinal());
    }

    private static void exibirHistoricoAnimal() {
        if (animais.isEmpty()) {
            System.out.println("\n Nenhum animal cadastrado");
            return;
        }

        Animal animal = selecionarAnimal();
        if (animal == null) return;

        animal.exibirHistoricoCompleto();
    }

    private static Animal selecionarAnimal() {
        System.out.println("\nAnimais disponíveis:");
        for (int i = 0; i < animais.size(); i++) {
            Animal a = animais.get(i);
            System.out.println(i + " - " + a.getNome() + " (" + a.getEspecie() + ") - " +
                    a.getProprietario().getNome());
        }

        System.out.print("Escolha o animal: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= animais.size()) {
            System.out.println(" Animal inválido");
            return null;
        }

        return animais.get(indice);
    }

    private static LocalDate lerData(String mensagem) {
        System.out.print(mensagem);
        String dataStr = scan.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(" Data inválida. Use o formato fornecido dd/MM/yyyy");
            return null;
        }
    }
    
    //Alterações para REC1

    private static void editarProprietario() {
        if (proprietarios.isEmpty()) {
            System.out.println("\nNenhum proprietário cadastrado");
            return;
        }

        System.out.println("\n===== EDITAR PROPRIETÁRIO =====");
        System.out.println("Proprietários cadastrados:");
        for (int i = 0; i < proprietarios.size(); i++) {
            Proprietario p = proprietarios.get(i);
            System.out.println(i + " - " + p.getNome() + " | " +
                    p.getContatoFormatado() + " | " + p.getAssinatura() +
                    (p.isVip() ? "VIP" : ""));
        }

        System.out.print("\nEscolha o proprietário: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= proprietarios.size()) {
            System.out.println("Proprietário inválido");
            return;
        }

        Proprietario p = proprietarios.get(indice);

        System.out.println("\nO que deseja editar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Contato");
        System.out.println("3 - Plano de Assinatura");
        System.out.println("4 - Status VIP");
        System.out.println("5 - Editar Tudo");
        System.out.print("Escolha: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                p.setNome(scan.nextLine());
                System.out.println("Nome atualizado");
                break;

            case 2:
                String novoContato;
                while (true) {
                    System.out.print("Novo contato (10-11 dígitos): ");
                    novoContato = scan.nextLine();
                    if (novoContato.matches("\\d{10,11}")) {
                        p.setContato(novoContato);
                        break;
                    }
                    System.out.println("Contato inválido");
                }
                System.out.println("Contato atualizado");
                break;

            case 3:
                System.out.println("\nNovo plano:");
                System.out.println("1 - Core | 2 - Essential | 3 - Premium | 4 - Ultimate");
                System.out.print("Escolha: ");
                int opcPlano = scan.nextInt();
                scan.nextLine();

                String novoPlano = switch (opcPlano) {
                    case 1 -> "Core";
                    case 2 -> "Essential";
                    case 3 -> "Premium";
                    case 4 -> "Ultimate";
                    default -> p.getAssinatura();
                };
                p.setAssinatura(novoPlano);
                System.out.println("Plano atualizado");
                break;

            case 4:
                System.out.print("Status VIP (S/N): ");
                p.setVip(scan.nextLine().toUpperCase().equals("S"));
                System.out.println("Status VIP atualizado");
                break;

            case 5:
                System.out.print("Novo nome: ");
                p.setNome(scan.nextLine());

                while (true) {
                    System.out.print("Novo contato (10-11 dígitos): ");
                    String cont = scan.nextLine();
                    if (cont.matches("\\d{10,11}")) {
                        p.setContato(cont);
                        break;
                    }
                    System.out.println("Contato inválido");
                }

                System.out.println("\nNovo plano:");
                System.out.println("1 - Core | 2 - Essential | 3 - Premium | 4 - Ultimate");
                System.out.print("Escolha: ");
                int opc = scan.nextInt();
                scan.nextLine();
                String pl = switch (opc) {
                    case 1 -> "Core";
                    case 2 -> "Essential";
                    case 3 -> "Premium";
                    case 4 -> "Ultimate";
                    default -> p.getAssinatura();
                };
                p.setAssinatura(pl);

                System.out.print("Status VIP (S/N): ");
                p.setVip(scan.nextLine().toUpperCase().equals("S"));

                System.out.println("Todos os dados atualizados");
                break;

            default:
                System.out.println("Opção inválida");
        }
    }

    private static void editarAnimal() {
        if (animais.isEmpty()) {
            System.out.println("\nNenhum animal cadastrado");
            return;
        }

        System.out.println("\n===== EDITAR ANIMAL =====");
        System.out.println("Animais cadastrados:");
        for (int i = 0; i < animais.size(); i++) {
            Animal a = animais.get(i);
            System.out.println(i + " - " + a.getNome() + " | " +
                    a.getEspecie() + " | " + a.getIdade() + " ano(s) | Dono: " +
                    a.getProprietario().getNome());
        }

        System.out.print("\nEscolha o animal: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= animais.size()) {
            System.out.println("Animal inválido");
            return;
        }

        Animal a = animais.get(indice);

        System.out.println("\nO que deseja editar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Espécie");
        System.out.println("3 - Idade");
        System.out.println("4 - Editar Todos");
        System.out.print("Escolha: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                a.setNome(scan.nextLine());
                System.out.println("Nome atualizado");
                break;

            case 2:
                System.out.print("Nova espécie: ");
                a.setEspecie(scan.nextLine());
                System.out.println("Espécie atualizada");
                break;

            case 3:
                try {
                    System.out.print("Nova idade: ");
                    int novaIdade = scan.nextInt();
                    scan.nextLine();
                    a.setIdade(novaIdade);
                    System.out.println("Idade atualizada");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;

            case 4:
                System.out.print("Novo nome: ");
                a.setNome(scan.nextLine());

                System.out.print("Nova espécie: ");
                a.setEspecie(scan.nextLine());

                try {
                    System.out.print("Nova idade: ");
                    int novaIdade = scan.nextInt();
                    scan.nextLine();
                    a.setIdade(novaIdade);
                    System.out.println("Dados atualizados");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro na idade: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Opção inválida");
        }
    }

    private static void excluirProprietario() {
        if (proprietarios.isEmpty()) {
            System.out.println("\nNenhum proprietário cadastrado");
            return;
        }

        System.out.println("\n===== EXCLUIR PROPRIETÁRIO =====");
        System.out.println("Ao excluir um proprietário, todos os seus animais serão juntamente excluídos");
        System.out.println("\nProprietários cadastrados:");

        for (int i = 0; i < proprietarios.size(); i++) {
            Proprietario p = proprietarios.get(i);
            System.out.println(i + " - " + p.getNome() + "Animais: " +
                    p.getAnimais().size() + (p.isVip() ? "VIP" : ""));
        }

        System.out.print("\nEscolha o proprietário: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= proprietarios.size()) {
            System.out.println("Proprietário inválido");
            return;
        }

        Proprietario p = proprietarios.get(indice);

        System.out.println("\nConfirma exclusão de " + p.getNome() + "?");
        System.out.println("Isso irá excluir " + p.getAnimais().size() + " animal(is) associado(s)");
        System.out.print("Digite 'CONFIRMAR' para prosseguir: ");
        String confirmacao = scan.nextLine();

        if (!confirmacao.equals("CONFIRMAR")) {
            System.out.println("Exclusão cancelada");
            return;
        }

        ArrayList<Animal> animaisParaRemover = new ArrayList<>(p.getAnimais());
        for (Animal animal : animaisParaRemover) {

            consultas.removeIf(c -> c.getAnimal().equals(animal));
            cirurgias.removeIf(c -> c.getAnimal().equals(animal));
            exames.removeIf(e -> e.getAnimal().equals(animal));
            animais.remove(animal);
        }

        proprietarios.remove(p);

        System.out.println("\nProprietário e " + animaisParaRemover.size() +
                " animal(is) excluídos com sucesso");
    }

    private static void excluirAnimal() {
        if (animais.isEmpty()) {
            System.out.println("\nNenhum animal cadastrado");
            return;
        }

        System.out.println("\n===== EXCLUIR ANIMAL =====");
        System.out.println("ATENÇÃO: Ao excluir um animal, todo o seu histórico será perdido");
        System.out.println("\nAnimais cadastrados:");

        for (int i = 0; i < animais.size(); i++) {
            Animal a = animais.get(i);
            System.out.println(i + " - " + a.getNome() + " | " +
                    a.getEspecie() + "Dono: " + a.getProprietario().getNome());
        }

        System.out.print("\nEscolha o animal: ");
        int indice = scan.nextInt();
        scan.nextLine();

        if (indice < 0 || indice >= animais.size()) {
            System.out.println("Animal inválido");
            return;
        }

        Animal a = animais.get(indice);

        System.out.println("\nConfirma exclusão de " + a.getNome() + "?");
        System.out.print("Digite 'CONFIRMAR' para prosseguir: ");
        String confirmacao = scan.nextLine();

        if (!confirmacao.equals("CONFIRMAR")) {
            System.out.println("Exclusão cancelada");
            return;
        }

        a.getProprietario().getAnimais().remove(a);

        consultas.removeIf(c -> c.getAnimal().equals(a));
        cirurgias.removeIf(c -> c.getAnimal().equals(a));
        exames.removeIf(e -> e.getAnimal().equals(a));
        animais.remove(a);

        System.out.println("\nAnimal excluído com sucesso");
    }
}
