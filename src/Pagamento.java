public class Pagamento implements Pagavel {
    private Proprietario proprietario;
    private double valor;
    private String descricao;

    public Pagamento(Proprietario proprietario, double valor, String descricao) {
        this.proprietario = proprietario;
        this.valor = valor;
        this.descricao = descricao;
    }

    @Override
    public double desconto() {
        // VIPs têm 10% de desconto
        if (proprietario.getVip()) {
            return valor * 0.10;
        }
        return 0.0;
    }

    @Override
    public void Pagamento(double valor) {
        System.out.println("Pagamento efetuado: R$ " + valor);
    }

    public double getValorComDesconto() {
        return valor - desconto();
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
}
