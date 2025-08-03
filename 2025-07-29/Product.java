public class Product {
    public String name;
    public Double price;
    public Integer stockQ;
    public Product (String name, Double price, Integer stockQ) {
        this.name = name;
        this.price = price;
        this.stockQ = stockQ;
    }
    public Double total () {
        Double total = this.price * this.stockQ;
        return total;
    }
    public void printUpdate () {
        System.out.println("Update data: " + this.name + ", $ "+ this.price + ", " + this.stockQ + " units, Total: $ " + this.total());
    }
    public void printData () {
        System.out.println("Product data: " + this.name + ", $ "+ this.price + ", " + this.stockQ + " units, Total: $ " + this.total());
    }

}
