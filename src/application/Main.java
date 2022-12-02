package application;
	
import java.util.HashMap;
import java.util.Map;

import controladores.MainController;
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
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/login.fxml"));
			MainController mainController = new MainController();
			fxmlLoader.setController(mainController);
			mainController.setUsuarios(loadUsers());

			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root,873,600);
			scene.getStylesheets().add(getClass().getResource("/vistas/styles/application.css").toExternalForm());

			primaryStage.setTitle("Java Library Management");
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/img/logo.jpg"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String, String> loadUsers() {
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
