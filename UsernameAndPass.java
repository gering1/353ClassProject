import javafx.application.Application;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.Image;
import java.net.URL;
import java.net.URLConnection;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.scene.control.PasswordField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.control.ButtonBase;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.io.*;

class User{
  public String name;
  public String password;
  // other stuff like login time, avatar, etc. ??
}

public class UsernameAndPass extends Application{
  Stage window;
  Scene scene1, scene2;
  TextArea ta;
  ListView<String> usersList = new ListView<String>();
  TextField userTextField;
  PasswordField pwBox;
  boolean spotifyCreds = false;

  //User user = new User();
  UserAccount user = new UserAccount();
  guiClient client = new guiClient(this);
	static UserAccountList userList = new UserAccountList();
	//MtServer server = new MtServer();
	boolean loginV;

  public static void main(String[] args){
	//userList.inputObject();
    //creating test users
    userList.setFileWrite(false); //don't create file on client side
    /*
    UserAccount Colton = new UserAccount("colton", "colton", "123");
    UserAccount blah = new UserAccount("blah", "blah", "123");
    userList.addUser(Colton);
    userList.addUser(blah);
    */
    launch(args);

	//userList.outputStream(userList);
  }

  private void handleLogin(String name, String password) {
	String messageType = "1";
	client.login(name, password, messageType);
   //return server.login(name, password);
   //client.connect();
   // create a user object in Main of type user class and stuff values in there
   // if successful login... otherwise if login not successful, return false
   //return true;
 }

