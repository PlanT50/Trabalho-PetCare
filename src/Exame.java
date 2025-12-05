public class Exame implements Agendavel {
    private String tipo;
    private String data;
    private String hora;
    private String resultado;
    private Animal animal;
    private boolean resultadoDisponivel;

    public Exame(String tipo, String data, String hora, Animal animal) {
        this.tipo = tipo;
        this.data = data;
        this.hora = hora;
        this.animal = animal;
        this.resultado = "Pendente";
        this.resultadoDisponivel = false;
    }

    @Override
    public void agendar(String data, String hora) {
        this.data = data;
        this.hora = hora;
    }

    @Override
    public void cancelar() {
        this.data = null;
        this.hora = null;
    }

    @Override
    public void remarcar(String novaData, String novaHora) {
        this.data = novaData;
        this.hora = novaHora;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
        this.resultadoDisponivel = true;
    }

    public Animal getAnimal() {
        return animal;
    }

    public boolean isResultadoDisponivel() {
        return resultadoDisponivel;
    }

    public void exibirDetalhes() {
        System.out.println("\n===== DETALHES DO EXAME =====");
        System.out.println("Tipo: " + tipo);
        System.out.println("Paciente: " + animal.getNome());
        System.out.println("Proprietário: " + animal.getProprietario().getNome());
        System.out.println("Data: " + data);
        System.out.println("Hora: " + hora);
        System.out.println("Resultado: " + resultado);
        System.out.println("Status: " + (resultadoDisponivel ? " Disponível" : " Aguardando"));
    }
}