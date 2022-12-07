package application;
	
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
				
	
	// Devuelve el Mapa de bibliotecarios guardados en archivo JSON
	public static Map<String, String> loadUsers () {
		Map<String, String> usuarios = new HashMap<String, String>(); //objeto vacio donde guardar la informacion
		try(Reader reader = new FileReader("listaBibliotecarios.json")){
			Gson gson = new Gson();
			Type tipoListaUsuarios = new TypeToken<Map<String, String>>() {}.getType();
			usuarios = gson.fromJson(reader, tipoListaUsuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

		
	// anade un nuevo usuario al listado guardado en archivo JSON
	public static  void anadirUsuario (Map<String, String> nuevoUsuario) {
		// guardamos en la variable usuarios la informacion del json
		Map<String, String> usuarios = new HashMap<String, String>();
		usuarios = loadUsers();
		// anadimos al mapa el nuevo usuario
		usuarios.putAll(nuevoUsuario);
		// guardamos en el Json el mapa
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		try(FileWriter writer = new FileWriter("listaBibliotecarios.json")){
			gson.toJson(usuarios, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		
	public static void main(String[] args) {
		launch(args);
	}
}
