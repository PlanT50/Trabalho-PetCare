import java.util.ArrayList;

public class Animal{
    private String nome;
    private int idade;
    private String historico;
    private Proprietario proprietario;
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public Animal(String nome, int idade, String historico, Proprietario proprietario){
        this.nome = nome;
        this.idade = idade;
        this.historico = historico;
        this.proprietario = proprietario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }
    public String getHistorico(){
        return historico;
    }
    public void setHistorico(String historico){
        this.historico = historico;
    }
    public Proprietario getProprietario() {
        return proprietario;
    }
    public void AdicionarConsulta(Consulta consulta){
        consultas.add(consulta);
    }
    public void ExibirConsulta(){
        if(consultas.isEmpty()){
            System.out.println("Nenhum consulta agendado");
        }
        else {
            System.out.println("Consultas de " + getNome()  + " agendadas: ");
            for(Consulta consulta : consultas){
             System.out.println("Data: " + consulta.getData() + " Hora: " + consulta.getHora());
            }
        }
    }

}
