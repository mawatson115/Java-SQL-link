/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Watson
 */
import java.sql.*;
import java.io.*;           //used for java cin

public class HookUp {

    static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
    static final String DB_URL = "jdbc:odbc:Christmas";
    //  Database credentials if needed
    static final String USER = "mark";
    static final String PASS = "mark213";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            //Register JDBC driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // Ask User for Data
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter First Name: "); // this is a cout
            System.out.flush(); // flushing the out buffer
            String firstName = in.readLine(); // mocking cin

            System.out.print("Enter Last Name: "); 
            System.out.flush(); 
            String lastName = in.readLine(); 

            System.out.print("Enter Address: "); 
            System.out.flush(); 
            String address = in.readLine(); 

            System.out.print("Enter Postal Code: "); 
            System.out.flush(); 
            String postalCode = in.readLine();

            // Insert Data into table
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO `ChristmasCards`(`FirstName`,`LastName`,`Address`,`PostalCode`) "
                    + "VALUES ('" + firstName + "','" + lastName + "','" + address + "','" + postalCode + "')";

            stmt.executeUpdate(sql); //Updates the Table with the information
            System.out.println("Inserted records into the table...");

            //Query the Database
            resultSet = stmt.executeQuery("SELECT lastName FROM ChristmasCards");

            // process query results
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("A list of the Last Names in Christmas Card Table:\n");

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.println(resultSet.getObject(i));
                }
            } // end while                    
        } 
        catch (SQLException se) 
        {
            //Handle errors for JDBC
            se.printStackTrace();
        } 
        catch (Exception e) 
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } 
        finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}//end class
