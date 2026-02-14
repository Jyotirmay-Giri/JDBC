import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {

    private static final String URL ="jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        // Here we are using DriverManager which helps to establish intraction/connection with DB
        // Here we are using mySql db

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to Database.........!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*

    // Above implementation is quite bit optmized and try catch block only opening and closing the connection
    Connection conn = null;
    try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connected to Database.........!");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                System.out.println("Disconnected to Database.......!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

     */
}
