public class Consulta implements Agendavel {
    private String data;
    private int hora;
    private Proprietario proprietario;
    private Animal animal;

    public Consulta(String data, int hora, Proprietario proprietario, Animal animal) {
        this.data = data;
        this.hora = hora;
        this.proprietario = proprietario;
        this.animal = animal;
    }
    @Override
    public void agendar(String data, int hora){
        this.data = data;
        this.hora = hora;
        System.out.println("Consulta agendada para dia" + data + "ás " + hora);
    }
}
