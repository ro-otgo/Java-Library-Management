package application;
	
import java.util.HashMap;
import java.util.Map;

import controladores.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			LoginController.mostrarLogin(primaryStage, loadUsers());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> loadUsers() {
		Map<String, String> usuarios = new HashMap<String,String>();
		usuarios.put("Angel","123");
		usuarios.put("Javier","123");
		usuarios.put("Rodrigo","123");
		return usuarios;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
