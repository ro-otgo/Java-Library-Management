package controladores;

/**
 * Sample Skeleton for 'app.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelos.Libro;
import repositorios.LibreriaSingleton;

public class MainController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="detallesLibroButton"
	private JFXButton detallesLibroButton; // Value injected by FXMLLoader

	@FXML
	void verDetallesLibro(ActionEvent event) {
		try {
			System.out.println("Ver detalles libro");
			mostrarVistaListaLibro();
//			mostrarVistaDetallesLibro();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void mostrarVistaListaLibro() throws IOException{
		// Mostrar vista lista libro
		FXMLLoader loaderListaLibros = new FXMLLoader(getClass().getResource("/vistas/LibrosList.fxml"));
		LibrosListController listaLibroController = new LibrosListController(LibreriaSingleton.getLibreria().getLibros());
		loaderListaLibros.setController(listaLibroController);
		Parent root = loaderListaLibros.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(LibrosListController.NOMBRE_VENTANA);
		stage.setMinHeight(LibrosListController.MIN_HEIGHT);
		stage.setMinWidth(LibrosListController.MIN_WIDTH);
		stage.show();
	}

	private void mostrarVistaDetallesLibro() throws IOException {
		// Mostrar vista ver detalles libro
		FXMLLoader loaderDetallesLibro = new FXMLLoader(getClass().getResource("/vistas/DetalleLibro.fxml"));
		DetalleLibroController detallesLibroController = new DetalleLibroController();
		Libro libro = new Libro();
		libro.setIsbn("ABC");
		libro.setNombre("Titulo Libro");
		libro.setReservado(false);
		loaderDetallesLibro.setController(detallesLibroController);
		detallesLibroController.setLibro(libro);
		Parent root = loaderDetallesLibro.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert detallesLibroButton != null
				: "fx:id=\"detallesLibroButton\" was not injected: check your FXML file 'app.fxml'.";

	}
}
