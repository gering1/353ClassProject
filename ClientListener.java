/**
 * ClientListener.java
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


public class ClientListener implements Runnable {
  private Socket connectionSock = null;
  UsernameAndPass up;

  ClientListener(Socket sock, UsernameAndPass up) {
    this.connectionSock = sock;
    this.up = up;

  }

  /**
   * Gets message from server and dsiplays it to the user.
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
          // need to get the remote user object somehow!!
          //
			//System.out.println("WORKING5");
			//System.out.println(serverText);

			System.out.println("THIS IS THE SERVER TEXT: " + serverText);
			if(serverText.equals("SUCCESS"))
			{
					System.out.println("Added user to list");
			}
			else if(serverText.equals("true 1 1 1")){
				up.setLogin("true");
			}
			else if(serverText.equals("false 1 1 1")){
				Platform.runLater(() -> up.setLogin("false"));
			}
			else
					System.out.println("YouLL never get this");
      if(messageType.equals("3")){
        //chat message
        String message = "";
        for(int i = 1; i < splitText.length; i++)
        {
          message += splitText[i] + " ";
        }
        final String message2 = message; //
        Platform.runLater(()-> up.ta.appendText(message2 + "\n\n"));
      }

        } else {
          // Connection was lost
          System.out.println("Closing connection for socket " + connectionSock);
          connectionSock.close();
          break;
        }
      }
	  System.out.println("WE NEVRE GER HERE");
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
    }
  }
} // ClientListener for MtClient
