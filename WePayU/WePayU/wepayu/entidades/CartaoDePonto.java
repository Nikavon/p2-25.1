package wepayu.entidades;

import java.time.LocalDate;

public class CartaoDePonto {
    private LocalDate data;
    private int horas;

    public CartaoDePonto(LocalDate data, int horas) {
        this.data = data;
        this.horas = horas;
    }

    public LocalDate getData() { return data; }
    public int getHoras() { return horas; }
}
