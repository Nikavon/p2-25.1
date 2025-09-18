package wepayu.empregado;

public class Horista extends Empregado {
    public Horista(String id, String nome, String endereco, double salarioHora) {
        super(id, nome, endereco, salarioHora);
    }

    public int getHorasExtrasTrabalhadas () {

        return 0; // placeholder
    }

    public int lancaCartao() {
        return 0; // placeholder
    }

    @Override
    public String getTipo() {
        return "horista";
    }
}
