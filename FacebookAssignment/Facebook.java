import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.PasswordField;
import javafx.scene.text.*;
import java.util.*;

// Facebook Application
public class Facebook extends Application
{
   // Facebook has 2 Scenes that uses BorderPane as its Layout Pane
   private Scene scene1, scene2;
   //Scene1 variables
   //BorderPane layout
   private BorderPane sceneLayout;
   // and has a subPane (top) that uses GridPane, 
   private GridPane p1;
   // with 1 Labels, and 2 TextFields, and 2 buttons
   private TextField email1, password1;
   private Button logIn,signUp;
   private Label fb;
   // a subpane (bottom) that uses StackPane
   private StackPane p2;
   // Scene2 Variables
   //BorderPane layout
   private BorderPane sceneLayout2;
   // and has a subPane (right) that uses GVBox, 
   private VBox p3;
   // with 1 Labels, and 4 TextFields, and a Button
   private Button signUp2;
   private TextField fName,lName, email2, password2;
   private Label fb1;
   // a subpane (left) that uses StackPane
   private StackPane p4;

   //The first scene set as the primary stage
   @Override
   public void start(Stage primaryStage)
   {
   
      FacebookDB fbDb = new FacebookDB();
      fbDb.createDatabase();
      // Construct p1
      p1=new GridPane();
      //Constructs a border around the gridPnae
      p1.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));
      //Constructs the Labels,texfields and buttons
      p1.add(new Label("Email:"),0,0);
      p1.add(new Label("Password:"),0,1);  
      p1.add(email1=new TextField(),1,0);                    
      PasswordField passwordField = new PasswordField();
      p1.add(passwordField, 1, 1);           
      p1.add(logIn=new Button("Log In"),2,0); 
      //Sets the background of the button to Alice Blue
      logIn.setStyle("-fx-background-color: #f0f8ff; ");
      //Constructs the border around the button
      logIn.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));
      //Sets the button to the max width of the coloumn
      logIn.setMaxWidth(Double.MAX_VALUE);   
      //Lamba expression event handler for the Log In button 
      logIn.setOnAction(
         e -> {
         //Gets the text from the text field and stores it as variables
            String emailAddress = String.valueOf(email1.getText());
            String password = String.valueOf(passwordField.getText());
            //Calls the getUserPasswordFromDatabase method and stores it in an array
            ArrayList<String> array = fbDb.getUserPasswordFromDatabase(emailAddress, password);
         
            //Iterates through the array making comparsions with the data in the database and the data inputed
            int i;
            for(i=0; i<array.size(); i++)
            {
               //If the array returns empty, then the email doesn't exist
               if(array.get(i).equals("no"))             
               {
                  // Error Dialog
                  Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                  errorAlert.setTitle("Error");
                  errorAlert.setHeaderText("Log In Error");
                  errorAlert.setContentText("Email Address Does not Exist");
                  errorAlert.showAndWait();
               }
               //If the passwords match the user logs in
               else if(array.get(i).equals(password))
               {
                  // Alert Information Dialog
                  Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                  informationAlert.setTitle("Information");
                  informationAlert.setHeaderText("Logged In");
                  informationAlert.setContentText("Press OK to continue");
                  informationAlert.showAndWait();
               }
               //If the passwords don't match then an error message displays
               else if(!array.get(i).equals(password))
               {
                  // Error Dialog
                  Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                  errorAlert.setTitle("Error");
                  errorAlert.setHeaderText("Log In Error");
                  errorAlert.setContentText("Wrong Password ");
                  errorAlert.showAndWait();
               }
            }              
         });      
      //Creates the sign up button with a background colour and a border
      p1.add(signUp=new Button("Sign Up"),2,1);
      signUp.setStyle("-fx-background-color: #f0f8ff; ");
      signUp.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));  
      signUp.setMaxWidth(Double.MAX_VALUE); 
      //Lamba expression event handler for the sign up button
      signUp.setOnAction(
         e -> {
         //Switches the scene from scene1 to scene2
            primaryStage.setScene(scene2);
         }); 
      //Creates a coloumn split of 25, 50, 25
      ColumnConstraints col1 = new ColumnConstraints();
      col1.setPercentWidth(25);
      ColumnConstraints col2 = new ColumnConstraints();
      col2.setPercentWidth(50);
      ColumnConstraints col3 = new ColumnConstraints();
      col3.setPercentWidth(25);
      p1.getColumnConstraints().addAll(col1,col2,col3);
      
      // Construct p2
      p2=new StackPane();
      //Constructs a border around p2
      p2.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));
      //Adds the label with the facebook png
      p2.getChildren().add(fb=new Label("", new ImageView("facebook.png"))); 
           
      // Construct sceneLayout
      sceneLayout=new BorderPane();
      // and setTop() of sceneLayout to p1                                
      sceneLayout.setTop(p1);
      // and setCenter() of sceneLayout to p2                                
      sceneLayout.setCenter(p2);
     
      // Construct scene1
      scene1=new Scene(sceneLayout);
      
      //Start of scene2
      VBox p3=new VBox();
      //Constructs a border around the VBox
      p3.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));
      //Adss a label, 4 textfields and a button 
      p3.getChildren().add(fb = new Label("Sign Up")); 
      fb.setMaxWidth(Double.MAX_VALUE); 
      //Sets the font to SanSerif and the style to bold
      fb.setFont(Font.font("SanSerif",FontWeight.BOLD,18));
      fb.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));
      p3.getChildren().add(fName=new TextField());  
      //Sets the colour of the text in the textfield to grey
      fName.setStyle("-fx-text-inner-color: grey;"); 
      fName.setPromptText("Enter First Name");
      p3.getChildren().add(lName=new TextField());
      lName.setStyle("-fx-text-inner-color: grey;"); 
      lName.setPromptText("Enter Last Name");
      p3.getChildren().add(email2=new TextField()); 
      email2.setStyle("-fx-text-inner-color: grey;"); 
      email2.setPromptText("Enter Email");
      p3.getChildren().add(password2=new TextField()); 
      password2.setStyle("-fx-text-inner-color: grey;");   
      password2.setPromptText("Enter Password");                     
      p3.getChildren().add(signUp2=new Button("Sign Up"));
      signUp2.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1)))); 
      //Sets the font to SanSerif and the style to bold
      signUp2.setFont(Font.font("SanSerif",FontWeight.BOLD,18));
      //Sets button background colour to Alice Blue
      signUp2.setStyle("-fx-background-color: #f0f8ff; ");
      signUp2.setMaxWidth(Double.MAX_VALUE);    
      //Lamba expression event handler for the sign up button
      signUp2.setOnAction(
         e -> {
            //Gets the text from the textfields and stores them as vsriables
            String email = String.valueOf(email2.getText());
            String password = String.valueOf(password2.getText());
            String fname = String.valueOf(fName.getText());
            String lname = String.valueOf(lName.getText());
            //If any textfields are empty display an error message saying to fill in all fields
            if(email.isEmpty() || password.isEmpty() || fname.isEmpty() || lname.isEmpty())
            {                            
               // Error Dialog
               Alert errorAlert = new Alert(Alert.AlertType.ERROR);
               errorAlert.setTitle("Error");
               errorAlert.setHeaderText("Sign Up Error");
               errorAlert.setContentText("Please make sure all details are entered");
               errorAlert.showAndWait();
            }
            else{
               //Writes the varialbes to the database using a sql query and sets the scene back to the first scene
               String sql = "INSERT INTO user VALUES ('"+email+"','"+password+"','"+fname+"','"+lname+"') " +
                  "ON DUPLICATE KEY UPDATE password='"+password+"',firstname='"+fname+"',lastname='"+lname+"'";
               fbDb.insertIntoDatabase(sql);
               primaryStage.setScene(scene1);
            }
         });       
      
      // Construct p4
      p4=new StackPane();
      p4.setBorder(new Border(new BorderStroke(Color.BLACK,
                                      BorderStrokeStyle.SOLID,
                                      new CornerRadii(0),
                                      new BorderWidths(1))));
      // setAlignment() on p4 Pos.CENTER
      p4.setAlignment(Pos.CENTER);
      //Adds a label with the image of facebook
      p4.getChildren().add(fb=new Label("", new ImageView("facebook.png")));   
           
      // Construct sceneLayout
      sceneLayout2=new BorderPane();
      // and setRight() of sceneLayout to p3                              
      sceneLayout2.setRight(p3);
      // and setLeft() of sceneLayout to p4                            
      sceneLayout2.setLeft(p4);
     
      // Construct scene2
      scene2=new Scene(sceneLayout2);
      
      //The application begins with scene1 being displayed first
      primaryStage.setScene(scene1);
      primaryStage.setTitle("Facebook");
      primaryStage.show(); 
   
   }
   
}

