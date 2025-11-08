import java.sql.SQLOutput;

public class Consulta implements Agendavel {
    private String data;
    private String hora;
    private Animal animal;

    public Consulta(String data, String hora, Animal animal) {
        this.data = data;
        this.hora = hora;
        this.animal = animal;
    }
    @Override
    public void agendar(String data, String hora) {
        this.data = data;
        this.hora = hora;
        System.out.println("Consulta agendada para " + animal.getEspecie() + " do proprietário " + animal.getProprietario().getNome() + " no dia " +
                data + " às " + hora );
    }
    @Override
    public void cancelar(){
        setData(null);
        setHora(null);
    }
    @Override
    public void remarcar(String novaData, String novahora){
        setData(novaData);
        setHora(novahora);
    }

    public String getData(){
        return data;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getHora(){
        return hora;
    }
    public void setHora(String hora){
        this.hora = hora;
    }
    public void exibirConsulta(Animal animal){
        System.out.println("Data da consulta: " + data);
        System.out.println("Hora da consulta: " + hora);
        System.out.println("Paciente: " + animal.getNome());
    }
    public Animal getAnimal(){
        return animal;
    }

}
