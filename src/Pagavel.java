public interface Pagavel {
    double calcularDesconto();
    void efetuarPagamento(double valor);
    double getValorFinal();
}