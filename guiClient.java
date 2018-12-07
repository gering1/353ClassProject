/**
 * MTClient.java
 *
 *Charlie Raymond, Colton Gering, CPSC353 MWF 9-10am, Michael Fahy
 *
 * This program implements a simple multithreaded chat client.  It connects to the
 * server (assumed to be localhost on port 7654) and starts two threads:
 * one for listening for data sent from the server, and another that waits
 * for the user to type something in that will be sent to the server.
 * Anything sent to the server is broadcast to all clients.
 *
 * The MTClient uses a ClientListener whose code is in a separate file.
 * The ClientListener runs in a separate thread, recieves messages form the server,
 * and displays them on the screen.
 *
 * Data received is sent to the output screen, so it is possible that as
 * a user is typing in information a message from the server will be
 * inserted.
 *
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class guiClient {
  /**
   * main method.
   * @params not used.
   */
  	UsernameAndPass up;
	static DataOutputStream serverOutput;


	//constructor which initializes the gui
  	public guiClient(UsernameAndPass up)
  	{
    	this.up = up;
  	}


	//connects to the server, assuming port 7654, and ip
	public void connect()
	{
    	try {
		
      	//	String hostname = "localhost"; 
      		String hostname = "172.17.0.1";
      		int port = 7654;

      		System.out.println("Connecting to server on port " + port);
      		Socket connectionSock = new Socket(hostname, port);

      		serverOutput = new DataOutputStream(connectionSock.getOutputStream());

      		System.out.println("Connection made.");

      		ClientListener listener = new ClientListener(connectionSock, up);
      		Thread theThread = new Thread(listener);
      		theThread.start();

    	} catch (IOException e) {
      		System.out.println(e.getMessage());
    	}
	}

	//this takes login information and sends it to the server to check
	public void login(String username, String password, String messageType)
	{
		try{
			String data = messageType + " " + username + " " + password;
			serverOutput.writeBytes(data+ "\n");
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
		} catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	//this sends user information to the server to save to the user
  	public void updateUser(String oldName, String newData)
  	{

  		try {
    		String message = oldName + " " + newData;
    		serverOutput.writeBytes("7 " + message + "\n"); //chat message type
  		} catch(NullPointerException e) {
    		System.out.println(e.getMessage());
  		} catch(IOException e){
    		System.out.println(e.getMessage());
  		}
  	}

	//sends the users message out to the server to send to all
	public void messageOut(String message)
	{
		try {
			String data = message;
			System.out.println(data);
			serverOutput.writeBytes("3 " + data+ "\n"); //chat message type
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
		} catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	//this sends user information to the server to add to the list of users
	public void addUser(String username, String password, String messageType)
	{
		try{
			String data = messageType + " " + username + " " + password;
			serverOutput.writeBytes(data+ "\n");
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
		} catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
} // MtClient
