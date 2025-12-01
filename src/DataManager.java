import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataManager {

    private static final String ARQUIVO = "data/dados.txt";

    public static void salvar(ArrayList<Proprietario> proprietarios,
                              ArrayList<Animal> animais,
                              ArrayList<Consulta> consultas,
                              ArrayList<Cirurgia> cirurgias,
                              ArrayList<Exame> exames,
                              ArrayList<Vacina> vacinas,
                              ArrayList<Pagamento> pagamentos) {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdir();

            BufferedWriter w = new BufferedWriter(new FileWriter(ARQUIVO));

            for (Proprietario p : proprietarios) {
                w.write("PROPRIETARIO;" + p.getNome() + ";" + p.getContato() + ";" +
                        p.getAssinatura() + ";" + p.isVip());
                w.newLine();
            }

            for (Animal a : animais) {
                w.write("ANIMAL;" + a.getNome() + ";" + a.getEspecie() + ";" +
                        a.getIdade() + ";" + a.getProprietario().getNome());
                w.newLine();
            }

            for (Consulta c : consultas) {
                w.write("CONSULTA;" + c.getAnimal().getNome() + ";" +
                        c.getData() + ";" + c.getHora() + ";" + c.getVeterinario());
                w.newLine();
            }
            for (Cirurgia c : cirurgias) {
                w.write("CIRURGIA;" + c.getAnimal().getNome() + ";" + c.getTipo() + ";" + c.getData() + ";" + c.getHora() + ";" + c.getVeterinarioResponsavel() + ";" + c.isEmergencia());
                w.newLine();
            }
            for (Exame e : exames) {
                w.write("EXAME;" + e.getAnimal().getNome() + ";" + e.getTipo() + ";" + e.getData() + ";" + e.getHora() + ";" + e.getResultado());
                w.newLine();
            }
            for (Vacina v : vacinas) {
                w.write("VACINA;" + v.getAnimal().getNome() + ";" + v.getNome() + ";" + v.getDataAplicacao() + ";" + v.getProximaDose() + ";" + v.getLote());
                w.newLine();
            }
            for (Pagamento p : pagamentos) {
                w.write("PAGAMENTO;" + p.getProprietario().getNome() + ";" + p.getDescricao() + ";" + p.getValor() + ";" + p.getFormaPagamento());
                w.newLine();
            }

            w.close();
            System.out.println("Dados salvos!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar");
        }
    }

    public static void carregar(ArrayList<Proprietario> proprietarios,
                                ArrayList<Animal> animais,
                                ArrayList<Consulta> consultas) {
        try {
            File f = new File(ARQUIVO);
            if (!f.exists()) return;

            BufferedReader r = new BufferedReader(new FileReader(f));
            String linha;

            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";");

                if (p[0].equals("PROPRIETARIO")) {
                    Proprietario prop = new Proprietario(p[1], p[2], p[3], Boolean.parseBoolean(p[4]));
                    proprietarios.add(prop);
                }

                if (p[0].equals("ANIMAL")) {
                    Proprietario dono = proprietarios.stream()
                            .filter(x -> x.getNome().equals(p[4]))
                            .findFirst().orElse(null);

                    if (dono != null) {
                        Animal a = new Animal(p[1], p[2], Integer.parseInt(p[3]), dono);
                        animais.add(a);
                        dono.addAnimal(a);
                    }
                }

                if (p[0].equals("CONSULTA")) {
                    Animal a = animais.stream()
                            .filter(x -> x.getNome().equals(p[1]))
                            .findFirst().orElse(null);

                    if (a != null) {
                        Consulta c = new Consulta(p[2], p[3], a, p[4]);
                        consultas.add(c);
                        a.adicionarConsulta(c);
                    }
                }
            }

            r.close();
            System.out.println("Dados carregados!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar");
        }
    }
}
