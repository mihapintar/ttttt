package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomePageController implements Initializable {

	@FXML private AnchorPane holderPane;
	
	@FXML private 
	AnchorPane anchor;	

    @FXML
    private JFXToolbar toolBar;
	
    @FXML
    private HBox toolBarRight;

    @FXML
    private Label lblMenu;

    @FXML
    private Text welcome;
    
    @FXML
    private VBox overfowContainer;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnExit;
	
	AnchorPane home;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		JFXRippler rippler = new JFXRippler(lblMenu);
		rippler.setMaskType(JFXRippler.RipplerMask.RECT);
		toolBarRight.getChildren().add(rippler);
		
		JFXPopup popup = new JFXPopup(overfowContainer);
		rippler.setOnMouseClicked(e -> popup.show(rippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -1,42) );
		
		//openMenu();
		createPage();

	}

	private void setNode(Node node) 
	{
		
		holderPane.getChildren().clear();
		holderPane.getChildren().add((Node) node);
		
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
		
	}
	
	private void createPage() {
		
		try {
			home = FXMLLoader.load(getClass().getResource("/FXML/HOME.fxml"));			
			setNode(home);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void homeBtn(ActionEvent he) {
		setNode(home);
	}
  
	
    @FXML
    void exit(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void logout(ActionEvent event1) {
    	System.out.println("0");
    	anchor.getScene().getWindow().hide();
    	
    	System.out.println("1");
    	
		Stage login = new Stage();
		Parent root;
		System.out.println("2");
		try {
			root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
			System.out.println("3");
			Scene scene = new Scene(root);
			login.setScene(scene);
			System.out.println("4");
			login.show();
			login.setResizable(false);    			
		} catch (IOException e) {
			System.out.println("5");
			e.printStackTrace();
		}
	
		System.out.println("6");
    }
    
	
	
}
