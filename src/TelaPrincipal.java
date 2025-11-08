import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame {

    private ArrayList<Proprietario> proprietarios = new ArrayList<>();
    private ArrayList<Animal> animais = new ArrayList<>();
    private ArrayList<Consulta> consultas = new ArrayList<>();
    private ArrayList<Vacina> vacinas = new ArrayList<>();
    private ArrayList<Exame> exames = new ArrayList<>();
    private ArrayList<Pagamento> pagamentos = new ArrayList<>();

    // Componentes compartilhados
    private JTextArea txtListaProprietarios, txtListaAnimais, txtListaConsultas, txtListaVacinas, txtListaExames, txtListaPagamentos;
    private JComboBox<String> comboProprietarios, comboAnimais, comboAnimaisVacina, comboAnimaisExame, comboProprietariosPagamento;

    public TelaPrincipal() {
        setTitle("PetCare - Sistema de Clínica Veterinária");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.add("Proprietários", criarPainelProprietarios());
        abas.add("Animais", criarPainelAnimais());
        abas.add("Consultas", criarPainelConsultas());
        abas.add("Vacinação", criarPainelVacinas());
        abas.add("Exames", criarPainelExames());
        abas.add("Pagamentos", criarPainelPagamentos());

        add(abas);
    }

    // ==== ABA PROPRIETÁRIOS ====
    private JPanel criarPainelProprietarios() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField txtNome = new JTextField();
        JTextField txtContato = new JTextField();
        JTextField txtAssinatura = new JTextField();
        JCheckBox chkVip = new JCheckBox("Cliente VIP");
        JButton btnCadastrar = new JButton("Cadastrar Proprietário");

        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("Contato:"));
        form.add(txtContato);
        form.add(new JLabel("Assinatura:"));
        form.add(txtAssinatura);
        form.add(new JLabel("VIP:"));
        form.add(chkVip);
        form.add(btnCadastrar);

        txtListaProprietarios = new JTextArea();
        txtListaProprietarios.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListaProprietarios);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                int contato = Integer.parseInt(txtContato.getText());
                String assinatura = txtAssinatura.getText();
                boolean vip = chkVip.isSelected();

                Proprietario p = new Proprietario(nome, contato, assinatura, vip);
                proprietarios.add(p);
                atualizarProprietarios();

                txtNome.setText("");
                txtContato.setText("");
                txtAssinatura.setText("");
                chkVip.setSelected(false);

                JOptionPane.showMessageDialog(this, "Proprietário cadastrado!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        return painel;
    }

    // ==== ABA ANIMAIS ====
    private JPanel criarPainelAnimais() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
        JTextField txtNome = new JTextField();
        JTextField txtEspecie = new JTextField();
        JTextField txtIdade = new JTextField();
        JTextField txtHistorico = new JTextField();
        comboProprietarios = new JComboBox<>();
        JButton btnCadastrar = new JButton("Cadastrar Animal");

        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("Espécie:"));
        form.add(txtEspecie);
        form.add(new JLabel("Idade:"));
        form.add(txtIdade);
        form.add(new JLabel("Histórico:"));
        form.add(txtHistorico);
        form.add(new JLabel("Proprietário:"));
        form.add(comboProprietarios);
        form.add(btnCadastrar);

        txtListaAnimais = new JTextArea();
        txtListaAnimais.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListaAnimais);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String especie = txtEspecie.getText();
                int idade = Integer.parseInt(txtIdade.getText());
                String historico = txtHistorico.getText();
                int indiceProp = comboProprietarios.getSelectedIndex();

                if (indiceProp >= 0) {
                    Proprietario p = proprietarios.get(indiceProp);
                    Animal a = new Animal(nome, especie, idade, historico, p);
                    animais.add(a);
                    p.addAnimal(a);
                    atualizarAnimais();
                    txtNome.setText("");
                    txtEspecie.setText("");
                    txtIdade.setText("");
                    txtHistorico.setText("");
                    JOptionPane.showMessageDialog(this, "Animal cadastrado!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        return painel;
    }

    // ==== ABA CONSULTAS ====
    private JPanel criarPainelConsultas() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField txtData = new JTextField();
        JTextField txtHora = new JTextField();
        comboAnimais = new JComboBox<>();
        JButton btnAgendar = new JButton("Agendar Consulta");

        form.add(new JLabel("Data:"));
        form.add(txtData);
        form.add(new JLabel("Hora:"));
        form.add(txtHora);
        form.add(new JLabel("Animal:"));
        form.add(comboAnimais);
        form.add(btnAgendar);

        txtListaConsultas = new JTextArea();
        txtListaConsultas.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListaConsultas);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        btnAgendar.addActionListener(e -> {
            try {
                String data = txtData.getText();
                String hora = txtHora.getText();
                int indiceAnimal = comboAnimais.getSelectedIndex();

                if (indiceAnimal >= 0) {
                    Animal a = animais.get(indiceAnimal);
                    Consulta c = new Consulta(data, hora, a);
                    consultas.add(c);
                    atualizarConsultas();
                    JOptionPane.showMessageDialog(this, "Consulta agendada!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        return painel;
    }

    // ==== ABA VACINAÇÃO ====
    private JPanel criarPainelVacinas() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField txtTipo = new JTextField();
        JTextField txtData = new JTextField();
        comboAnimaisVacina = new JComboBox<>();
        JButton btnRegistrar = new JButton("Registrar Vacina");

        form.add(new JLabel("Tipo da Vacina:"));
        form.add(txtTipo);
        form.add(new JLabel("Data:"));
        form.add(txtData);
        form.add(new JLabel("Animal:"));
        form.add(comboAnimaisVacina);
        form.add(btnRegistrar);

        txtListaVacinas = new JTextArea();
        txtListaVacinas.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListaVacinas);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            try {
                String tipo = txtTipo.getText();
                String data = txtData.getText();
                int indiceAnimal = comboAnimaisVacina.getSelectedIndex();

                if (indiceAnimal >= 0) {
                    Animal a = animais.get(indiceAnimal);
                    Vacina v = new Vacina(tipo, data, a);
                    vacinas.add(v);
                    atualizarVacinas();
                    JOptionPane.showMessageDialog(this, "Vacina registrada!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        return painel;
    }

    // ==== ABA EXAMES ====
    private JPanel criarPainelExames() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField txtTipo = new JTextField();
        JTextField txtData = new JTextField();
        JTextField txtResultado = new JTextField();
        comboAnimaisExame = new JComboBox<>();
        JButton btnRegistrar = new JButton("Registrar Exame");

        form.add(new JLabel("Tipo de Exame:"));
        form.add(txtTipo);
        form.add(new JLabel("Data:"));
        form.add(txtData);
        form.add(new JLabel("Resultado:"));
        form.add(txtResultado);
        form.add(new JLabel("Animal:"));
        form.add(comboAnimaisExame);
        form.add(btnRegistrar);

        txtListaExames = new JTextArea();
        txtListaExames.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListaExames);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            try {
                String tipo = txtTipo.getText();
                String data = txtData.getText();
                String resultado = txtResultado.getText();
                int indiceAnimal = comboAnimaisExame.getSelectedIndex();

                if (indiceAnimal >= 0) {
                    Animal a = animais.get(indiceAnimal);
                    Exame ex = new Exame(tipo, data, a);
                    ex.setResultado(resultado);
                    exames.add(ex);
                    atualizarExames();
                    JOptionPane.showMessageDialog(this, "Exame registrado!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        return painel;
    }

    // ==== ABA PAGAMENTOS ====
    private JPanel criarPainelPagamentos() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        comboProprietariosPagamento = new JComboBox<>();
        JTextField txtValor = new JTextField();
        JTextField txtDescricao = new JTextField();
        JButton btnPagar = new JButton("Registrar Pagamento");

        form.add(new JLabel("Proprietário:"));
        form.add(comboProprietariosPagamento);
        form.add(new JLabel("Descrição do Serviço:"));
        form.add(txtDescricao);
        form.add(new JLabel("Valor (R$):"));
        form.add(txtValor);
        form.add(btnPagar);

        txtListaPagamentos = new JTextArea();
        txtListaPagamentos.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtListaPagamentos);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        btnPagar.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(txtValor.getText());
                String descricao = txtDescricao.getText();
                int indiceProp = comboProprietariosPagamento.getSelectedIndex();

                if (indiceProp >= 0) {
                    Proprietario p = proprietarios.get(indiceProp);
                    Pagamento pg = new Pagamento(p, valor, descricao);
                    pagamentos.add(pg);
                    atualizarPagamentos();
                    JOptionPane.showMessageDialog(this,
                            "Pagamento registrado!\nValor original: R$ " + valor +
                                    "\nDesconto VIP: R$ " + pg.desconto() +
                                    "\nValor final: R$ " + pg.getValorComDesconto());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        return painel;
    }

    // ==== MÉTODOS DE ATUALIZAÇÃO ====
    private void atualizarProprietarios() {
        txtListaProprietarios.setText("");
        comboProprietarios.removeAllItems();
        comboProprietariosPagamento.removeAllItems();
        for (Proprietario p : proprietarios) {
            txtListaProprietarios.append(p.getNome() + " - VIP: " + (p.getVip() ? "Sim" : "Não") + "\n");
            comboProprietarios.addItem(p.getNome());
            comboProprietariosPagamento.addItem(p.getNome());
        }
    }

    private void atualizarAnimais() {
        txtListaAnimais.setText("");
        comboAnimais.removeAllItems();
        comboAnimaisVacina.removeAllItems();
        comboAnimaisExame.removeAllItems();
        for (Animal a : animais) {
            txtListaAnimais.append(a.getNome() + " (" + a.getEspecie() + ") - Dono: " + a.getProprietario().getNome() + "\n");
            comboAnimais.addItem(a.getNome());
            comboAnimaisVacina.addItem(a.getNome());
            comboAnimaisExame.addItem(a.getNome());
        }
    }

    private void atualizarConsultas() {
        txtListaConsultas.setText("");
        for (Consulta c : consultas) {
            txtListaConsultas.append(c.getAnimal().getNome() + " - " + c.getData() + " às " + c.getHora() + "\n");
        }
    }

    private void atualizarVacinas() {
        txtListaVacinas.setText("");
        for (Vacina v : vacinas) {
            txtListaVacinas.append(v.getAnimal().getNome() + " - " + v.getTipo() + " em " + v.getData() + "\n");
        }
    }

    private void atualizarExames() {
        txtListaExames.setText("");
        for (Exame e : exames) {
            txtListaExames.append(e.getAnimal().getNome() + " - " + e.getTipo() + " (" + e.getResultado() + ")\n");
        }
    }

    private void atualizarPagamentos() {
        txtListaPagamentos.setText("");
        for (Pagamento p : pagamentos) {
            txtListaPagamentos.append(p.getProprietario().getNome() + " - " + p.getDescricao() +
                    " | Valor: R$ " + p.getValorComDesconto() +
                    (p.getProprietario().getVip() ? " (com desconto VIP)\n" : "\n"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
