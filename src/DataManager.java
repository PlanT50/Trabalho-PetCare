import java.io.*;
import java.util.ArrayList;

public class DataManager {

    private static final String CAMINHO = "data/dados.txt";

    public static void salvar(ArrayList<Proprietario> proprietarios, ArrayList<Animal> animais) {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdir();

            BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO));

            for (Proprietario p : proprietarios) {
                writer.write("PROPRIETARIO;" +
                        p.getNome() + ";" +
                        p.getContato() + ";" +
                        p.getAssinatura() + ";" +
                        p.isVip());
                writer.newLine();
            }


            for (Animal a : animais) {
                writer.write("ANIMAL;" +
                        a.getNome() + ";" +
                        a.getEspecie() + ";" +
                        a.getIdade() + ";" +
                        a.getProprietario().getNome());
                writer.newLine();
            }

            writer.close();
            System.out.println(" Dados salvos no arquivo TXT!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void carregar(ArrayList<Proprietario> proprietarios, ArrayList<Animal> animais) {
        File arquivo = new File(CAMINHO);
        if (!arquivo.exists()) {
            System.out.println(" Nenhum arquivo de dados encontrado.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes[0].equals("PROPRIETARIO")) {
                    String nome = partes[1];
                    String contato = partes[2];
                    String assinatura = partes[3];
                    boolean vip = Boolean.parseBoolean(partes[4]);

                    Proprietario p = new Proprietario(nome, contato, assinatura, vip);
                    proprietarios.add(p);
                }

                else if (partes[0].equals("ANIMAL")) {
                    String nome = partes[1];
                    String especie = partes[2];
                    int idade = Integer.parseInt(partes[3]);
                    String nomeProp = partes[4];

                    Proprietario prop = proprietarios.stream()
                            .filter(p -> p.getNome().equals(nomeProp))
                            .findFirst()
                            .orElse(null);

                    if (prop != null) {
                        Animal a = new Animal(nome, especie, idade, prop);
                        animais.add(a);
                        prop.addAnimal(a);
                    }
                }
            }

            System.out.println(" Dados carregados do arquivo TXT!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

