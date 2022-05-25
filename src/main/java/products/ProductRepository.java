package products;

import database.DBHandler;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {
    DBHandler dbHandler = new DBHandler();

    //Create table for product.
    //if a method has the ability to return an error, then either specify the error while creating the method or wrap the code in try catch to handle
    //the error properly

    public void createTable() throws SQLException {
        String query = "CREATE TABLE products(id int(11) PRIMARY KEY AUTO INCREMENT, name varchar(50) NOT NULL, price double(12,2) NOT NULL, quantity int(11) DEFAULT 0, description varchar(120))";

        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public void create(Product product) throws SQLException {
        String query = "INSERT INTO products(name, price, quantity, description) VALUES(?,?,?,?)";

        //Create prepared statement to help execute query
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);

        //Set values for ? place holders
        preparedStatement.setString(1, product.name);
        preparedStatement.setDouble(2, product.price);
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.description);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public ArrayList<Product> getAll() throws SQLException{
        //Specify query to get all columns in the products table
        String query = "SELECT * FROM products";

        //Create statement variable
        Statement statement = dbHandler.getConnection().createStatement();

        //Query the statement with the query string created and store the result in a result set
        ResultSet result = statement.executeQuery(query);

        //Create arraylist to hold the result gotten from the database
        ArrayList<Product> products = new ArrayList<>();

        //Loop through result with result.next() and save each record to the products arraylist
        while(result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            double price = result.getDouble("price");
            int quantity = result.getInt("quantity");
            String description = result.getString("description");

            products.add(new Product(id, name, price, quantity, description));
        }

        //Close statement
        statement.close();

        //return array list of products
        return products;
    }

    public Product findById(int id) throws SQLException{
        String query = "SELECT * FROM products WHERE id=?";

        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);

        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        ResultSet result = preparedStatement.getResultSet();



        if(result.next()){
            int resultId = result.getInt("id");
            String name = result.getString("name");
            double price = result.getDouble("price");
            int quantity = result.getInt("quantity");
            String description = result.getString("description");

            return new Product(resultId, name, price, quantity, description);

        } else {
            return null;
        }
    }

    public void update(Product product) throws SQLException{
        String query = "UPDATE products SET name=?, price=?, quantity=?, description=? WHERE id=?";

        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);

        //Set values for ? placeholders
        preparedStatement.setString(1, product.name);
        preparedStatement.setDouble(2, product.price);
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.description);
        preparedStatement.setInt(5, product.getId());

        preparedStatement.execute();
        preparedStatement.close();

    }
}
