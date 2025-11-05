public class Consulta implements Agendavel {
    private String data;
    private String hora;
    private Proprietario proprietario;
    private Animal animal;

    public Consulta(String data, String hora, Proprietario proprietario, Animal animal) {
        this.data = data;
        this.hora = hora;
        this.proprietario = proprietario;
        this.animal = animal;
    }
    @Override
    public void agendar(String data, String hora){
        this.data = data;
        this.hora = hora;
        System.out.println("Consulta agendada para dia" + data + "ás " + hora);
    }
    @Override
    public void cancelar(){
        setData(null);
        setHora(null);
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
}
