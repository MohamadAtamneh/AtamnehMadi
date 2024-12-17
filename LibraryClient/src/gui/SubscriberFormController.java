package gui;

import java.net.URL;
import java.util.ResourceBundle;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Subscriber;

public class SubscriberFormController implements Initializable {
    private Subscriber subscriber;
        
    @FXML
    private Label lblId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblEmail;
    
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;

    @FXML
    public void handleSave(ActionEvent event) {
        subscriber.setSubscriberPhoneNumber(txtPhone.getText());
        subscriber.setSubscriberEmail(txtEmail.getText());
        ClientUI.chat.accept("UpdateSubscriber " + subscriber.toString());
    }

    @FXML
    public void handleClose(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            stage = null;
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
        this.txtId.setText(String.valueOf(subscriber.getSubscriberId()));
        this.txtName.setText(subscriber.getSubscriberName());
        this.txtPhone.setText(subscriber.getSubscriberPhoneNumber());
        this.txtEmail.setText(subscriber.getSubscriberEmail());
        
        // dont allow to edit this fielde
        this.txtId.setEditable(false);
        this.txtName.setEditable(false);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
