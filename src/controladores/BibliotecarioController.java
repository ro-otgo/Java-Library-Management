package controladores;

/**
 * Sample Skeleton for 'PantallaBibliotecario.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import controladores.reservas.ReservaListController;

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
import repositorios.BibliotecariosSingleton;
import repositorios.UsuariosSingleton;

public class BibliotecarioController {

	public static void mostrarVistaPantallaBibliotecario(String nombreBibliotecario) throws IOException {
		FXMLLoader loaderPantallaBibliotecario = new FXMLLoader(
				BibliotecarioController.class.getResource("/vistas/PantallaBibliotecario.fxml"));
		BibliotecarioController pantallaBibliotecarioController = new BibliotecarioController();
		loaderPantallaBibliotecario.setController(pantallaBibliotecarioController);
		Parent root = loaderPantallaBibliotecario.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image("/img/logo.jpg"));
		pantallaBibliotecarioController.bibliotecarioLabel.setText(nombreBibliotecario);
		stage.show();
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="anadirButton"
	private JFXButton anadirButton; // Value injected by FXMLLoader

	@FXML // fx:id="verReservaButton"
	private JFXButton verReservaButton; // Value injected by FXMLLoader

	@FXML // fx:id="detalleLibrosButton"
	private JFXButton detalleLibrosButton; // Value injected by FXMLLoader

	@FXML // fx:id="devolverButton"
	private JFXButton devolverButton; // Value injected by FXMLLoader

	@FXML // fx:id="modificarButton"
	private JFXButton modificarButton; // Value injected by FXMLLoader

	@FXML // fx:id="signOutButton"
	private JFXButton signOutButton; // Value injected by FXMLLoader

	@FXML
	private Label bibliotecarioLabel;

	@FXML
	void anadirLibro(ActionEvent event) {
		Node source = (Node) event.getSource();
		Scene scene = (Scene) source.getScene();
		CrearLibroController.mostrarVistaCrearLibro(scene);
	}

	@FXML
	void devolverButton(ActionEvent event) {
		try {
			System.out.println("Se ha pulsado ver devolverButton");
			Node source = (Node) event.getSource();
			Scene scene = (Scene) source.getScene();
			devolverLibros(scene);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	void modificarLibro(ActionEvent event) {
		System.out.println("Se ha pulsado ver modificarLibro");
		try {
			Node source = (Node) event.getSource();
			Scene scene = (Scene) source.getScene();
			mostrarVistaListaLibro(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@FXML
	void verReservas(ActionEvent event) {
		System.out.println("Se ha pulsado ver reservas");
		try {
			Node source = (Node) event.getSource();
			Scene scene = (Scene) source.getScene();
			mostrarReservas(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private void devolverLibros(Scene scene) throws IOException {
		ReservaListController.mostrarListaReservasActivas(scene);
	}

	private void mostrarReservas(Scene scene) throws IOException {
		ReservaListController.mostrarListaReservas(scene);
	}

	private void mostrarVistaListaLibro(Scene scene) throws IOException {
		LibrosListController.mostarLibrosList(scene);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert anadirButton != null
				: "fx:id=\"anadirButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
		assert verReservaButton != null
				: "fx:id=\"verReservaButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
		assert detalleLibrosButton != null
				: "fx:id=\"detalleLibrosButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
		assert devolverButton != null
				: "fx:id=\"devolverButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
		assert modificarButton != null
				: "fx:id=\"modificarButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
		assert signOutButton != null
				: "fx:id=\"signOutButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
		assert bibliotecarioLabel != null
				: "fx:id=\"bibliotecarioLabel\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";

	}
}
