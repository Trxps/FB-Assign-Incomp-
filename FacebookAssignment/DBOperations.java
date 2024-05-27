/**
* Class: B.Sc. in Computing
* Description: A DBOperations interface - All classes implementing this interface must override its abstract methods
* Date: 12/03/2021
* @author Maria Boyle
* @version 1.0
*/

import java.util.*;

public interface DBOperations{ 
   public abstract void createConnection(String dbUrl);
   public abstract void createDatabase();
   public abstract void closeConnection();
   public abstract void insertIntoDatabase(String sqlString); 
   public abstract ArrayList<String> getUserPasswordFromDatabase(String emailAddress, String password);  
      
}