/**
 * ClientListener.java
 *
 *Charlie Raymond, Colton Gering, CPSC353 MWF 9-10am, Michael Fahy
 *
 *
 * This class runs on the client end and just
 * displays any text received from the server.
 *
 */

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

import java.util.ArrayList;
import java.util.Scanner;



//Client Listener class which implements runnable
public class ClientListener implements Runnable {
  	private Socket connectionSock = null;
  	UsernameAndPass up;


	//constructor to initialize variables
  	ClientListener(Socket sock, UsernameAndPass up) {
    	this.connectionSock = sock;
    	this.up = up;

  	}

  /**
   * Gets message from server and replays information back to client
   */
 	public void run() {
   		try {
      		BufferedReader serverInput = new BufferedReader(
          	new InputStreamReader(connectionSock.getInputStream()));
      		while (true) {
        		// Get data sent from the server
        		String serverText = serverInput.readLine();
        		String[] splitText = serverText.split(" ");
        		String messageType = splitText[0];

				System.out.println("THIS IS THE SERVER TEXT: " + serverText);
        		if (serverInput != null) {
          			switch(messageType)
          			{
            			case "3": //chat message from server
              				String message = "";
              				for(int i = 1; i < splitText.length; i++)
              				{
                				message += splitText[i] + " ";
              				}
              				final String message2 = message; //
              				Platform.runLater(()-> up.ta.appendText(message2 + "\n\n"));
              				break;
            			case "4": //login succeeded
              				up.userList.stringToUserList(splitText[1]);
              				Platform.runLater(() -> up.updateViewList());
              				Platform.runLater(() -> up.setLogin(true));
              				break;
            			case "5": //login failed
              				Platform.runLater(() -> up.setLogin(false));
              				break;
            			case "8": //update to userlist from server
              				up.userList.stringToUserList(splitText[1]);
              				Platform.runLater(() -> up.updateViewList());
              				break;
            			default:
              				break;
          			}
        		} else {
          			// Connection was lost
          			System.out.println("Closing connection for socket " + connectionSock);
          			connectionSock.close();
          			break;
        		}
      		}
  		}catch (Exception e) {
      		System.out.println("Error: " + e.toString());
    	}
	}
} // ClientListener for MtClient
