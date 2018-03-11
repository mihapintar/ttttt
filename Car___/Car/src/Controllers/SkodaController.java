package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SkodaController implements Initializable {

    @FXML
    private JFXButton back;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//back.setText(null);
	}

    @FXML
    void backAction(ActionEvent event) {
    	back.getScene().getWindow().hide();
    }    
}
