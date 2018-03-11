package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private JFXButton skoda;	
    
//    HomePageController homePage; AnchorPane u;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		
		
		
		
	}

	@FXML
	public void skodaAction(ActionEvent e) {
		
//		System.out.println("Hii");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/FXML/SkodaAuto.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));		
			stage.setTitle("S");
			stage.setX(429);
			stage.setY(129);
			stage.show();			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}    
}
