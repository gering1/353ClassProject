/**
 * ClientHandler.java
 *
 * This class handles communication between the client
 * and the server.  It runs in a separate thread but has a
 * link to a common list of sockets to handle broadcast.
 *
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

import java.util.ArrayList;
import java.util.Scanner;


public class ClientHandler implements Runnable {
  private Socket connectionSock = null;
  private ArrayList<Socket> socketList;
  UserAccountList userList;

  ClientHandler(Socket sock, ArrayList<Socket> socketList, UserAccountList userList) {
    this.connectionSock = sock;
    this.socketList = socketList;  // Keep reference to master list
    this.userList = userList;
  }

  /**
  * received input from a client.
  * sends it to other clients.
  */
  public void run() {
    try {
      System.out.println("Connection made with socket " + connectionSock);
      BufferedReader clientInput = new BufferedReader(
      new InputStreamReader(connectionSock.getInputStream()));
      while (true) {
        // Get data sent from a client
        String clientText = clientInput.readLine();
        String[] splitText = clientText.split(" ");
        String messageType = splitText[0];
        String username;
        String password;

        boolean again = true;

        if (clientText != null) {
          System.out.println("Received: " + clientText);
          System.out.println("message type: " + messageType);
            switch(messageType)
            {
              //login case
              case "1":
              username = splitText[1];
              password = splitText[2];
              if(userList.login(username, password))
              {
                DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
                String message = userList.toString();
                clientOutput.writeBytes("4 " + message + "\n");

          }
          else{
            DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
            clientOutput.writeBytes("8 " + "\n");
      }
      again = false;
      break;
      //add user case
      case "2":
      username = splitText[1];
      password = splitText[2];
      UserAccount newUser = new UserAccount(username, password, "HEY","x","x");
      userList.addUser(newUser);
      DataOutputStream clientOutput;
      String message = userList.toString();
      for (Socket s : socketList) {
        if (s != connectionSock) {
          clientOutput = new DataOutputStream(s.getOutputStream());
          clientOutput.writeBytes("8 " + message + "\n");
        }
      }

      //Data
      again = false;
      break;
      //message case
      case "3":
      for (Socket s : socketList) {
        if (s != connectionSock) {
          clientOutput = new DataOutputStream(s.getOutputStream());
          clientOutput.writeBytes(clientText + "\n");
        }

      }
      break;

      case "7":
      userList.updateUserFromString(splitText[1],splitText[2]);
      String msg = userList.toString();
      System.out.println("In case 7 " + msg);
      for (Socket s : socketList) {
        if (s != connectionSock) {
          clientOutput = new DataOutputStream(s.getOutputStream());
          clientOutput.writeBytes("8 " + msg + "\n");
        }
      }
      break;

      default:

      break;
    }
  // Turn around and output this data
  // to all other clients except the one
  // that sent us this information

} else {
  // Connection was lost
  System.out.println("Closing connection for socket " + connectionSock);
  // Remove from arraylist
  socketList.remove(connectionSock);
  connectionSock.close();
  break;
}

}
} catch (Exception e) {
  System.out.println("Error: " + e.toString());
  // Remove from arraylist
  socketList.remove(connectionSock);
}

}
} // ClientHandler for MtServer.java
