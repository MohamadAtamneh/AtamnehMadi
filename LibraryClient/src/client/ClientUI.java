package client;
import javafx.application.Application;

import javafx.stage.Stage;

import java.util.Vector;
import gui.SearchSubscriberController;
import client.ClientController;

public class ClientUI extends Application {
	public static ClientController chat; //only one instance

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		 chat= new ClientController("localhost",4444);
		// TODO Auto-generated method stub
						  		
		 SearchSubscriberController aFrame = new SearchSubscriberController(); // create StudentFrame
		 
		aFrame.start(primaryStage);
	}
	
	
}
