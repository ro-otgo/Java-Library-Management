package application;
	

import java.util.List;

import config.AppConfiguration;
import controladores.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import modelos.Usuario;
import repositorios.BibliotecariosSingleton;
import repositorios.LibreriaSingleton;
import repositorios.ReservaSingleton;
import repositorios.UsuariosSingleton;


public class Main extends Application {

	@Override
	public void stop() throws Exception {
		System.out.println("Cerrando la aplicacion");
		almacenarFicheros();
		System.out.println("Ficheros almacenados.");
		super.stop();
	}

	private void almacenarFicheros() {
		System.out.println("Almacenar archivos de configuracion de la aplicacion");
		AppConfiguration.getConfiguration().almacenarAjustes();
		BibliotecariosSingleton.getRepoUsuarios().escribirUsuarios();
		LibreriaSingleton.getLibreria().escribirLibros();
		ReservaSingleton.getReservaSingleton().escribirReservas();
		UsuariosSingleton.getRepoUsuarios().escribirUsuarios();
		System.out.println("-----------------------------------------------------");
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			BibliotecariosSingleton repoBibliotecarios = BibliotecariosSingleton.getRepoUsuarios();
			UsuariosSingleton repoUsuarios = UsuariosSingleton.getRepoUsuarios();
			List<Usuario> usuarios = repoUsuarios.getUsuarios();
			LoginController.mostrarLogin(primaryStage, repoBibliotecarios.getUsuarios(), usuarios);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
				
	public static void main(String[] args) {
		launch(args);
	}
}
