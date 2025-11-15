import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        int assinatura = 0;


        while (opcao != 5) {
            System.out.println("========== PET CARE ==========");
            System.out.println("1 - Cadastrar Proprietário");
            System.out.println("2 - Cadastrar Animal");
            System.out.println("3 - Agendar Consultas");
            System.out.println("4 - Verificar Consultas");
            System.out.println("5 - Sair");

            opcao = scan.nextInt();
            assinatura = scan.nextInt();

            scan.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastro Proprietário");
                    System.out.println("Digite seu nome:");

                    String nome = scan.nextLine();

                    System.out.println("Digite seu Contato: ");
                    int contato = scan.nextInt();

                    System.out.println("Qual plano deseja: ");


                    Proprietario p = new Proprietario(nome,contato,);
                    break;

                case 2:
                    System.out.println("Cadastro Animal");
                    System.out.println("Digite seu nome:");

                    String nomeAnimal = scan.nextLine();

                    System.out.println("Qual a idade do animal");

                    String idadeAnimal = scan.nextLine();

                    System.out.println("Qual o histórico de Saúde do animal:");

                    String historicoAnimal = scan.nextLine();



                    Animal m = new Animal(nomeAnimal, idadeAnimal, historicoAnimal,)
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
