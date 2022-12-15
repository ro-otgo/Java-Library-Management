package controladores;

/**
 * Sample Skeleton for 'CrearLibro.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.Libro;
import repositorios.LibreriaSingleton;

public class CrearLibroController {
	
	private static String NOMBRE_VISTA = "Crear Libro";
	
	private Stage stage;
	
	public static void mostrarVistaCrearLibro(Scene scene) {
		try {
			FXMLLoader loader = new FXMLLoader(BibliotecarioController.class.getResource("/vistas/CrearLibro.fxml"));
			CrearLibroController controller = new CrearLibroController();
			loader.setController(controller);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image("/img/logo.jpg"));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(scene.getWindow());
			stage.setTitle(NOMBRE_VISTA);
			controller.stage = stage;
			controller.idInput.setDisable(true);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idInput"
    private TextField idInput; // Value injected by FXMLLoader

    @FXML // fx:id="tituloInput"
    private TextField tituloInput; // Value injected by FXMLLoader

    @FXML // fx:id="isbnInput"
    private TextField isbnInput; // Value injected by FXMLLoader

    @FXML // fx:id="autorInput"
    private TextField autorInput; // Value injected by FXMLLoader

    @FXML // fx:id="reservadoInput"
    private JFXToggleButton reservadoInput; // Value injected by FXMLLoader

    @FXML // fx:id="crearLibro"
    private JFXButton crearLibro; // Value injected by FXMLLoader

    @FXML // fx:id="cancelarButton"
    private JFXButton cancelarButton; // Value injected by FXMLLoader
    
    @FXML
    void cancelar(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void crearLibro(ActionEvent event) {
    	Libro libro = new Libro();
    	libro.setIsbn(isbnInput.getText());
    	libro.setNombre(tituloInput.getText());
    	libro.setAutor(autorInput.getText());
    	libro.setReservado(reservadoInput.isSelected());
    	LibreriaSingleton.getLibreria().addLibro(libro);
    	stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 assert idInput != null : "fx:id=\"idInput\" was not injected: check your FXML file 'CrearLibro.fxml'.";
         assert tituloInput != null : "fx:id=\"tituloInput\" was not injected: check your FXML file 'CrearLibro.fxml'.";
         assert isbnInput != null : "fx:id=\"isbnInput\" was not injected: check your FXML file 'CrearLibro.fxml'.";
         assert autorInput != null : "fx:id=\"autorInput\" was not injected: check your FXML file 'CrearLibro.fxml'.";
         assert reservadoInput != null : "fx:id=\"reservadoInput\" was not injected: check your FXML file 'CrearLibro.fxml'.";
         assert crearLibro != null : "fx:id=\"crearLibro\" was not injected: check your FXML file 'CrearLibro.fxml'.";
         assert cancelarButton != null : "fx:id=\"cancelarButton\" was not injected: check your FXML file 'CrearLibro.fxml'.";

    }
}
