/**
 * MTServer.java
 *
 * Charlie Raymond, Colton Gering, CPSC353 MWF 9-10am, Michael Fahy
 *
 *
 * This program implements a simple multithreaded chat server.  Every client that
 * connects to the server can broadcast data to all other clients.
 * The server stores an ArrayList of sockets to perform the broadcast.
 *
 * The MTServer uses a ClientHandler whose code is in a separate file.
 * When a client connects, the MTServer starts a ClientHandler in a separate thread
 * to receive messages from the client.
 *
 * To test, start the server first, then start multiple clients and type messages
 * in the client windows.
 *
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

//Server class
public class MtServer {
  	// Maintain list of all client sockets for broadcast
  	private ArrayList<Socket> socketList;
  	UserAccount user = new UserAccount();

	//constructor, initializes variable
  	public MtServer() {
    	socketList = new ArrayList<Socket>();
  	}

	//gets connection, and passes in a list of user accounts
  	private void getConnection(UserAccountList userList) {
    	// Wait for a connection from the client
    	try {
      		System.out.println("Waiting for client connections on port 7654.");
      		ServerSocket serverSock = new ServerSocket(7654);
      		// This is an infinite loop, the user will have to shut it down
      		// using control-c
      		while (true) {
        		Socket connectionSock = serverSock.accept();
        		// Add this socket to the list
        		socketList.add(connectionSock);
        		// Send to ClientHandler the socket and arraylist of all sockets
        		ClientHandler handler = new ClientHandler(connectionSock, this.socketList, userList);
        		Thread theThread = new Thread(handler);
        		theThread.start();
      		}
      		// Will never get here, but if the above loop is given
      		// an exit condition then we'll go ahead and close the socket
      		//serverSock.close();
    	} catch (IOException e) {
      		System.out.println(e.getMessage());
    	}
  	}

	//main, loads the user list then gets server running
  	public static void main(String[] args) {

  		UserAccountList userList = new UserAccountList();
		userList.inputObject();
    	MtServer server = new MtServer();
    	server.getConnection(userList);
  	}
} // MtServer
