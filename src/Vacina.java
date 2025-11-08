public class Vacina implements Agendavel {
    private String tipo;
    private String data;
    private Animal animal;

    public Vacina(String tipo, String data, Animal animal) {
        this.tipo = tipo;
        this.data = data;
        this.animal = animal;
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

    public Animal getAnimal() {
        return animal;
    }
}
