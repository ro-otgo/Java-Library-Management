package controladores;

/**
 * Sample Skeleton for 'app.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import modelos.Libro;
import repositorios.LibreriaSingleton;

public class LoginController {
	
	public static void mostrarLogin(Stage primaryStage, Map<String,String> usuarios) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/vistas/LoginView.fxml"));
		LoginController mainController = new LoginController();
		fxmlLoader.setController(mainController);
		mainController.setUsuarios(usuarios);

		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root,873,600);
		scene.getStylesheets().add(LoginController.class.getResource("/vistas/styles/application.css").toExternalForm());

		primaryStage.setTitle("Java Library Management");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/img/logo.jpg"));
		primaryStage.show();
	}

	@FXML
	private JFXTextField username;
	@FXML
	private JFXPasswordField password;
	@FXML
	private Label wrongLogIn;	
	
	    
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="detallesLibroButton"
	private JFXButton detallesLibroButton; // Value injected by FXMLLoader
	
	@FXML
    private JFXButton signInButton;
	
	private Map<String,String> usuarios;

	/*
	 * @author Javier
	 * C�digo de prueba para que entre directamente en la vista del bibiliotecario
	 * */
    @FXML
    void signIn(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	
        //Campo vacio
    	if(username.getText().isEmpty() || password.getText().isEmpty()) {
            wrongLogIn.setText("Porfavor introduce tus datos.");
        }else {
        	boolean resultado = validarUsuario(username.getText(),password.getText());
        	if(resultado) {
                wrongLogIn.setText("Success!");
                mostrarVistaPantallaBibliotecario(stage,username.getText().toString());
        	}
            //Contrasena o usuario incorrectos
            else {
                wrongLogIn.setText("Usuario o contrasena incorrectos!");
            }	
        }
    }
    
    private boolean validarUsuario(String username, String password) {
    	String usuarioPwd = usuarios.getOrDefault(username,null);
    	if (usuarioPwd!=null) {
    		return usuarioPwd.equals(password);
    	}
    	return false;
    }
    

	
	private void mostrarVistaPantallaBibliotecario(Stage stage, String nombreBibliotecario) throws IOException{
		BibliotecarioController.mostrarVistaPantallaBibliotecario(nombreBibliotecario);
    	stage.close();
	}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert detallesLibroButton != null
				: "fx:id=\"detallesLibroButton\" was not injected: check your FXML file 'app.fxml'.";

	}

	public void setUsuarios(Map<String, String> loadUsers) {
		this.usuarios = loadUsers;
	}
}
