# JDBC - Java Database Connectivity

JDBC stands for Java Database Connectivity. JDBC is a Java API to connect and execute the query with the database. It is a part of JavaSE (Java Standard Edition). JDBC API uses JDBC drivers to connect with the database.

There are four types of JDBC drivers:

- JDBC-ODBC Bridge Driver,
- JDBC-Native API,
- JDBC-Net pure Java, and
- 100% pure Java

## Connection:

```java
Connection con = null;
try {
    Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newtest", "root", "");
    if (!con.isClosed())
        System.out.println("Successfully connected to MySQL server...");
}
catch (Exception e) {
    System.err.println("Exception: " + e.getMessage());
}
```

## Create Statements

```java
Statement st = null;
ResultSet rs = null;
String qry = "select * from stud";
try {
    st = con.createStatement();
    rs = st.executeQuery(qry);
}
catch (SQLException e) {
    System.out.println("Error while processing SQL query");
}
```

## Get ResultSet

```java
String querystud = "select * from stud";
ResultSet rs = Stmt.executeQuery(querystud);
while (rs.next()) {
    String usn = rs.getString("USN");
    String name = rs.getString("NAME");
    int marks = rs.getInt("MARKS");
}
```

## Statements in JDBC:

- <b> Statement: </b>
  Used to implement simple SQL statements with no
  parameters.
- <b> PreparedStatement: (Extends Statement) </b>
  Used for precompiling SQL statements that might contain
  input parameters. These input parameters will be given a
  value in the runtime

- <b> CallableStatement: (Extends PreparedStatement) </b> Used to execute stored procedures that may contain both
  input and output parameters.

## Prepared Statements

- The PreparedStatement is used to compile the query first before executing it.
- All parameters (arguments) are represented by the ? symbol, which is known as the place holder.
- You must supply values for every parameter before executing the SQL statement.
- The setXXX() methods bind values to the parameters, where XXX represents the Java data type of the value.
- Each parameter marker is referred to by its ordinal position. The first marker represents position 1, the next position 2, and so forth

### Execute Commands:

- boolean execute() - Executes the SQL statement in this PreparedStatement object, which may be any kind of SQL statement.

- ResultSet executeQuery() - This method is used to execute SQL DQL statements, i.e <i> Select </i>. It returns an object of the class ResultSet.

- int executeUpdate() - This is used to execute SQL DML statements, i.e <i> Insert, Update, Delete </i>. It returns an integer value representing the number of rows affected.

### Code:

```java
    PreparedStatement pstmt = con.prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?");
    pstmt.setBigDecimal(1, 153833.00);
    pstmt.setInt(2, 110592);
    pstmt.execute();
```

## CallableStatement

CallableStatement interface is used to call the stored procedures and functions.
