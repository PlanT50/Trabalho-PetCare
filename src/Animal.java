public class Animal{
    private String nome;
    private String especie;
    private int idade;
    private String historico;
    private Proprietario proprietario;

    public Animal(String nome,String especie, int idade, String historico, Proprietario proprietario){
        this.nome = nome;
        this.especie = especie;
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
    public String getEspecie() {
        return especie;
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

}
