package Controllers;


import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import DBConnection.DBHandler;

public class SignupController implements Initializable  {

    @FXML
    private AnchorPane signUP;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField yourLocation;

    @FXML
    private JFXButton signup;

    @FXML
//    private ToggleGroup genders;
    private Label genders; 
    
    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXRadioButton other;    

    @FXML
    private JFXButton login;

    @FXML
    private ImageView progress;

	
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		System.out.println("SignupController + initialize + 00000");
		progress.setVisible(false);
		name.setStyle("-fx-text-inner-color: #a0a2ab;");
		password.setStyle("-fx-text-inner-color: #a0a2ab;");
		yourLocation.setStyle("-fx-text-inner-color: #a0a2ab;");
		
		
		handler = new DBHandler();
	}
	
	@FXML
	private void signUP(ActionEvent se1) {
		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(ev -> {
			System.out.print("SignUp Succesfully");
		});

		pt.play();		
		
		//Saving data
		String insert = "INSERT INTO youtubers(names, password,gender,location)"
				+ "VALUES (?,?,?,?)";
		
		connection = handler.getConnection();  
		try {
			pst = connection.prepareStatement(insert);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			pst.setString(1, name.getText());
			pst.setString(2, password.getText());
			pst.setString(3, getGender());
			pst.setString(4, yourLocation.getText());
			
			System.out.println("pst.toString():: "+ pst.toString());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void loginAction(ActionEvent se2) throws IOException {
		
		login.getScene().getWindow().hide();		
		Stage login = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
		Scene scene = new Scene(root);
		login.setScene(scene);		
		login.show();
		login.setResizable(false);
	}	
	
	public String getGender() {
		
		String gen = "";
		
		if (male.isSelected()) {
			gen = "Male";
		}
		else if (female.isSelected()) {
			gen = "Female";
		}
		else if (other.isSelected()) {
			gen = "Other";
		}
		
		return gen;
	}
	
}
