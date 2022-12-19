package application;
	
import controladores.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import repositorios.BibliotecariosSingleton;
import repositorios.UsuariosSingleton;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BibliotecariosSingleton repoBibliotecarios = BibliotecariosSingleton.getRepoUsuarios();
			UsuariosSingleton repoUsuarios = UsuariosSingleton.getRepoUsuarios();
			LoginController.mostrarLogin(primaryStage, repoBibliotecarios.getUsuarios(), repoUsuarios.getUsuarios());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
				
	public static void main(String[] args) {
		launch(args);
	}
}
