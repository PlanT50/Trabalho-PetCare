public class Pagamento implements Pagavel {
    private Proprietario proprietario;
    private double valor;
    private String descricao;
    private String formaPagamento;

    public Pagamento(Proprietario proprietario, double valor, String descricao) {
        this.proprietario = proprietario;
        this.valor = valor;
        this.descricao = descricao;
        this.formaPagamento = "Não definido";
    }

    @Override
    public double calcularDesconto() {
        if (proprietario.isVip()) {
            return valor * 0.10; // 10% de desconto para VIP
        }
        return 0.0;
    }

    @Override
    public void efetuarPagamento(double valor) {
        System.out.println("\nPagamento efetuado com sucesso");
        System.out.println("Valor pago: R$ " + String.format("%.2f", valor));
        System.out.println("Forma de pagamento: " + formaPagamento);
    }

    @Override
    public double getValorFinal() {
        return valor - calcularDesconto();
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void exibirResumo() {
        System.out.println("\n===== RESUMO DO PAGAMENTO =====");
        System.out.println("Cliente: " + proprietario.getNome());
        System.out.println("Status: " + (proprietario.isVip() ? "VIP" : "Regular"));
        System.out.println("Plano: " + proprietario.getAssinatura());
        System.out.println("Serviço: " + descricao);
        System.out.println("Valor original: R$ " + String.format("%.2f", valor));

        if (proprietario.isVip()) {
            System.out.println("Desconto VIP (10%): -R$ " + String.format("%.2f", calcularDesconto()));
        }

        System.out.println("─────────────────────────────────");
        System.out.println("TOTAL A PAGAR: R$ " + String.format("%.2f", getValorFinal()));
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
}