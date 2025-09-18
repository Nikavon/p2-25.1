package wepayu.pagamento;

public class PagamentoChequeCorreios implements MetodoPagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Enviando cheque de R$ " + valor + " pelos correios");
    }
}
