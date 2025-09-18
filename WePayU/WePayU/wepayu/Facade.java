package wepayu;

import wepayu.empregado.*;
import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.atomic.AtomicInteger;

public class Facade {
    private Map<String, Empregado> empregados = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);
    private static final DecimalFormat df;

    static {
        DecimalFormatSymbols s = new DecimalFormatSymbols(new Locale("pt", "BR"));
        s.setDecimalSeparator(',');
        df = new DecimalFormat("0.00", s);
    }

    public void zerarSistema() {
        empregados.clear();
        nextId.set(1);
    }

    public String criarEmpregado(String nome, String endereco, String tipo, String salario) {
//        throw new RuntimeException("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if (tipo.equals("comissionado")) {
            throw new RuntimeException("Tipo nao aplicavel.");   
        }
        return criarEmpregado(nome, endereco, tipo, salario, null);
    }

    public String criarEmpregado(String nome, String endereco, String tipo, String salario, String comissao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome nao pode ser nulo.");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new RuntimeException("Endereco nao pode ser nulo.");
        }
        if (tipo == null) {
            throw new RuntimeException("Tipo invalido.");
        }
        tipo = tipo.trim().toLowerCase();
        if (!tipo.equals("horista") && !tipo.equals("assalariado") && !tipo.equals("comissionado")) {
            throw new RuntimeException("Tipo invalido.");
        }
        if (salario == null || salario.trim().isEmpty()) {
            throw new RuntimeException("Salario nao pode ser nulo.");
        }
        double salarioValor;
        try {
            salarioValor = parseNumber(salario);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Salario deve ser numerico.");
        }
        if (salarioValor < 0) {
            throw new RuntimeException("Salario deve ser nao-negativo.");
        }

        if (tipo.equals("comissionado")) {
            if (comissao == null || comissao.trim().isEmpty()) {
                throw new RuntimeException("Comissao nao pode ser nula.");
            }
            double comissaoValor;
            try {
                comissaoValor = parseNumber(comissao);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Comissao deve ser numerica.");
            }
            if (comissaoValor < 0) {
                throw new RuntimeException("Comissao deve ser nao-negativa.");
            }
            // criar comissionado
            String id = String.valueOf(nextId.getAndIncrement());
            Empregado emp = new Comissionado(id, nome, endereco, salarioValor, comissaoValor);
            empregados.put(id, emp);
            return id;
        } else {
            // se comissao foi passada para tipos que não aceitam -> erro
            if (comissao != null && !comissao.trim().isEmpty()) {
                throw new RuntimeException("Tipo nao aplicavel.");
            }
            String id = String.valueOf(nextId.getAndIncrement());
            Empregado emp;
            if (tipo.equals("horista")) {
                emp = new Horista(id, nome, endereco, salarioValor);
            } else { // assalariado
                emp = new Assalariado(id, nome, endereco, salarioValor);
            }
            empregados.put(id, emp);
            return id;
        }
    }

    public String getAtributoEmpregado(String empId, String atributo) {
        if (empId == null || empId.trim().isEmpty()) {
            throw new RuntimeException("Identificacao do empregado nao pode ser nula.");
        }
        Empregado e = empregados.get(empId);
        if (e == null) {
            throw new RuntimeException("Empregado nao existe.");
        }
        if (atributo == null || atributo.trim().isEmpty()) {
            throw new RuntimeException("Atributo nao existe.");
        }
        atributo = atributo.trim().toLowerCase();
        switch (atributo) {
            case "nome":
                return e.getNome();
            case "endereco":
                return e.getEndereco();
            case "tipo":
                return e.getTipo();
            case "salario":
                return df.format(e.getSalario());
            case "comissao":
                if (e instanceof Comissionado) {
                    return df.format(((Comissionado)e).getComissao());
                } else {
                    throw new RuntimeException("Atributo nao existe.");
                }
            case "sindicalizado":
                return Boolean.toString(e.isSindicalizado());
            default:
                throw new RuntimeException("Atributo nao existe.");
        }
    }

    public String getEmpregadoPorNome(String nome, String indice) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome nao pode ser nulo.");
        }
        if (indice == null || indice.trim().isEmpty()) {
            throw new RuntimeException("Indice nao pode ser nulo.");
        }
        int idx;
        try {
            idx = Integer.parseInt(indice);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Indice deve ser numerico.");
        }
        if (idx < 0) {
            throw new RuntimeException("Indice deve ser nao-negativo.");
        }
        List<Empregado> encontrados = new ArrayList<>();
        for (Empregado e : empregados.values()) {
            if (e.getNome().equals(nome)) {
                encontrados.add(e);
            }
        }
        if (idx >= encontrados.size()) {
            throw new RuntimeException("Nao ha empregado com esse nome.");
        }
        return encontrados.get(idx -1).getId();
    }

    public void removerEmpregado(String empId) {
        if (empId == null || empId.trim().isEmpty()) {
            throw new RuntimeException("Identificacao do empregado nao pode ser nula.");
        }
        Empregado e = empregados.remove(empId);
        if (e == null) {
            throw new RuntimeException("Empregado nao existe.");
        }
    }

    // public String getHorasExtrasTrabalhadas(String empId) {
    //     if (empId == null || empId.trim().isEmpty()) {
    //         throw new RuntimeException("Identificacao do empregado nao pode ser nula.");
    //     }
    //     Empregado e = empregados.get(empId);
    //     if (e == null) {
    //         throw new RuntimeException("Empregado nao existe.");
    //     }
    //     if (!(e instanceof Horista)) {
    //         throw new RuntimeException("Empregado nao eh horista.");
    //     }
    //     Horista h = (Horista)e;
    //     return df.format(h.getHorasExtrasTrabalhadas());
    // }

    // public void lancaCartao(String emp, String data, String horas){
    //     if (emp == null || emp.trim().isEmpty()) {
    //         throw new RuntimeException("Identificacao do empregado nao pode ser nula.");
    //     }
    //     Empregado e = empregados.get(emp);
    //     if (e == null) {
    //         throw new RuntimeException("Empregado nao existe.");
    //     }
    //     if (!(e instanceof Horista)) {
    //         throw new RuntimeException("Empregado nao eh horista.");
    //     }
    //     if (data == null || data.trim().isEmpty()) {
    //         throw new RuntimeException("Data nao pode ser nula.");
    //     }
    //     data = data.trim();
    //     // checar formato da data
    //     if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
    //         throw new RuntimeException("Data invalida.");
    //     }
    //     if (horas == null || horas.trim().isEmpty()) {
    //         throw new RuntimeException("Horas nao pode ser nula.");
    //     }
    //     double horasValor;
    //     try {
    //         horasValor = parseNumber(horas);
    //     } catch (NumberFormatException ex) {
    //         throw new RuntimeException("Horas deve ser numerico.");
    //     }
    //     if (horasValor < 0) {
    //         throw new RuntimeException("Horas devem ser positivas.");
    //     }
    //     Horista h = (Horista)e;
    //     h.lancaCartao(data, horasValor);

    // }



    public void encerrarSistema() {
    }

    public void quit() {
    }

    // Helpers
    private double parseNumber(String s) {
        s = s.trim();
        // aceitar vírgula como separador decimal
        s = s.replace(".", ""); // remove pontos de milhar, se existirem
        s = s.replace(',', '.');
        return Double.parseDouble(s);
    }
}
