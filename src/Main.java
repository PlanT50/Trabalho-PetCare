import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;


        while (opcao != 5) {
            System.out.println("========== PET CARE ==========");
            System.out.println("1 - Cadastrar Proprietário");
            System.out.println("2 - Cadastrar Animal");
            System.out.println("3 - Agendar Consultas");
            System.out.println("4 - Verificar Consultas");
            System.out.println("5 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastro Proprietário");
                    break;

                case 2:
                    System.out.println("Cadastro Animal");
                    break;

                case 3:
                    System.out.println("Agendar Consultas");
                    break;

                case 4:
                    System.out.println("Verificar Consultas");
                    break;

                case 5:
                    System.out.println("Saindo...");

            }


        }
    }
}
