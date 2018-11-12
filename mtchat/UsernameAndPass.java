import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.control.ButtonBase;
import java.io.*;
class User{
  public String name;
  public String password;
  // other stuff like login time, avatar, etc. ??
}

public class UsernameAndPass extends Application{
  Stage window;
  Scene scene1, scene2;
  User user = new User();

  public static void main(String[] args){
    launch(args);
  }

  private boolean handleLogin(String name, String password) {
   user.name = name;
   user.password = password;
   // create a user object in Main of type user class and stuff values in there
   // if successful login... otherwise if login not successful, return false
   return true;
 }

  public Scene createLoginScene(Stage win) {

      //final Text actiontarget = new Text();
      GridPane grid = new GridPane();
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(25, 25, 25, 25));
      Button btn = new Button("Sign in");

      Text scenetitle = new Text("Chat Login");
      scenetitle.setFont(Font.font("Allan", FontWeight.NORMAL, 32));
      grid.add(scenetitle, 0, 0, 2, 1);

      Label userName = new Label("User Name:");
      grid.add(userName, 0, 1);

      TextField userTextField = new TextField();
      grid.add(userTextField, 1, 1);

      Label pw = new Label("Password:");
      grid.add(pw, 0, 2);

      PasswordField pwBox = new PasswordField();
      grid.add(pwBox, 1, 2);
      HBox hbBtn = new HBox(10);
      hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
      hbBtn.getChildren().add(btn);
      grid.add(hbBtn, 1, 4);

      btn.setOnAction(e -> {
        if (handleLogin(userTextField.getText(), pwBox.getText())){
          window.setScene(scene2);
          window.setTitle("Main Chat App");
        } else {
          System.out.println("Login failed");
        }
      });
      return new Scene(grid, 300, 250);
    }

    private Scene createMainScene(Stage win){
        Button button2 = new Button("Exit");
        button2.setOnAction(e -> window.close());
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);

        return new Scene(layout2, 600, 600);
      }

      @Override
    public void start(Stage primaryStage) throws Exception {
    window = primaryStage;
    scene1 = createLoginScene(window);
    scene2 = createMainScene(window);

    window.setScene(scene1);
    window.setTitle("Login Window");
    window.show();
}

    public String getUsername()
    {
      return user.name;
    }
    public String getPassword()
    {
      return user.password;
    }
}
