public class Consulta implements Agendavel {
    private String data;
    private String hora;
    private Animal animal;
    private String veterinario;
    private String prontuario;
    private boolean prioritaria;

    public Consulta(String data, String hora, Animal animal, String veterinario) {
        this.data = data;
        this.hora = hora;
        this.animal = animal;
        this.veterinario = veterinario;
        this.prontuario = "";
        this.prioritaria = animal.getProprietario().isVip();
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

    public Animal getAnimal() {
        return animal;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public boolean isPrioritaria() {
        return prioritaria;
    }

    public void exibirDetalhes() {
        System.out.println("\n===== DETALHES DA CONSULTA =====");
        System.out.println("Paciente: " + animal.getNome());
        System.out.println("Proprietário: " + animal.getProprietario().getNome());
        System.out.println("Data: " + data);
        System.out.println("Hora: " + hora);
        System.out.println("Veterinário: " + veterinario);
        System.out.println("Prioritária: " + (prioritaria ? "SIM (VIP)" : "NÃO"));
        if (!prontuario.isEmpty()) {
            System.out.println("Prontuário: " + prontuario);
        }
    }
}