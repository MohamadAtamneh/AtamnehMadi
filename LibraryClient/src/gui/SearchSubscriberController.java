package gui;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchSubscriberController {
    @FXML
    private Button btnExit;
    
    @FXML
    private Button btnSend;
    
    @FXML
    private TextField idtxt;
    
    @FXML
    private Label errorLabel;
    
    private String getID() {
        return idtxt.getText();
    }
    
    public void Search(ActionEvent event) throws Exception {
        String id = getID();
        if(id.trim().isEmpty()) {
            errorLabel.setText("Please enter ID number");
            return;
        }
        
        try {
            ClientUI.chat.accept(id);
            
            if(ChatClient.s1.getSubscriberId() == 0) {
                errorLabel.setText("Subscriber not found in system");
            } else {
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("/gui/SubscriberDetails.fxml").openStream());
                
                SubscriberFormController subscriberFormController = loader.getController();
                subscriberFormController.loadSubscriber(ChatClient.s1);
                
                Scene scene = new Scene(root);
                primaryStage.setTitle("Subscriber Details");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        } catch (Exception e) {
            errorLabel.setText("Database connection error");
        }
    }
    
    public void getExitBtn(ActionEvent event) throws Exception {
        System.out.println("EXIT");
        System.exit(0);
    }
    
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/SearchSubscriber.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("SearchSubscriber");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
