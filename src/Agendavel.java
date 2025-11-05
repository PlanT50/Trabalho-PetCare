public interface Agendavel {
    void agendar(String data, int hora);
    void cancelar();
    void remarcar(String novaData, String novahora);

}
