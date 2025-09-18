package wepayu.empregado;

public class Comissionado extends Empregado {
    private double comissao; // percentual (ex.: 0.05 para 5%)

    public Comissionado(String id, String nome, String endereco, double salarioMensal, double comissao) {
        super(id, nome, endereco, salarioMensal);
        this.comissao = comissao;
    }

    public double getComissao() {
        return comissao;
    }

    @Override
    public String getTipo() {
        return "comissionado";
    }
}