 public void editProfile(UserAccount user)
 {
   //allows user to change profile settings and save
   GridPane secondaryLayout = new GridPane();

   Scene secondScene = new Scene(secondaryLayout, 500, 500);

   Label userName = new Label("User Name:");
   secondaryLayout.add(userName, 0, 1);
   TextField userTextField = new TextField(user.getUserName());
   secondaryLayout.add(userTextField, 1, 1);

   /*
   Label ageL = new Label("Age");
   secondaryLayout.add(ageL, 0, 3);
   TextField ageTextField = new TextField(user.getAge().toString());
   secondaryLayout.add(ageTextField, 1, 3);
   */

   // New window (Stage)
   Stage newWindow = new Stage();
   newWindow.setTitle("Second Stage");
   newWindow.setScene(secondScene);

   Button saveProfButton = new Button("Save Changes");
   secondaryLayout.add(saveProfButton,5,9);

   Button exitEditButton = new Button("Exit");
   secondaryLayout.add(exitEditButton,8,10);

   saveProfButton.setOnAction(new EventHandler<ActionEvent>() {
       @Override public void handle(ActionEvent e) {
         UserAccount clickedUser = userList.findUser(user.getUserName());
         if(clickedUser != null)
         {
           String oldName = user.getUserName();
           user.setUserName(userTextField.getText());
           clickedUser.setUserName(user.getUserName());
           updateViewList();
           client.updateUser(oldName, clickedUser.toString());
        }
       }
   });

   //close window when button is clicked
   exitEditButton.setOnAction(new EventHandler<ActionEvent>() {
       @Override public void handle(ActionEvent e) {
         Stage stage = (Stage) exitEditButton.getScene().getWindow();
         stage.close();
       }
   });
   // Set position of second window, related to primary window.
   newWindow.setX(scene1.getX() + 200);
   newWindow.setY(scene1.getY() + 100);

   newWindow.show();
 }
/*
 public void displayProfile(UserAccount user)
 {
   GridPane secondaryLayout = new GridPane();
   Scene secondScene = new Scene(secondaryLayout, 500, 500);
   Label userName = new Label("User Name:");
   secondaryLayout.add(userName, 0, 1);


   //Label ageL = new Label("Age");
   //secondaryLayout.add(ageL, 0, 3);

   //TextField ageTextField = new TextField(user.getAge().toString());
   //ageTextField.setDisable(true);
   //secondaryLayout.add(ageTextField, 1, 3);

   //Button exitDisplayButton = new Button("Exit");
   //secondaryLayout.add(exitDisplayButton, 5, 5);

   //close window when button is clicked
   exitDisplayButton.setOnAction(new EventHandler<ActionEvent>() {
       @Override public void handle(ActionEvent e) {
         Stage stage = (Stage) exitDisplayButton.getScene().getWindow();
         stage.close();
       }
   });



   TextField userTextField = new TextField(user.getUserName());
   userTextField.setDisable(true);
   secondaryLayout.add(userTextField, 1, 1);


   Label age = new Label("Age");
   secondaryLayout.add(age, 0, 3);
   // New window (Stage)
   Stage newWindow = new Stage();
   newWindow.setTitle("Second Stage");
   newWindow.setScene(secondScene);

   // Set position of second window, related to primary window.
   newWindow.setX(scene1.getX() + 200);
   newWindow.setY(scene1.getY() + 100);

   newWindow.show();
 }
*/
// Create a javafx Image from a url
  // Taken from stack overflow example
  //
private javafx.scene.image.Image createImage(String url)  throws IOException
{
    URLConnection conn = new URL(url).openConnection();
    conn.setRequestProperty("User-Agent", "Wget/1.13.4 (linux-gnu)");
    InputStream stream = conn.getInputStream();
    return new javafx.scene.image.Image(stream);
}

public void displayProfile(UserAccount ua)
 {
   GridPane secondaryLayout = new GridPane();
   secondaryLayout.setPadding(new Insets(10, 10, 10, 10));

      //Setting the vertical and horizontal gaps between the columns
      secondaryLayout.setVgap(10);
      secondaryLayout.setHgap(5);

   Scene secondScene = new Scene(secondaryLayout, 500, 500);
   Label userName = new Label("User Name:");
   secondaryLayout.add(userName, 0, 0);

   TextField userTextField = new TextField(ua.getUserName());
   if(ua.getUserName().equals(user.getUserName()))
   {
     userTextField.setDisable(false);
   }
   else
   {
     userTextField.setDisable(true);
   }
   secondaryLayout.add(userTextField, 1, 0);

   Label trackLabel = new Label("Track:");
   TextField trackField = new TextField();
   //trackField.setDisable(true);
   secondaryLayout.add(trackLabel, 0, 1);
   secondaryLayout.add(trackField, 1, 1);

   Label artistLabel = new Label("Artist:");
   TextField artistField = new TextField();
   //trackField.setDisable(true);
   secondaryLayout.add(artistLabel, 0, 2);
   secondaryLayout.add(artistField, 1, 2);

   //TextField albumField = new TextField ("Artist: None");
   Text albumText = new Text("No Album");
   secondaryLayout.add(albumText, 1, 3);

   Button saveProfButton = new Button("Save Changes");
   if(ua.getUserName().equals(user.getUserName()))
   {
     saveProfButton.setDisable(false);
   }
   else
   {
     saveProfButton.setDisable(true);
   }
   secondaryLayout.add(saveProfButton,4,5);

   //if(user.getUserName() )

   saveProfButton.setOnAction(new EventHandler<ActionEvent>() {
       @Override public void handle(ActionEvent e) {
         UserAccount clickedUser = userList.findUser(user.getUserName());
         if(clickedUser != null)
         {
           String oldName = user.getUserName();
           user.setUserName(userTextField.getText());
           clickedUser.setUserName(user.getUserName());
           updateViewList();
           client.updateUser(oldName, clickedUser.toString());
        }
       }
   });


   Button spotifyDisplayButton = new Button("Get Spotify Info");
   secondaryLayout.add(spotifyDisplayButton, 5, 0);
   spotifyDisplayButton.setOnAction(new EventHandler<ActionEvent>() {
       @Override public void handle(ActionEvent e) {
         //get the spotify info

        SpotifySearch ss = new SpotifySearch();
        if (!spotifyCreds){
          ss.clientCredentials_Sync();
          spotifyCreds = true;
        }
        //Track [] tracks = ss.searchTracks_Sync("Eruption");
        Track [] tracks = ss.searchTracks_Sync("mr brightside");

        String album  = tracks[0].getAlbum().getName();
        Image []albumImage = tracks[0].getAlbum().getImages();
        javafx.scene.image.Image im = null;
        try {
           im = createImage(albumImage[0].getUrl());
        } catch (Exception ex) {}
        if (tracks != null){
          ImageView iv= new ImageView(im);
          iv.setFitHeight(150);
          iv.setFitWidth(150);
          secondaryLayout.add(iv, 3,3);
          albumText.setText(album);
          System.out.println(" ALBUM = " + album);
          System.out.println(" IMAGE = "+ albumImage[0].getUrl());
        } else {
          System.out.println("No Tracks FOUND!!");
        }
       }
   });
   Button exitDisplayButton = new Button("Close");
   secondaryLayout.add(exitDisplayButton, 5, 5);

   //close window when button is clicked
   exitDisplayButton.setOnAction(new EventHandler<ActionEvent>() {
       @Override public void handle(ActionEvent e) {
         Stage stage = (Stage) exitDisplayButton.getScene().getWindow();
         stage.close();
       }
   });

   Stage newWindow = new Stage();
   newWindow.setTitle("Second Stage");
   newWindow.setScene(secondScene);

   // Set position of second window, related to primary window.
   newWindow.setX(scene1.getX() + 200);
   newWindow.setY(scene1.getY() + 100);

   newWindow.show();

 }


  public Scene createLoginScene(Stage win) {

	client.connect();
      //final Text actiontarget = new Text();
      GridPane grid = new GridPane();
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(25, 25, 25, 25));
      Button signInBtn = new Button("Sign in");
      Button addBtn = new Button("Create user");

