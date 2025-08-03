import java.util.Scanner;   //para pegar o input do usuario

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Product product;
        product = create(ler);
        product = addStock(product, ler);
        product = removeStock(product, ler);

        ler.close();
    }

    public static Product create(Scanner ler){
        System.out.println("Enter product data:");
        System.out.print("Name: ");  
        String name = ler.nextLine();
        System.out.print("Price: ");
        Double price = Double.valueOf(ler.nextLine());
        System.out.print("Quantity in stock: ");
        Integer stockQ = Integer.valueOf(ler.nextLine());
        Product product = new Product(name, price, stockQ);
        product.printData();  
        return product;
    }

    public static Product addStock(Product product, Scanner ler){
            System.out.println("Enter the number of products to be added in stock: ");
            String added = ler.nextLine();
            product.stockQ = product.stockQ + Integer.valueOf(added);
            product.printUpdate();
            return product;
    }
    public static Product removeStock(Product product, Scanner ler){
            System.out.println("Enter the number of products to be removed from stock: ");
            String removed = ler.nextLine();
            product.stockQ = product.stockQ - Integer.valueOf(removed);
            product.printUpdate();
            return product;

    }
}
