package controladores.reservas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.Libro;
import modelos.Reserva;
import modelos.Usuario;
import repositorios.LibreriaSingleton;
import repositorios.ReservaSingleton;
import repositorios.SesionSingleton;
import repositorios.UsuariosSingleton;

/**
 * Sample Skeleton for 'DetalleReserva.fxml' Controller Class
 */
public class ReservaDetalleController {

	private static String NOMBRE_VISTA = "Reserva";
	// Constante tamano: Ancho
	public static final double MIN_WIDTH= 500;
	// Constante tamano: Largo
	public static final double MIN_HEIGHT= 500;

	public static void mostrarReservaDetalle(Reserva reserva, Control control) {
		try {
			FXMLLoader loader = new FXMLLoader(
					ReservaDetalleController.class.getResource("/vistas/DetalleReserva.fxml"));
			ReservaDetalleController controller = new ReservaDetalleController();
			controller.setReserva(reserva);
			loader.setController(controller);
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image("/img/logo.jpg"));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(control.getScene().getWindow());
			stage.setMinHeight(MIN_HEIGHT);
			stage.setMinWidth(MIN_WIDTH);
			stage.setTitle(NOMBRE_VISTA);
			controller.idReserva.setDisable(true);
			Usuario usuarioActual = SesionSingleton.getSesionSingleton().obtenerUsuarioActual();
			if(usuarioActual!=null) {
				// Es un usuario del tipo normal: Desactivar campos
				controller.idLibro.setDisable(true);
				controller.idUsuario.setDisable(true);
				controller.fechaInicioReserva.setDisable(true);
				controller.fechaFinReserva.setDisable(true);
				controller.borrarId.setDisable(true);
				controller.borrarId.setVisible(false);
			}

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Reserva reserva;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="idReserva"
	private JFXTextField idReserva; // Value injected by FXMLLoader

	@FXML // fx:id="idUsuario"
	private JFXTextField idUsuario; // Value injected by FXMLLoader

	@FXML // fx:id="idLibro"
	private JFXTextField idLibro; // Value injected by FXMLLoader

	@FXML // fx:id="isActive"
	private JFXToggleButton isActive; // Value injected by FXMLLoader

	@FXML // fx:id="fechaInicioReserva"
	private DatePicker fechaInicioReserva; // Value injected by FXMLLoader

	@FXML // fx:id="fechaFinReserva"
	private DatePicker fechaFinReserva; // Value injected by FXMLLoader

	@FXML // fx:id="volverButton"
	private JFXButton volverButton; // Value injected by FXMLLoader

	@FXML // fx:id="modificarButton"
	private JFXButton modificarButton; // Value injected by FXMLLoader

	@FXML // fx:id="borrarId"
	private JFXButton borrarId; // Value injected by FXMLLoader

	@FXML
	void borrar(ActionEvent event) {
		reserva.setActive(false);
		isActive.setSelected(false);
		Notifications.create()
	        .title("Actualizacion")
	        .text("Se ha actualizado la reserva")
	        .showInformation();
	}

	private boolean validarFechaReserva() {
		// Validar fechas
		LocalDate finReserva = fechaFinReserva.getValue();
		LocalDate inicioReserva = fechaInicioReserva.getValue();
		return inicioReserva.isBefore(finReserva);
	}

	/**
	 * Validar reserva: Solo se podra activar si el libro al que hace referencia
	 * no tiene ninguna otra reserva.
	 * Para desactivar no hay ninguna restriccion
	 * @return
	 */
	private boolean validarReserva() {
		boolean reservaValida = true;
//		Optional<Libro> optLibro = LibreriaSingleton.getLibreria().buscarLibroPorId(reserva.getIdLibro());
		Optional<Libro> optLibro = LibreriaSingleton.getLibreria().buscarLibroPorId(Long.parseLong(idLibro.getText()));
		if (!optLibro.isPresent()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("El libro al que hace referencia no existe.");
			Scene scene = modificarButton.getScene();
			alert.initOwner(scene.getWindow());
			alert.showAndWait();
			idLibro.setText("" + reserva.getIdLibro());
			reservaValida = false;
		} else {
			if (isActive.isSelected() && !reserva.isActive()) {
				// Se esta intentando activar una reserva desactivada.
				// Se comprueban las reserevas activas del libro (Nota: Solo deberia existir una
				// reserva)
				// Recuperar libro
				// Buscar la reserva activa del libro
				if (optLibro.isPresent()) {
					Libro libro = optLibro.get();
					List<Reserva> reservasLibro = ReservaSingleton.getReservaSingleton()
							.buscarReservaActivaPorLibro(libro);
					if (!reservasLibro.isEmpty()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Se ha producido un error");
						alert.setContentText("El libro al que hace referencia ya tiene una reserva activa.");
						Scene scene = modificarButton.getScene();
						alert.initOwner(scene.getWindow());
						alert.showAndWait();
						isActive.setSelected(false);
						reservaValida = false;
						System.out
								.println("No se ha podido reservar el libro, ya hay una reserva activa de este libro");
						reservaValida = false;
					} else {
						System.out.println("Se puede activar de nuevo la reserva.");
						reservaValida = true;
					}

				} else {
					System.err.println("Se ha producido un error en los datos.");
					System.err.println("Impossible. Perhaps the Archives Are Incomplete");
					System.err.println("Error: No se ha podido recuperar el libro.");
					reservaValida = false;
				}

			}

		}
		return reservaValida;
	}
	
	private boolean validarUsuario() {
		String usuarioId = idUsuario.getText();
		Optional<Usuario> usuario = UsuariosSingleton.getRepoUsuarios().findUsuarioById(usuarioId);
		boolean resultado = usuario.isPresent();
		if(!resultado) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("El usuario que ha introducido no existe.");
			Scene scene = modificarButton.getScene();
			alert.initOwner(scene.getWindow()); 
			alert.showAndWait();
			idUsuario.setText(reserva.getIdUsuario());
		}
		return resultado;
	}

