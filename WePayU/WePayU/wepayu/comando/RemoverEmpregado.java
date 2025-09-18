package wepayu.comando;

import wepayu.empregado.Empregado;
import java.util.Map;

public class RemoverEmpregado implements Comando {
    private Map<String, Empregado> empregados;
    private Empregado removido;

    public RemoverEmpregado(Map<String, Empregado> empregados, Empregado removido) {
        this.empregados = empregados;
        this.removido = removido;
    }

    @Override
    public void executar() {
        empregados.remove(removido.getId());
    }

    @Override
    public void desfazer() {
        empregados.put(removido.getId(), removido);
    }
}
