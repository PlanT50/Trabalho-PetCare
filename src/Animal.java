public class Animal{
    String especie;
    int idade;
    String historico;
    String proprietario;

    public Animal(String especie, int idade, String historico, String proprietario){
        this.especie = especie;
        this.idade = idade;
        this.historico = historico;
        this.proprietario = proprietario;
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
    public String getProprietario() {
        return proprietario;
    }

}
