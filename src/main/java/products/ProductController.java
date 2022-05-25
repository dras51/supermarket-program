package products;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {
    ProductRepository productRepository = new ProductRepository();
    Scanner scanner = new Scanner(System.in);
    Scanner intScanner = new Scanner(System.in);

    public void createTable() {
        try {
            productRepository.createTable();
            System.out.println("Created product table successfully");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Product creation failed");
        }
    }

    public void createProduct() {
        try {
            System.out.println("Add product to supermarket");

            System.out.println("Product Name: ");
            String name = scanner.nextLine();

            System.out.println("Price: ");
            double price = intScanner.nextDouble();

            System.out.println("Quantity: ");
            int quantity = intScanner.nextInt();

            System.out.println("Description: ");
            String description = scanner.nextLine();

            Product product = new Product(name, price, quantity, description);

            productRepository.create(product);
            System.out.println( name + " added to the supermarket!");

        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println( "Unable to add product to supermarket");
        }
    }

    public void listProducts() {
        try{
            System.out.println(productRepository.getAll());
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Cannot retrieve products from database at the moment");
        }
    }

    public void updateProduct() {
        try{
            System.out.println("Update product in supermarket");
            System.out.println("Enter product id to update");
            int id = intScanner.nextInt();

            //Find product from the database with ID provide by User
            Product product = productRepository.findById(id);

            //Read new product name from user and set new name to product
            System.out.println("Name (" + product.name + "): ");
            product.name = scanner.nextLine();

            System.out.println("Price (" + product.price + "): ");
            product.price = intScanner.nextDouble();

            System.out.println("Description (" + product.description + "): ");
            product.description = scanner.nextLine();

            productRepository.update(product);
            System.out.println(product.name + "updated successfully");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Unable to update product");
        }
    }
//    public String createProduct(Product product) {
//        try {
//            //add product to database
//            return "Product added successfully";
//        }catch(SQLException e){
//            e.printStackTrace();
//            return "Error Creating product";
//        }
//    }
}
