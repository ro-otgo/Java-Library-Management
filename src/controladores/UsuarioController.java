package controladores;
/**
 * Sample Skeleton for 'PantallaUsuario.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
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
import modelos.Usuario;
import repositorios.BibliotecariosSingleton;
import repositorios.UsuariosSingleton;

public class UsuarioController{
	
	private static String NOMBRE_VISTA = "Pantalla usuario";
	
//pasar libros???? creo que no
	public static void mostrarVistaPantallaUsuario(Usuario usuario) throws IOException{
		FXMLLoader loader = new FXMLLoader(UsuarioController.class.getResource("/vistas/PantallaUsuario.fxml"));
		UsuarioController pantallaUsuarioController = new UsuarioController();
		loader.setController(pantallaUsuarioController);
		
		Parent root = loader.load();
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image("/img/logo.jpg"));
		stage.setTitle(NOMBRE_VISTA);
		pantallaUsuarioController.usuarioLabel.setText(usuario.getnombre());
		stage.show();
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="devolucionButton"
    private JFXButton devolucionButton; // Value injected by FXMLLoader

    @FXML // fx:id="reservarButton"
    private JFXButton reservarButton; // Value injected by FXMLLoader

    @FXML // fx:id="detalleLibrosButton"
    private JFXButton detalleLibrosButton; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private JFXButton signOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="usuarioLabel"
    private Label usuarioLabel; // Value injected by FXMLLoader

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
    	System.out.println("Se ha pulsado Sign out");
    	Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	
    	Stage loginStage = new Stage();
    	BibliotecariosSingleton bibliotecariosRepo = BibliotecariosSingleton.getRepoUsuarios();
    	UsuariosSingleton usuariosRepo = UsuariosSingleton.getRepoUsuarios();
    	LoginController.mostrarLogin(loginStage, bibliotecariosRepo.getUsuarios(), usuariosRepo.getUsuarios()); 
    	stage.close();
    }

    @FXML
    void devolucionLibro(ActionEvent event) {

    }

    @FXML
    void reservarLibro(ActionEvent event) {

    }

    @FXML
    void verDetallesLibro(ActionEvent event) {
		try {
			Node source = (Node) event.getSource();
	    	Scene scene = (Scene) source.getScene();
			mostrarVistaListaLibro(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void mostrarVistaListaLibro(Scene scene) throws IOException{
    	LibrosListController.mostarLibrosList(scene);
	}
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert devolucionButton != null : "fx:id=\"devolucionButton\" was not injected: check your FXML file 'PantallaUsuario.fxml'.";
        assert reservarButton != null : "fx:id=\"reservarButton\" was not injected: check your FXML file 'PantallaUsuario.fxml'.";
        assert detalleLibrosButton != null : "fx:id=\"detalleLibrosButton\" was not injected: check your FXML file 'PantallaUsuario.fxml'.";
        assert signOutButton != null : "fx:id=\"signOutButton\" was not injected: check your FXML file 'PantallaUsuario.fxml'.";
        assert usuarioLabel != null : "fx:id=\"usuarioLabel\" was not injected: check your FXML file 'PantallaUsuario.fxml'.";

    }
}
