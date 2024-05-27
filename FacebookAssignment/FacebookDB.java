import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class FacebookDB implements DBOperations{
   // JDBC database URL, user name and password
   private final String DB_URL = "jdbc:mysql://localhost/";
   private final String FB_URL = "jdbc:mysql://localhost/facebook";
   private final String USER_NAME = "root";
   private final String PASSWORD = "password";
   
   private Connection conn;
   
   // Constructor
   public FacebookDB(){
      conn=null;
   }

   ////
   // createConnection() - Opens a connection to DB_URL
   ////
   public void createConnection(String dbUrl){
      try{
      	// STEP 1 - Open a connection
      	//          Use the DriverManager.getConnection() method to create a Connection object,
      	//          which represents a physical connection with the database server.
         conn = DriverManager.getConnection(dbUrl, USER_NAME, PASSWORD);
         System.out.println("COMPLETE - Connection obtained...");
      }
      catch (SQLException e) {
         System.out.println("Cannot create connection.\n" + e.getMessage());
      }
   }
   
   ////
   // createDatabase() - Calls createConnection(), creates a database called facebook with a table called user
   ////
   public void createDatabase(){
      try{
         // createConnection() to localhost
         createConnection(DB_URL);
      
         // Create Statement object		    
         Statement stmt = conn.createStatement();
         System.out.println("COMPLETE - Statement object created...");
          
         // Execute Update to Create a Database called facebook
         String createDatabase = "CREATE DATABASE IF NOT EXISTS facebook"; 
         stmt.executeUpdate(createDatabase);
         System.out.println("COMPLETE - Update executed and facebook database created...");
          
         // Execute Update to Create a Table called user
         String createTable = "CREATE TABLE IF NOT EXISTS user " +
                               "(emailaddress VARCHAR(24) not NULL, " +
                               " password VARCHAR(18), " + 
                               " firstname VARCHAR(20), " + 
                               " lastname VARCHAR(20), " + 
                               " PRIMARY KEY (emailaddress))"; 
                               
      
         stmt.executeUpdate("USE facebook");
         stmt.executeUpdate(createTable);
         System.out.println("COMPLETE - Update executed and user table added to facebook database...");
         
         // Call closeConnection() method to close the connection
         closeConnection();
      }
      catch (SQLException e){
         System.out.println("Problem with SQL.\n" + e.getMessage());
      }
   }

   ////
   // closeConnection() - Closes the connection
   ////
   public void closeConnection(){   
      try{
         if(conn != null){
            conn.close();
            System.out.println("COMPLETE - Connection closed.");				
         }
      }
      catch (SQLException e){
         System.out.println("Could not close connection.\n" + e.getMessage());
      }
   }
   
   ////
   // ADD PART A HERE
   // insertIntoDatabase() - Calls createConnection() to connect to facebook database, 
   //                      - and executes an sqlString to inserts a user into the user table in the facebook database
   ////
   
   //connect to facebook database
   //excute the SQL insert into the user table
   //Close the connection
   public void insertIntoDatabase(String sqlString)
   {
   
      try{
         //Creates connection to database
         createConnection(FB_URL);
         Statement stmt = conn.createStatement();
         System.out.println("COMPLETE - Statement object created...");
         //Executes the sql string
         stmt.executeUpdate(sqlString);
      
      }
      catch (SQLException e){
         System.out.println("Problem with SQL.\n" + e.getMessage());
      }
      
   }

   ////
   // ADD PART C HERE
   // getUserPasswordFromDatabase() - Calls createConnection() to connect to facebook database, 
   //                               - Selects all users with a required emailaddress (at most one should exist),
   //                               - and gets and returns that users password
   ////
   
   public ArrayList<String> getUserPasswordFromDatabase(String emailAddress, String password) 
   {
   
      try{
         //Creates a connection
         createConnection(FB_URL);
         Statement stmt = conn.createStatement();
         System.out.println("COMPLETE - Statement object created...");
         //SQL query that gets everything from the database where the emailaddress equals the emailaddress from the textfield in the gui
         String sqlQuery = "Select * FROM user WHERE emailaddress='"+emailAddress+"';";
         ResultSet rs = stmt.executeQuery(sqlQuery);
         ArrayList<String> array = new ArrayList<String>();
         while(rs.next() != false){
            //Stores the password from the database into an array
            array.add(rs.getString("password"));
         }
         //If the array is empty return "no"
         if(array.size() == 0)
         {
            array.add("no");
            return (array);
         }
         return (array);
      
      }
      catch (SQLException e){
         System.out.println("Problem with SQL.\n" + e.getMessage());
      }
      return null;
   }
   
}