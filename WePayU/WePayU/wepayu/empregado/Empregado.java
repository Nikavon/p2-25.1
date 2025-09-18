package wepayu.empregado;

public abstract class Empregado {
    protected String id;
    protected String nome;
    protected String endereco;
    protected double salario;
    protected boolean sindicalizado = false;
    protected String idSindicato = null;

    public Empregado(String id, String nome, String endereco, double salario) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public double getSalario() { return salario; }
    public boolean isSindicalizado() { return sindicalizado; }
    public String getIdSindicato() { return idSindicato; }

    public abstract String getTipo();
}
