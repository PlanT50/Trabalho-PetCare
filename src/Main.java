import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Proprietario> proprietarios = new ArrayList<>();
        ArrayList<Animal> animais = new ArrayList<>();
        ArrayList<Consulta> consultas = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("========== PET CARE ==========");
            System.out.println("1 - Cadastrar Proprietário");
            System.out.println("2 - Cadastrar Animal");
            System.out.println("3 - Agendar Consultas");
            System.out.println("4 - Verificar Consultas");
            System.out.println("5 - Sair");

            opcao = scan.nextInt();
            scan.nextLine();


            switch (opcao) {
                case 1:
                    System.out.println("Cadastro Proprietário");
                    System.out.println("Digite seu nome:");
                    String nome = scan.nextLine();

                    System.out.println("Digite seu Contato: ");
                    int contato = scan.nextInt();

                    System.out.println("Qual plano deseja?");
                    System.out.println("1 - Normal");
                    System.out.println("2 - VIP");
                    int opcPlano = scan.nextInt();
                    scan.nextLine();

                    String assinaturaEscolhida;
                    boolean vip;

                    if (opcPlano == 2) {
                        assinaturaEscolhida = "VIP";
                        vip = true;
                    } else {
                        assinaturaEscolhida = "Normal";
                        vip = false;
                    }

                    Proprietario p = new Proprietario(nome, contato, assinaturaEscolhida, vip);
                    proprietarios.add(p);
                    break;

                case 2:
                    if (proprietarios.isEmpty()) {
                        System.out.println("Nenhum proprietário cadastrado! Cadastre um primeiro.");
                        break;
                    }

                    System.out.println("Cadastro Animal");
                    System.out.println("Digite o nome do animal:");
                    String nomeAnimal = scan.nextLine();

                    System.out.println("Qual a idade do animal:");
                    int idadeAnimal = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Qual o histórico de Saúde do animal:");
                    String historicoAnimal = scan.nextLine();

                    System.out.println("Escolha o proprietário:");

                    for (int i = 0; i < proprietarios.size(); i++) {
                        System.out.println(i + " - " + proprietarios.get(i).getNome());
                    }

                    int indice = scan.nextInt();
                    scan.nextLine();

                    if (indice < 0 || indice >= proprietarios.size()) {
                        System.out.println("Proprietário inválido!");
                        break;
                    }

                    Proprietario escolhido = proprietarios.get(indice);

                    Animal m = new Animal(nomeAnimal, idadeAnimal, historicoAnimal, escolhido);
                    escolhido.addAnimal(m);
                    animais.add(m);
                    break;

                case 3:
                    if (animais.isEmpty()) {
                        System.out.println("Nenhum animal cadastrado!");
                        break;
                    }

                    System.out.println("Agendar Consulta");
                    System.out.println("Escolha o animal:");

                    for (int i = 0; i < animais.size(); i++) {
                        System.out.println(i + " - " + animais.get(i).getNome());
                    }

                    int indiceAnimal = scan.nextInt();
                    scan.nextLine();

                    if (indiceAnimal < 0 || indiceAnimal >= animais.size()) {
                        System.out.println("Animal inválido!");
                        break;
                    }

                    Animal animalSelecionado = animais.get(indiceAnimal);

                    System.out.println("Digite a data da consulta (dd/mm):");
                    String dataConsulta = scan.nextLine();

                    System.out.println("Digite a hora da consulta (hh:mm):");
                    String horaConsulta = scan.nextLine();

                    Consulta c = new Consulta(dataConsulta, horaConsulta, animalSelecionado);

                    animalSelecionado.adicionarConsulta(c);
                    consultas.add(c);

                    System.out.println("Consulta agendada com sucesso!");
                    break;

                case 4:
                    if (consultas.isEmpty()) {
                        System.out.println("Nenhuma consulta agendada.");
                        break;
                    }

                    System.out.println("Consultas marcadas:");

                    for (Consulta con : consultas) {
                        System.out.println("---------------------");
                        System.out.println("Animal: " + con.getAnimal().getNome());
                        System.out.println("Proprietário: " + con.getAnimal().getProprietario().getNome());
                        System.out.println("Data: " + con.getData());
                        System.out.println("Hora: " + con.getHora());
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

}
