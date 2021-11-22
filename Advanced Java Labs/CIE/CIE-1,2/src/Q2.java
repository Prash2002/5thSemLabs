import java.sql.*;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String db = "cie1";
        String username = "root";
        String password = "";
        Connection con = null;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(url + db, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO student values (?,?)");
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("Enter name, cgpa");
                String name = sc.next();
                double cgpa = sc.nextFloat();

                st.setString(1, name);
                st.setDouble(2, cgpa);

                st.execute();

                System.out.println("Do you want to add more records? y/n");
                String c = sc.next();
                if (c.startsWith("n")) {
                    break;
                }
            } while (true);

            String q1 = "SELECT * from student where cgpa<9.0";
            ResultSet rs = st.executeQuery(q1);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getDouble(2));
            }

            String q2 = "SELECT * from student";
            Statement st1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            System.out.println("Updated");
            rs = st1.executeQuery(q2);
            while (rs.next()) {
                if (rs.getString(1).equals("john")) {
                    // System.out.println("John!!! ");
                    rs.updateDouble(2, 9.4);
                    rs.updateRow();
                }
                System.out.println(rs.getString(1) + " " + rs.getDouble(2));
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
