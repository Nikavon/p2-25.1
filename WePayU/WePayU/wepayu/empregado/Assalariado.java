package wepayu.empregado;

public class Assalariado extends Empregado {
    public Assalariado(String id, String nome, String endereco, double salarioMensal) {
        super(id, nome, endereco, salarioMensal);
    }

    @Override
    public String getTipo() {
        return "assalariado";
    }
}
