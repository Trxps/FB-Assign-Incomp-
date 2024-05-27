public class FacebookDBTester{
   public static void main(String[] args) {
      FacebookDB fbDb = new FacebookDB();
   
      fbDb.createDatabase();
      
//        String sql = "INSERT INTO user VALUES ('b.simp@lyit.ie','LetMeIn','Bart','Simpson') " +
//           "ON DUPLICATE KEY UPDATE password='LetMeIn',firstname='Bart',lastname='Simpson'";
//        fbDb.insertIntoDatabase(sql);
      
      
      
   }
}