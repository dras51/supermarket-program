package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Creating a connection to the SQL database
public class DBHandler {

    String connectonUrl = "jdbc:mysql://localhost:3306/shop";
    String user = "root";
    String password = "asdfg12345";

    //make connection variable static because only one connection is needed in the whole program.
    private static Connection connection;

    public DBHandler() {
        try{
            connection = DriverManager.getConnection(connectonUrl, user, password);
            System.out.println("Connection to database successful");
        }catch (SQLException e) {
            System.out.println("Cannot connect to the database!!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
