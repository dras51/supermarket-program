import products.Product;
import products.ProductController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the supermarket.");

        String input = "";

        do {
            System.out.println("""
                    What would you like to do?
                    1. Add product to supermarket.
                    2. List products in supermarket
                    3. Update product details in supermarket
                    """);

            input = scanner.nextLine();

            switch (input){
                case "quit":
                    System.out.println("closing application");
                    break;
                case "1":
                    productController.createProduct();
                    break;
                case "2":
                    productController.listProducts();
                    break;
                case "3":
                    productController.updateProduct();
                    break;
                default:
                    System.out.println("Please enter a valid input");
                    break;
            }

        }while (!input.equalsIgnoreCase("quit"));
    }
}
