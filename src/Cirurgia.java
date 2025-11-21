public class Cirurgia implements Agendavel {
    private String tipo;
    private String data;
    private String hora;
    private Animal animal;
    private String veterinarioResponsavel;
    private boolean emergencia;
    private String observacoes;

    public Cirurgia(String tipo, String data, String hora, Animal animal, String veterinario, boolean emergencia) {
        this.tipo = tipo;
        this.data = data;
        this.hora = hora;
        this.animal = animal;
        this.veterinarioResponsavel = veterinario;
        this.emergencia = emergencia;
        this.observacoes = "";
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

    public Animal getAnimal() {
        return animal;
    }

    public String getVeterinarioResponsavel() {
        return veterinarioResponsavel;
    }

    public void setVeterinarioResponsavel(String veterinarioResponsavel) {
        this.veterinarioResponsavel = veterinarioResponsavel;
    }

    public boolean isEmergencia() {
        return emergencia;
    }

    public void setEmergencia(boolean emergencia) {
        this.emergencia = emergencia;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void exibirDetalhes() {
        System.out.println("\n===== DETALHES DA CIRURGIA =====");
        System.out.println("Tipo: " + tipo);
        System.out.println("Paciente: " + animal.getNome());
        System.out.println("Proprietário: " + animal.getProprietario().getNome());
        System.out.println("Data: " + data);
        System.out.println("Hora: " + hora);
        System.out.println("Veterinário: " + veterinarioResponsavel);
        System.out.println("Emergência: " + (emergencia ? "SIM" : "NÃO"));
        if (!observacoes.isEmpty()) {
            System.out.println("Observações: " + observacoes);
        }
    }
}