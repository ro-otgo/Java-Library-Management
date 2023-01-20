package controladores.reservas;
/**
 * Sample Skeleton for 'ReservaCell.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;


import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import modelos.Reserva;

public class ReservaListaCell extends ListCell<Reserva> {

	private Reserva reserva;
	
    public ReservaListaCell() {
		loadFXML();
	}
    
	/**
	 * Cargar xml con la vista
	 */
	private void loadFXML() {
		try {
			FXMLLoader cellLoader = new FXMLLoader(getClass().getResource("/vistas/ReservaCell.fxml"));
			cellLoader.setController(this);
			cellLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualizar vista con los datos de la reserva
	 */
	private void updateContent() {
		System.out.println(reserva);
		reservaID.setText("Reserva ID: " + reserva.getId());
		usuarioID.setText("Usuario ID: " + reserva.getIdUsuario());
		libroID.setText("Libro ID: " + reserva.getIdLibro());
		setText(null);
		setGraphic(cellId);
	}

	@Override
	protected void updateItem(Reserva item, boolean empty) {
		super.updateItem(item, empty);
		if (empty || item == null) {
			System.out.println(">Contenido vacio");
			clear();
		} else {
			System.out.println(">Contenido no vacio");
			this.reserva = item;
			updateContent();
		}
	}

	/**
	 * Limpiar la celda
	 */
	private void clear() {
		setText(null);
		setGraphic(null);
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="cellId"
	private AnchorPane cellId; // Value injected by FXMLLoader

	@FXML // fx:id="reservaID"
	private Label reservaID; // Value injected by FXMLLoader

	@FXML // fx:id="usuarioID"
	private Label usuarioID; // Value injected by FXMLLoader

	@FXML // fx:id="libroID"
	private Label libroID; // Value injected by FXMLLoader

	@FXML // fx:id="detalleButton"
	private JFXButton detalleButton; // Value injected by FXMLLoader

	@FXML
	void detalleReserva(ActionEvent event) {
		System.out.println("Se ha pulsado ver detalle reserva");
		ReservaDetalleController.mostrarReservaDetalle(reserva, this);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert cellId != null : "fx:id=\"cellId\" was not injected: check your FXML file 'ReservaCell.fxml'.";
		assert reservaID != null : "fx:id=\"reservaID\" was not injected: check your FXML file 'ReservaCell.fxml'.";
		assert usuarioID != null : "fx:id=\"usuarioID\" was not injected: check your FXML file 'ReservaCell.fxml'.";
		assert libroID != null : "fx:id=\"libroID\" was not injected: check your FXML file 'ReservaCell.fxml'.";
		assert detalleButton != null
				: "fx:id=\"detalleButton\" was not injected: check your FXML file 'ReservaCell.fxml'.";

	}

}
