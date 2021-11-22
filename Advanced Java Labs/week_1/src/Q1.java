import java.sql.*;

public class Q1 {
    public static void main(String[] args) {
        System.out.println("MySQL Connect Example.");
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "week1";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        String f1, f2;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            String query = "Select * FROM stud";
            System.out.println("Connected to the database");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
