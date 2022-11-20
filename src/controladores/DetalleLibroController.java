package controladores;


/**
 * Sample Skeleton for 'DetalleLibro.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetalleLibroController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="tituloLabel"
    private Label tituloLabel; // Value injected by FXMLLoader

    @FXML // fx:id="ISBNLabel"
    private Label ISBNLabel; // Value injected by FXMLLoader

    @FXML // fx:id="reservadoLabel"
    private Label reservadoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="reservarButton"
    private JFXButton reservarButton; // Value injected by FXMLLoader

    @FXML // fx:id="volverButton"
    private JFXButton volverButton; // Value injected by FXMLLoader

    @FXML
    void reservarLibro(ActionEvent event) {
    	reservadoLabel.setText("Reservado");
    	System.out.println("Se ha pulsado reservar libro");
    }

    @FXML
    void volverAtras(ActionEvent event) {
    	System.out.println("Se ha pulsado volver atras");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert tituloLabel != null : "fx:id=\"tituloLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert ISBNLabel != null : "fx:id=\"ISBNLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert reservadoLabel != null : "fx:id=\"reservadoLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert reservarButton != null : "fx:id=\"reservarButton\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert volverButton != null : "fx:id=\"volverButton\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
    	System.out.println("Se ha creado la vista");

    }
}
