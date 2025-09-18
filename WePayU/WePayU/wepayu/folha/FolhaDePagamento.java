package wepayu.folha;

import wepayu.empregado.Empregado;
import java.util.List;

public class FolhaDePagamento {
    public double processar(List<Empregado> empregados) {
        double total = 0;
        for (Empregado e : empregados) {
            double valor = e.calcularPagamento();
            if (e.getSindicato() != null) {
                valor -= e.getSindicato().calcularDeducoes();
            }
            e.getMetodoPagamento().pagar(valor);
            total += valor;
        }
        return total;
    }
}