	private boolean validarModificacion() {
		return validarFechaReserva() && validarReserva() &&  validarUsuario();
	}
	
	private void actualizarCampos() {
		reserva.setActive(isActive.isSelected());
		reserva.setFechaFinReserva(fechaFinReserva.getValue());
		reserva.setFechaInicioReserva(fechaInicioReserva.getValue());
		reserva.setIdLibro(Long.parseLong(idLibro.getText()));
		reserva.setIdUsuario(idUsuario.getText());
	}

	@FXML
	void modificar(ActionEvent event) {
		System.out.println("Se ha pulsado modificar");
		if (validarModificacion()) {
			actualizarCampos();
			Notifications.create()
				.title("Actualizacion")
				.text("Se ha actualizado la reserva")
				.showInformation();
			System.out.println("Actualizando reserva");
		} else {
			System.out.println("No se ha podido actualizar la reserva");
		}
	}

	@FXML
	void volverAtras(ActionEvent event) {
    	Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert idReserva != null : "fx:id=\"idReserva\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert idUsuario != null : "fx:id=\"idUsuario\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert idLibro != null : "fx:id=\"idLibro\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert isActive != null : "fx:id=\"isActive\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert fechaInicioReserva != null
				: "fx:id=\"fechaInicioReserva\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert fechaFinReserva != null
				: "fx:id=\"fechaFinReserva\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert volverButton != null
				: "fx:id=\"volverButton\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert modificarButton != null
				: "fx:id=\"modificarButton\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		assert borrarId != null : "fx:id=\"borrarId\" was not injected: check your FXML file 'DetalleReserva.fxml'.";
		populateVista();
		
		// metodo que limita los valores que se pueden introducir para el campor idlibro
		idLibro.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// https://stackoverflow.com/a/19099928/8873596 
				if (newValue.matches("\\d*")) {
			            Long value = Long.parseLong(newValue);
			        } else {
			        	idLibro.setText(oldValue);
			        }				
			}
		});
		System.out.println("Se ha creado la vista");

	}

	private void populateVista() {
		idReserva.setText("" + reserva.getId());
		idUsuario.setText(reserva.getIdUsuario());
		idLibro.setText("" + reserva.getIdLibro());
		isActive.setSelected(reserva.isActive());
		fechaInicioReserva.setValue(reserva.getFechaInicioReserva());
		fechaFinReserva.setValue(reserva.getFechaFinReserva());
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
