import java.sql.*;

public class P2 {
    public static void main(String[] args) {
        System.out.println("MySQL Connect Example.");
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "newtest";
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

            while (rs.next()) {
                f1 = rs.getString(1);
                f2 = rs.getString(2);
                System.out.println(f1 + "  " + f2);

            } // end while
            conn.close();
            System.out.println("Disconnected from database");
        } // end try

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
