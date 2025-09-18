package wepayu.pagamento;

public class PagamentoChequeMao implements MetodoPagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Entregando cheque de R$ " + valor + " em mãos");
    }
}
