import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        int assinatura = 0;
        ArrayList<Proprietario> proprietarios = new ArrayList<>();
        ArrayList<Animal> animais = new ArrayList<>();


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
                    System.out.println("Cadastro Animal");
                    System.out.println("Digite seu nome:");

                    String nomeAnimal = scan.nextLine();

                    System.out.println("Qual a idade do animal");

                    String idadeAnimal = scan.nextLine();

                    System.out.println("Qual o histórico de Saúde do animal:");

                    String historicoAnimal = scan.nextLine();

                    System.out.println("Qual é o proprietário: ");

                    for(int i=0; i < proprietarios.size(); i++){
                        System.out.println( i + "-" + proprietarios.get(i).getNome());
                    }

                    int indice = scan.nextInt();
                    scan.nextLine();

                    Proprietario escolhido = proprietarios.get(indice);

                    Animal m = new Animal(nomeAnimal, idadeAnimal, historicoAnimal, escolhido);
                    break;

                case 3:
                    System.out.println("Agendar Consultas");
                    break;

                case 4:
                    System.out.println("Verificar Consultas");
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

            }


        }
    }
}