      Text scenetitle = new Text("Chat Login");
      scenetitle.setFont(Font.font("Allan", FontWeight.NORMAL, 32));
      grid.add(scenetitle, 0, 0, 2, 1);

      Label userName = new Label("User Name:");
      grid.add(userName, 0, 1);

      userTextField = new TextField();
      grid.add(userTextField, 1, 1);

      Label pw = new Label("Password:");
      grid.add(pw, 0, 2);

      pwBox = new PasswordField();
      grid.add(pwBox, 1, 2);
      HBox hbBtn = new HBox(10);
      hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
      hbBtn.getChildren().add(signInBtn);
      grid.add(hbBtn, 1, 4);

      HBox newBtn = new HBox(10);
      newBtn.setAlignment(Pos.BOTTOM_LEFT);
      newBtn.getChildren().add(addBtn);
      grid.add(newBtn, 1, 5);

      signInBtn.setOnAction(e -> {

      handleLogin(userTextField.getText(), pwBox.getText());

      });

	  addBtn.setOnAction(e -> {
		addNewUser(userTextField.getText(), pwBox.getText());
	  });
      userTextField.clear();
      pwBox.clear();
      return new Scene(grid, 300, 250);
    }

	private Scene createMainScene(Stage win){


		BorderPane layout2 = new BorderPane();
    HBox usersBox = new HBox();
    HBox taBox = new HBox();
    HBox inputBox = new HBox();
		String textMessage = "";

    inputBox.setSpacing(20);
    Button enterButton = new Button("Enter");
		layout2.setAlignment(enterButton, Pos.BOTTOM_RIGHT);

    Button editProfButton = new Button("Edit Profile");
    usersList.setPrefWidth(200);
    usersList.setPrefHeight(50);



		TextField userInput = new TextField();
    userInput.setPrefWidth(200);
		userInput.setMaxWidth(200);

    Label userName = new Label(user.getUserName());

		enterButton.setOnAction(e -> {
				Platform.runLater(new Runnable(){
						@Override public void run(){
							sendMessage(userInput.getText());
              userInput.clear();
						}
				});
		});

    editProfButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          //open window to edit profile
          //editProfile(user);
          displayProfile(user);

        }
    });
    userInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)){
            Platform.runLater(new Runnable(){
            @Override public void run(){
              sendMessage(userInput.getText());
              userInput.clear();
            }
        });
        }
      }
    });

    usersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        //System.out.println(newValue);
        UserAccount clickedUser = userList.findUser(newValue);
        displayProfile(clickedUser);


    }
});


    // chat message display area
    ta = new TextArea();
    ta.setPrefHeight(250);
    ta.setPrefWidth(200);
    ta.setEditable(false);
    //VBox vbox = new VBox(ta);
    //vbox.setPrefSize(400,300);

    taBox.getChildren().add(ta);
		inputBox.getChildren().add(userInput);
    inputBox.getChildren().add(enterButton);
    inputBox.getChildren().add(editProfButton);
    usersBox.getChildren().add(usersList);

    layout2.setLeft(taBox);
    layout2.setBottom(inputBox);
    layout2.setRight(usersBox);
		return new Scene(layout2, 400, 400);
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


/*
	public String getUsername()
    {
    	return user.name;
    }
    public String getPassword()
    {
     	return user.password;
    }

    public void setMessage(String newMessage)
    {
	    user.message = newMessage;
    }

    public String getMessage()
    {
		return user.message;
    }
*/
    public void sendMessage(String newMessage)
    {
		try{
			Platform.runLater(new Runnable(){
				@Override public void run(){
					client.messageOut(user.getUserName() + ": " + newMessage);
          //update chat window
          ta.appendText(user.getUserName() + ": " + newMessage + "\n\n");
				}
			});
		} catch(NullPointerException e){
				throw new IllegalStateException("Somethin is null");
		}
    }

	public void addNewUser(String username, String password)
	{

		//UserAccount newUser = new UserAccount(username, password, "Hey");
		client.addUser(username, password, "2");
		//server.addUser(newUser);

	}

	public void setLogin(boolean login)
	{
			System.out.println("Login is: " + login);
		if(login)
		{
        this.loginV = true;
        user.setUserName(userTextField.getText());
        /*
        UserAccount ua = null;
        //UserAccount ua = userList.findUser(user.getUserName());
        if(ua != null)
        {
          user.setTrack(ua.getTrack());
        }
        else
        {
          System.out.println("Didn't find user " + user.getUserName());
        }
        */
        window.setScene(scene2);
        window.setTitle("Main Chat App");

		}
		else
				this.loginV =false;
        userTextField.clear();
        pwBox.clear();
	}

	public boolean getLogin()
	{
		return this.loginV;
	}

  public void updateViewList()
  {
    //populate listview with users
    usersList.getItems().clear();
    Iterator<UserAccount> it = userList.users.iterator();

    while(it.hasNext())
    {
        usersList.getItems().add(it.next().getUserName());
    }
  }

}
