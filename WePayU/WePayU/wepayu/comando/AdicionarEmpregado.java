package wepayu.comando;

import wepayu.empregado.Empregado;
import java.util.Map;

public class AdicionarEmpregado implements Comando {
    private Map<String, Empregado> empregados;
    private Empregado empregado;

    public AdicionarEmpregado(Map<String, Empregado> empregados, Empregado empregado) {
        this.empregados = empregados;
        this.empregado = empregado;
    }

    @Override
    public void executar() {
        empregados.put(empregado.getId(), empregado);
    }

    @Override
    public void desfazer() {
        empregados.remove(empregado.getId());
    }
}
