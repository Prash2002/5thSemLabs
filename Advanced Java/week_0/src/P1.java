import java.sql.*;

public class P1 {

    public static void main(String args[]) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newtest", "root", "");
            if (!con.isClosed())
                System.out.println("Successfully connected to MySQL server...");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
    }
}
