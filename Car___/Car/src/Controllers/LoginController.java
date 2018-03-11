package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import DBConnection.DBHandler;

public class LoginController implements Initializable {

	@FXML
	private AnchorPane logingMain;

	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXButton signup;

	@FXML
	private JFXButton login;

	@FXML
	private JFXCheckBox remember;

	@FXML
	private JFXButton forgotpassword;

	@FXML
	private ImageView progress;

	private Connection connection;
	private DBHandler handler;
	private PreparedStatement pst;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progress.setVisible(false);
		username.setStyle("-fx-text-inner-color: #a0a2ab;");
		password.setStyle("-fx-text-inner-color: #a0a2ab;");

		handler = new DBHandler();
	}

	@FXML
	private void loginAction(ActionEvent e) {
		progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(ev -> {
			// Retrive data from DB
			connection = handler.getConnection();
			String q1 = "SELECT * from youtubers where names=? and password=?";

			try {
				pst = connection.prepareStatement(q1);

				pst.setString(1, username.getText());
				pst.setString(2, password.getText());
				ResultSet rs = pst.executeQuery();

				int count = 0;
				while (rs.next()) {
					count = count + 1;
					// count=+1;
				}

				if (count == 1) {
					System.out.print("Login Succesfull");
					login.getScene().getWindow().hide();

					Stage home = new Stage();
					try {
						Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
						Scene scene = new Scene(root);
						home.setScene(scene);
						home.show();

					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else {
					// System.out.print("Username and Password is not Correct!");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Username and Password Is Not Correct!");
					alert.show();
					progress.setVisible(false);

				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		pt.play();
		/*
		 * // Retrive data from DB connection = handler.getConnection(); String q1 =
		 * "SELECT * from youtubers where names=? and password=?";
		 * 
		 * try { pst = connection.prepareStatement(q1);
		 * 
		 * pst.setString(1, username.getText()); pst.setString(2, password.getText());
		 * ResultSet rs = pst.executeQuery();
		 * 
		 * int count = 0; while (rs.next()) { count = count + 1; // count=+1; }
		 * 
		 * if (count == 1) { System.out.print("Login Succesfull");
		 * login.getScene().getWindow().hide();
		 * 
		 * Stage home = new Stage(); try { Parent root =
		 * FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml")); Scene scene =
		 * new Scene(root); home.setScene(scene); home.show();
		 * 
		 * } catch (IOException e1) {
		 * 
		 * e1.printStackTrace(); }
		 * 
		 * } else { // System.out.print("Username and Password is not Correct!"); Alert
		 * alert = new Alert(Alert.AlertType.ERROR); alert.setHeaderText(null);
		 * alert.setContentText("Username and Password Is Not Correct!"); alert.show();
		 * progress.setVisible(false);
		 * 
		 * }
		 * 
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } finally { try { connection.close(); } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * } }
		 */
	}

	@FXML
	public void signUp(ActionEvent e1) throws IOException {

		login.getScene().getWindow().hide();
		Stage signup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUP.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		signup.setResizable(false);
	}

}