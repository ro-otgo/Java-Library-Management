package controladores;
/**
 * Sample Skeleton for 'PantallaBibliotecario.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import application.Main;

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
import javafx.stage.Window;
import repositorios.LibreriaSingleton;

public class BibliotecarioController {
	
	public static void mostrarVistaPantallaBibliotecario(String nombreBibliotecario) throws IOException{
		FXMLLoader loaderPantallaBibliotecario = new FXMLLoader(BibliotecarioController.class.getResource("/vistas/PantallaBibliotecario.fxml"));
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

    @FXML // fx:id="devolucionButton"
    private JFXButton devolucionButton; // Value injected by FXMLLoader

    @FXML // fx:id="reservaButton"
    private JFXButton reservaButton; // Value injected by FXMLLoader

    @FXML // fx:id="detalleLibrosButton"
    private JFXButton detalleLibrosButton; // Value injected by FXMLLoader

    @FXML // fx:id="borrarButton"
    private JFXButton borrarButton; // Value injected by FXMLLoader

    @FXML // fx:id="modificarButton"
    private JFXButton modificarButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="signOutButton"
    private JFXButton signOutButton; // Value injected by FXMLLoader
    
    @FXML
    private Label bibliotecarioLabel;

    
    @FXML
    void anadirLibro(ActionEvent event) {

    }

    @FXML
    void borrarLibro(ActionEvent event) {

    }

    // Falta añadir que cierre todas las ventanas abiertas (detalle libros, añadir libro, etc) no solo PantallaBibliotecario
    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
    	System.out.println("Se ha pulsado Sign out");
    	Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	
    	Stage loginStage = new Stage();
    	LoginController.mostrarLogin(loginStage, Main.loadUsers());
    	stage.close();
    }
    
    @FXML
    void devolucionLibro(ActionEvent event) {

    }

    @FXML
    void modificarLibro(ActionEvent event) {

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
        assert anadirButton != null : "fx:id=\"anadirButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
        assert devolucionButton != null : "fx:id=\"devolucionButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
        assert reservaButton != null : "fx:id=\"reservaButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
        assert detalleLibrosButton != null : "fx:id=\"detalleLibrosButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
        assert borrarButton != null : "fx:id=\"borrarButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";
        assert modificarButton != null : "fx:id=\"modificarButton\" was not injected: check your FXML file 'PantallaBibliotecario.fxml'.";

    }
}
