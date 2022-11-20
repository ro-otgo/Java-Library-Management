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
			// Mostrar vista ver detalles libro
			FXMLLoader loaderDetallesLibro = new FXMLLoader(getClass().getResource("/vistas/DetalleLibro.fxml"));
			DetalleLibroController detallesLibroController = new DetalleLibroController();
			loaderDetallesLibro.setController(detallesLibroController);
			Parent root = loaderDetallesLibro.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert detallesLibroButton != null
				: "fx:id=\"detallesLibroButton\" was not injected: check your FXML file 'app.fxml'.";

	}
}
