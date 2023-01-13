package controladores;


/**
 * Sample Skeleton for 'DetalleLibro.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	public static void mostrarVistaDetallesLibro(Libro libro, Control control) throws IOException {
		// Mostrar vista ver detalles libro
		FXMLLoader loaderDetallesLibro = new FXMLLoader(DetalleLibroController.class.getResource("/vistas/DetalleLibro.fxml"));
		DetalleLibroController detallesLibroController = new DetalleLibroController();
		loaderDetallesLibro.setController(detallesLibroController);
		detallesLibroController.setLibro(libro);
		Parent root = loaderDetallesLibro.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(control.getScene().getWindow());
		stage.setTitle(DetalleLibroController.NOMBRE_VENTANA);
		stage.setMinHeight(DetalleLibroController.MIN_HEIGHT);
		stage.setMinWidth(DetalleLibroController.MIN_WIDTH);
		stage.getIcons().add(new Image("/img/logo.jpg"));
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
    	boolean reservado = libro.isReservado();
    	if (!reservado) {
        	if (usuario != null) {
            	ReservaSingleton.getReservaSingleton().crearReserva(libro, usuario);
        	}
        	reservadoLabel.setText(TEXT_RESERVADO);
    	}else {
    		System.out.println("Comprobando reservas");
    		List<Reserva> reservas = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorUsuarioLibro(usuario,libro);
    		if (!reservas.isEmpty()) {
    			Reserva reserva = reservas.get(0);
    			reserva.setActive(false);
        		reservadoLabel.setText(TEXT_DISPONIBLE);
    		}
    		else {
        		System.out.println("El libro esta reservado.");
    		}
    	}
    	System.out.println("Se ha pulsado reservar libro");
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
    		idLabel.setText(String.valueOf(libro.getId()));
    		tituloLabel.setText(libro.getTitulo());
    		autorLabel.setText(libro.getAutor());
    		ISBNLabel.setText(libro.getIsbn());
    		if (libro.isReservado()) {
        		reservadoLabel.setText(TEXT_RESERVADO);
    		}else {
        		reservadoLabel.setText(TEXT_DISPONIBLE);
    		}
    	}
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
    
}
