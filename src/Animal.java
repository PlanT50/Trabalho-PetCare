import java.util.ArrayList;

public class Animal {
    private String nome;
    private String especie;
    private int idade;
    private Proprietario proprietario;
    private ArrayList<EventoMedico> historicoMedico;
    private ArrayList<Consulta> consultas;
    private ArrayList<Cirurgia> cirurgias;
    private ArrayList<Vacina> vacinas;

    public Animal(String nome, String especie, int idade, Proprietario proprietario) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa");
        }
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.proprietario = proprietario;
        this.historicoMedico = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.cirurgias = new ArrayList<>();
        this.vacinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa");
        }
        this.idade = idade;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void adicionarEventoMedico(EventoMedico evento) {
        historicoMedico.add(evento);
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void adicionarCirurgia(Cirurgia cirurgia) {
        cirurgias.add(cirurgia);
    }

    public void adicionarVacina(Vacina vacina) {
        vacinas.add(vacina);
    }

    public ArrayList<EventoMedico> getHistoricoMedico() {
        return historicoMedico;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public ArrayList<Cirurgia> getCirurgias() {
        return cirurgias;
    }

    public ArrayList<Vacina> getVacinas() {
        return vacinas;
    }

    public void exibirHistoricoCompleto() {
        System.out.println("\n===== HISTÓRICO MÉDICO DE " + nome.toUpperCase() + " =====");
        System.out.println("Espécie: " + especie);
        System.out.println("Idade: " + idade + " ano(s)");
        System.out.println("Proprietário: " + proprietario.getNome());

        if (historicoMedico.isEmpty()) {
            System.out.println("\nNenhum evento médico registrado.");
        } else {
            System.out.println("\nEventos Médicos:");
            for (EventoMedico evento : historicoMedico) {
                System.out.println("  " + evento);
            }
        }
    }

    public void exibirConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            System.out.println("Consultas de " + nome + ":");
            for (Consulta consulta : consultas) {
                System.out.println("  Data: " + consulta.getData() + " às " + consulta.getHora());
            }
        }
    }
}