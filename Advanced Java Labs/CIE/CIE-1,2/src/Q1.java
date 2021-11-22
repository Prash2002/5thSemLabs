import java.sql.*;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "cie1";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(url + dbName, username, password);
            // Using Prepared Statements
            int id, year, noe;
            String name, head;
            PreparedStatement st = con.prepareStatement("insert into department values(?,?,?,?,?)");
            boolean more = true;
            Scanner sc = new Scanner(System.in);
            while (more) {
                System.out.println("Enter Dept_ID, Name, Year_Established, Head_Name, No_of_Employees.");

                id = sc.nextInt();
                name = sc.next();
                year = sc.nextInt();
                head = sc.next();
                noe = sc.nextInt();

                System.out.println(id + " " + name + " " + year + " " + head + " " + noe);

                st.setInt(1, id);
                st.setString(2, name);
                st.setInt(3, year);
                st.setString(4, head);
                st.setInt(5, noe);

                st.execute();

                System.out.println("Do you want to add more records?(y/n) ");
                String c = sc.next();
                if (c.startsWith("n")) {
                    more = false;
                }

            }

            String q1 = "SELECT noe from department where name=\"CSE\"";
            ResultSet rs = st.executeQuery(q1);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }

            String q2 = "SELECT name, id from department where year = 2010";
            rs = st.executeQuery(q2);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getInt(2));
            }
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
