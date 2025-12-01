import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataManager {

    private static final String ARQ = "dados.txt";

    public static void salvar(ArrayList<Proprietario> proprietarios,
                              ArrayList<Animal> animais,
                              ArrayList<Consulta> consultas,
                              ArrayList<Cirurgia> cirurgias,
                              ArrayList<Exame> exames,
                              ArrayList<Vacina> vacinas,
                              ArrayList<Pagamento> pagamentos) {

        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(ARQ));

            for (Proprietario p : proprietarios) {
                w.write("PROPRIETARIO;" + p.getNome() + ";" + p.getContato() + ";" + p.getAssinatura() + ";" + p.isVip());
                w.newLine();
            }

            for (Animal a : animais) {
                w.write("ANIMAL;" + a.getNome() + ";" + a.getEspecie() + ";" + a.getIdade() + ";" + a.getProprietario().getNome());
                w.newLine();
            }

            for (Consulta c : consultas) {
                w.write("CONSULTA;" + c.getAnimal().getNome() + ";" + c.getData() + ";" + c.getHora() + ";" + c.getVeterinario());
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

        } catch (Exception ignored) {}
    }


    public static void carregar(ArrayList<Proprietario> proprietarios,
                                ArrayList<Animal> animais,
                                ArrayList<Consulta> consultas,
                                ArrayList<Cirurgia> cirurgias,
                                ArrayList<Exame> exames,
                                ArrayList<Vacina> vacinas,
                                ArrayList<Pagamento> pagamentos) {

        try {
            File f = new File(ARQ);
            if (!f.exists()) return;

            BufferedReader r = new BufferedReader(new FileReader(f));
            String linha;

            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";");

                if (p[0].equals("PROPRIETARIO")) {
                    Proprietario pr = new Proprietario(p[1], p[2], p[3], Boolean.parseBoolean(p[4]));
                    proprietarios.add(pr);
                }

                if (p[0].equals("ANIMAL")) {
                    Proprietario dono = proprietarios.stream().filter(x -> x.getNome().equals(p[4])).findFirst().orElse(null);
                    if (dono != null) {
                        Animal a = new Animal(p[1], p[2], Integer.parseInt(p[3]), dono);
                        animais.add(a);
                        dono.addAnimal(a);
                    }
                }

                if (p[0].equals("CONSULTA")) {
                    Animal a = animais.stream().filter(x -> x.getNome().equals(p[1])).findFirst().orElse(null);
                    if (a != null) {
                        Consulta c = new Consulta(p[2], p[3], a, p[4]);
                        consultas.add(c);
                        a.adicionarConsulta(c);
                    }
                }

                if (p[0].equals("CIRURGIA")) {
                    Animal a = animais.stream().filter(x -> x.getNome().equals(p[1])).findFirst().orElse(null);
                    if (a != null) {
                        Cirurgia c = new Cirurgia(p[2], p[3], p[4], a, p[5], Boolean.parseBoolean(p[6]));
                        cirurgias.add(c);
                        a.adicionarCirurgia(c);
                    }
                }

                if (p[0].equals("EXAME")) {
                    Animal a = animais.stream().filter(x -> x.getNome().equals(p[1])).findFirst().orElse(null);
                    if (a != null) {
                        Exame e = new Exame(p[2], p[3], p[4], a);
                        e.setResultado(p[5]);
                        exames.add(e);
                        a.adicionarExame(e);
                    }
                }

                if (p[0].equals("VACINA")) {
                    Animal a = animais.stream().filter(x -> x.getNome().equals(p[1])).findFirst().orElse(null);
                    if (a != null) {
                        LocalDate data = LocalDate.parse(p[3]);
                        LocalDate prox = p[4].equals("null") ? null : LocalDate.parse(p[4]);
                        Vacina v = new Vacina(p[2], data, prox, a, p[5]);
                        vacinas.add(v);
                        a.adicionarVacina(v);
                    }
                }

                if (p[0].equals("PAGAMENTO")) {
                    Proprietario pr = proprietarios.stream().filter(x -> x.getNome().equals(p[1])).findFirst().orElse(null);
                    if (pr != null) {
                        Pagamento pay = new Pagamento(pr, Double.parseDouble(p[3]), p[2]);
                        pay.setFormaPagamento(p[4]);
                        pagamentos.add(pay);
                    }
                }
            }

            r.close();

        } catch (Exception ignored) {}
    }
}
