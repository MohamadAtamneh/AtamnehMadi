// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;

import java.io.*;
import java.sql.SQLException;
import java.util.Vector;

import logic.Subscriber; 

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  //final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * 
   */
	public static Subscriber[] subscribers;  


  public EchoServer(int port) 
  {
    super(port);
  }

  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   * @param 
   */

  public void handleMessageFromClient(Object msg, ConnectionToClient client) {
	    System.out.println("Message received: " + msg + " from " + client);
	    try {
	        String message = msg.toString();
	        if (message.startsWith("UpdateSubscriber")) {
	        	handleUpdateSubscriber(message);
	        } else {
	        	Subscriber subscriber = DBConnector.getSubscriber(message);
	            if (subscriber != null) {
	                System.out.println("Server Found");
	                this.sendToAllClients(subscriber.toString());
	            } else {
	                System.out.println("Not Found");
	                this.sendToAllClients("Error");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Database Error");
	        this.sendToAllClients("Error");
	        e.printStackTrace();
	    }
	}


  private void handleUpdateSubscriber(String msg) {
	    try {
	        String st = msg.substring(14); // remove "UpdateSubscriber "
	        st = st.substring(1, st.length()-1); // remove []
	        String[] result = st.split(", "); 
	        
	        Subscriber subscriberToUpdate = new Subscriber(
	            Integer.parseInt(result[0].split("=")[1]),    
	            result[1].split("=")[1],                      
	            Integer.parseInt(result[2].split("=")[1]),    
	            result[3].split("=")[1],                      
	            result[4].split("=")[1]                       
	        );
	        
	        DBConnector.updateSubscriber(subscriberToUpdate);
	        System.out.println("Subscriber updated successfully");
	        this.sendToAllClients("Update Successful");
	        
	    } catch (Exception e) {
	        System.out.println("Update failed: " + e.getMessage());
	        this.sendToAllClients("Update Failed");
	        e.printStackTrace();
	    }
	}


  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */

  protected void serverStarted() {
	    System.out.println("Server listening for connections on port " + getPort());
	    try {
	        DBConnector.connect();
	    } catch (Exception e) {
	        System.out.println("Error connecting to database");
	        e.printStackTrace();
	    }
	}

  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()  {
    System.out.println ("Server has stopped listening for connections.");
  }  
}
//End of EchoServer class
