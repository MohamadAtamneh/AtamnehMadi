package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Scanner;

import logic.Subscriber;

public class DBConnector {



    private static Connection connection = null;
    
	public static void connect() throws Exception 
	{
		 if (connection == null) {
				try{
		            Class.forName("com.mysql.cj.jdbc.Driver");
		          // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab5?useSSL=false&serverTimezone=UTC", "root", "Aa123456");
		            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "Aa123456");

				} catch (Exception e) {
		            e.printStackTrace();
		        }
			}
	}

	public static Subscriber getSubscriber(String SubscriberId) throws SQLException {
	    String query = "SELECT * FROM Subscriber WHERE subscriber_id  = ?";
	    
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setString(1, SubscriberId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {

                return new Subscriber(
                    rs.getInt("subscriber_id"),
                    rs.getString("subscriber_name"),
                    rs.getInt("detailed_subscription_history"),
                    rs.getString("subscriber_phone_number"),
                    rs.getString("subscriber_email")
                );
	        }
	    }
	    return null;
	}


    public static void updateSubscriber(Subscriber subscriber) throws SQLException {
        String query = "UPDATE Subscriber SET subscriber_phone_number=?, subscriber_email=? WHERE subscriber_id=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, subscriber.getSubscriberPhoneNumber());
            pstmt.setString(2, subscriber.getSubscriberEmail());
            pstmt.setInt(3, subscriber.getSubscriberId());
            pstmt.executeUpdate();
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

