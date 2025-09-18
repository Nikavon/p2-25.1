package wepayu.pagamento;

public class PagamentoBanco implements MetodoPagamento {
    private String banco;
    private String agencia;
    private String contaCorrente;

    public PagamentoBanco(String banco, String agencia, String contaCorrente) {
        this.banco = banco;
        this.agencia = agencia;
        this.contaCorrente = contaCorrente;
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Depositando R$ " + valor + " no banco " + banco +
                           ", agência " + agencia + ", conta " + contaCorrente);
    }
}
