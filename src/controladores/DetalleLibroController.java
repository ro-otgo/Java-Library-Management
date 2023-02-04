package controladores;


/**
 * Sample Skeleton for 'DetalleLibro.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import modelos.Libro;
import modelos.Reserva;
import modelos.Usuario;
import repositorios.ReservaSingleton;
import repositorios.SesionSingleton;

/**
 * Detalles libros
 * @author Rodrigo
 *
 */
public class DetalleLibroController {
	
	/**
	 * Mostrar vista detalles del libro seleccionado
	 * @throws IOException
	 */
	public static void mostrarVistaDetallesLibro(Libro libro, Scene escenaListadoLibrosScene, Window ventanaUsuarioWindow) throws IOException {
		// Mostrar vista ver detalles libro
		String vistaPath = "/vistas/DetalleLibro.fxml";
		FXMLLoader loaderDetallesLibro = new FXMLLoader(DetalleLibroController.class.getResource(vistaPath));
		DetalleLibroController detallesLibroController = new DetalleLibroController();
		loaderDetallesLibro.setController(detallesLibroController);
		detallesLibroController.setLibro(libro);
		Parent root = loaderDetallesLibro.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(ventanaUsuarioWindow);
		stage.setTitle(DetalleLibroController.NOMBRE_VENTANA);
		stage.setMinHeight(DetalleLibroController.MIN_HEIGHT);
		stage.setMinWidth(DetalleLibroController.MIN_WIDTH);
		stage.getIcons().add(new Image("/img/logo.jpg"));
		// Cerrar vista listo libros
		Window windowListaLibros = escenaListadoLibrosScene.getWindow();
		Stage stageListaLibros = (Stage) windowListaLibros;
		stageListaLibros.close();
		// Mostrar  vista detalles libros
		stage.show();
	}
	
	// Constante tamano: Ancho
	public static final double MIN_WIDTH= 500;
	// Constante tamano: Largo
	public static final double MIN_HEIGHT= 500;
	// Nombre vista
	public static final String NOMBRE_VENTANA = "Detalles Libro";
	
	// Constante estado reserva libro
	private static final String TEXT_RESERVADO = "Reservado";
	private static final String TEXT_RESERVA_LIBRO = "Tiene reservado este libro";
	private static final String TEXT_DISPONIBLE = "Disponible";
	
	private Libro libro;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idLabel"
    private Label idLabel; // Value injected by FXMLLoader

    @FXML // fx:id="tituloLabel"
    private Label tituloLabel; // Value injected by FXMLLoader
    
    @FXML // fx:id="autorLabel"
    private Label autorLabel; // Value injected by FXMLLoader

    @FXML // fx:id="ISBNLabel"
    private Label ISBNLabel; // Value injected by FXMLLoader

    @FXML // fx:id="reservadoLabel"
    private Label reservadoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="reservarButton"
    private JFXButton reservarButton; // Value injected by FXMLLoader

    @FXML // fx:id="volverButton"
    private JFXButton volverButton; // Value injected by FXMLLoader


    /**
     * Reserva de libro
     * @param event
     */
    @FXML
    void reservarLibro(ActionEvent event) {
    	Usuario usuario = SesionSingleton.getSesionSingleton().obtenerUsuarioActual();
		// Comprobar que no es un bibliotecario
    	if (usuario==null) {
    		// Biblitoecario no puede realizar reservas
    		System.out.println("No se puede reservar libros con esta cuenta.");
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Se ha producido un error");
    		alert.setContentText("El usuario actual no puede reservar ningun libro.");
    		Node source = (Node) event.getSource();
    		alert.initOwner(source.getScene().getWindow()); 
    		alert.showAndWait();
    		return;
    	}
    	// Comprobar si el libro esta reservado
    	boolean reservado = libro.isReservado();
    	if (!reservado) {
    		// Crear la reserva
        	ReservaSingleton.getReservaSingleton().crearReserva(libro, usuario);
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Informacion");
    		alert.setHeaderText("Actualizacion reserva");
    		alert.setContentText("Se ha realizado la reserva del libro.");
    		Node source = (Node) event.getSource();
    		alert.initOwner(source.getScene().getWindow()); 
    		alert.showAndWait();
        	reservadoLabel.setText(TEXT_RESERVA_LIBRO);
    	}else {
    		System.out.println("Comprobando reservas");
    		Optional<Reserva> reservaOpt = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorUsuarioLibro(usuario,libro);
    		if (reservaOpt.isPresent()) {
    			// El usuario actual esta devolviendo el libro.
    			Reserva reserva = reservaOpt.get();
    			reserva.setActive(false);
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Informacion");
        		alert.setHeaderText("Actualizacion reserva");
        		alert.setContentText("Se ha devuelto el libro.");
        		Node source = (Node) event.getSource();
        		alert.initOwner(source.getScene().getWindow()); 
        		alert.showAndWait();
        		reservadoLabel.setText(TEXT_DISPONIBLE);
    		}
    		else {
    			// El usuario actual esta intentando reservar un libro que ya lo itene otro usuario.
    			Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Error");
        		alert.setHeaderText("Se ha producido un error");
        		alert.setContentText("El libro ya esta reservado.");
        		Node source = (Node) event.getSource();
        		alert.initOwner(source.getScene().getWindow()); 
        		alert.showAndWait();
        		System.out.println("El libro ya esta reservado.");
    		}
    	}
    }

    /**
     * Boton para salir de la vista detalle
     * @param event
     */
    @FXML
    void volverAtras(ActionEvent event) {
    	Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    /**
     * Inicializacion
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert tituloLabel != null : "fx:id=\"tituloLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert autorLabel != null : "fx:id=\"autorLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert ISBNLabel != null : "fx:id=\"ISBNLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert reservadoLabel != null : "fx:id=\"reservadoLabel\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert reservarButton != null : "fx:id=\"reservarButton\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
        assert volverButton != null : "fx:id=\"volverButton\" was not injected: check your FXML file 'DetalleLibro.fxml'.";
    	populateVista();
    	System.out.println("Se ha creado la vista");
    }

    /**
     * Cargar datos del libro en la vista
     */
	private void populateVista() {
		if (libro!=null) {
			populateVistaUsuario();
    	}
	}

	private void populateVistaUsuario() {
		idLabel.setText(String.valueOf(libro.getId()));
		tituloLabel.setText(libro.getTitulo());
		autorLabel.setText(libro.getAutor());
		ISBNLabel.setText(libro.getIsbn());
		if (libro.isReservado()) {
			reservadoLabel.setText(TEXT_RESERVADO);
			Usuario usuarioActual = SesionSingleton.getSesionSingleton().obtenerUsuarioActual();
			Optional<Reserva> reserva = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorUsuarioLibro(usuarioActual, libro);
			if(reserva.isPresent()) {
				reservadoLabel.setText(TEXT_RESERVA_LIBRO);
			}
		}else {
			reservadoLabel.setText(TEXT_DISPONIBLE);
		}
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
    
}
