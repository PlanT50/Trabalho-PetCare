public class Exame implements Agendavel {
    private String tipo;
    private String data;
    private String resultado;
    private Animal animal;

    public Exame(String tipo, String data, Animal animal) {
        this.tipo = tipo;
        this.data = data;
        this.animal = animal;
        this.resultado = "Pendente";
    }

    @Override
    public void agendar(String data, String hora) {
        this.data = data;
    }

    @Override
    public void cancelar() {
        this.data = null;
    }

    @Override
    public void remarcar(String novaData, String novaHora) {
        this.data = novaData;
    }

    public String getTipo() {
        return tipo;
    }

    public String getData() {
        return data;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Animal getAnimal() {
        return animal;
    }
}
