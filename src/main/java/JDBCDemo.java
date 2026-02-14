import java.sql.*;

public class JDBCDemo {

    private static final String URL ="jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "****";
    private static final String PASSWORD = "*****";

    public static void main(String[] args) {

        // Here we are using DriverManager which helps to establish intraction/connection with DB
        // Here we are using mySql db

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to Database.........!");
            insertStudent(conn,"Jyoti","jyoti@gmail.com"); // calling for inserting the data
            updateStudent(conn,1,"Shree","shree@gmail.com"); // Updating the table
            deleteStudent(conn,1); // Deleting the row by using id
            selectStudent(conn); // calling for see/print the table
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



    // Here we are inserting data into table , in sql variable we are storing the SQL query
    private static void insertStudent(Connection conn,String name,String email){
        String sql = "INSERT INTO student (name,email) VALUES ('" + name + "','" + email + "')";
        try(Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("INSERTED Row : " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // Here we are printing/viewing the student table
    private static void selectStudent(Connection conn){
        String sql = "SELECT * FROM student";
        try(Statement stmt = conn.createStatement()){
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("Student list : ");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(id + " : " + name + " : " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Used for updating the table
    private static void updateStudent(Connection conn,int id,String name,String email){
        String sql = "UPDATE student SET name = '" + name + "', email = '" + email + "' WHERE id=" + id;
        try(Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("UPDATED : " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Here we are deleting the row
    private static void deleteStudent(Connection conn,int id){
        String sql = "DELETE FROM student WHERE id = " + id;
        try(Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("DELETE :" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
