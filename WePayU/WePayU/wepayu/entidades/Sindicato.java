package wepayu.entidades;

import java.util.ArrayList;
import java.util.List;

public class Sindicato {
    private String idSindicato;
    private double taxaMensal;
    private List<TaxaServico> taxas;

    public Sindicato(String idSindicato, double taxaMensal) {
        this.idSindicato = idSindicato;
        this.taxaMensal = taxaMensal;
        this.taxas = new ArrayList<>();
    }

    public void adicionarTaxa(TaxaServico taxa) {
        taxas.add(taxa);
    }

    public double calcularDeducoes() {
        double total = taxaMensal;
        for (TaxaServico t : taxas) {
            total += t.getValor();
        }
        taxas.clear();
        return total;
    }
}
