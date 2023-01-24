package controladores;


/**
 * Sample Skeleton for 'DetalleLibro.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.Libro;
import modelos.Reserva;
import modelos.Usuario;
import repositorios.LibreriaSingleton;
import repositorios.ReservaSingleton;
import repositorios.SesionSingleton;

/**
 * Detalles libros
 * @author Rodrigo
 *
 */
public class DetalleLibroBibliotecarioController {
	
	/**
	 * Mostrar vista detalles del libro seleccionado
	 * @throws IOException
	 */
	public static void mostrarVistaDetallesLibroBibliotecario(Libro libro, Scene scene) throws IOException {
		// Mostrar vista ver detalles libro
		String vistaPath = "/vistas/DetalleLibroBibliotecario.fxml";
		FXMLLoader loaderDetallesLibro = new FXMLLoader(DetalleLibroBibliotecarioController.class.getResource(vistaPath));
		DetalleLibroBibliotecarioController detallesLibroController = new DetalleLibroBibliotecarioController();
		loaderDetallesLibro.setController(detallesLibroController);
		detallesLibroController.setLibro(libro);
		Parent root = loaderDetallesLibro.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(scene.getWindow());
		stage.setTitle(DetalleLibroBibliotecarioController.NOMBRE_VENTANA);
		stage.setMinHeight(DetalleLibroBibliotecarioController.MIN_HEIGHT);
		stage.setMinWidth(DetalleLibroBibliotecarioController.MIN_WIDTH);
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
    

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

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

    
    @FXML // fx:id="idLabel"
    private Label idLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="tituloLabel1"
    private JFXTextField tituloLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="autorLabel1"
    private JFXTextField autorLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="ISBNLabel1"
    private JFXTextField ISBNLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="reservadoLabel"
    private Label reservadoLabel1; // Value injected by FXMLLoader
    
    @FXML // fx:id="modificarButton"
    private JFXButton modificarButton; // Value injected by FXMLLoader

    @FXML // fx:id="borrarButton1"
    private JFXButton borrarButton1; // Value injected by FXMLLoader

    
    @FXML
    void borrarButton(ActionEvent event) {
		Node source = (Node) event.getSource();
    	if(libro.isReservado()) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Se ha producido un error");
    		alert.setContentText("No se puede borrar un libro reservado.");
    		alert.initOwner(source.getScene().getWindow()); 
    		alert.showAndWait();
    		return;
    	}
//    	https://code.makery.ch/blog/javafx-dialogs-official/
    	Alert confirmacionAlert = new Alert(AlertType.CONFIRMATION);
    	confirmacionAlert.setTitle("Borrar libro");
    	confirmacionAlert.setHeaderText("Se va a proceder a eliminar el libro seleccionado");
    	confirmacionAlert.setContentText("¿Esta seguro de borrar el libro?");

    	Optional<ButtonType> result = confirmacionAlert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		LibreriaSingleton.getLibreria().removeLibro(libro);
    		Notifications.create().title("Actualizacion libro").text("Se ha eliminado el libro").showInformation();
    		Stage stage = (Stage) source.getScene().getWindow();
        	stage.close();    		
    	} else {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Informacion");
    		alert.setHeaderText("Informacion");
    		alert.setContentText("No se ha borrado el libro");
    		alert.initOwner(source.getScene().getWindow()); 
    		alert.show();
    	}
    }

    @FXML
    void modificar(ActionEvent event) {
    	if(comprobarCampos()) {
    		libro.setAutor(autorLabel1.getText());
    		libro.setIsbn(ISBNLabel1.getText());
    		libro.setTitulo(tituloLabel1.getText());
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Informacion");
    		alert.setHeaderText("Actualizacion libro");
    		alert.setContentText("Se ha actualizado el libro.");
    		Node source = (Node) event.getSource();
    		alert.initOwner(source.getScene().getWindow()); 
    		alert.showAndWait();
    	}
    	else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("No puede haber campos vacios.");
			alert.showAndWait();
    	}
    }

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
        	reservadoLabel.setText(TEXT_RESERVADO);
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Informacion");
    		alert.setHeaderText("Actualizacion reserva");
    		alert.setContentText("Se ha realizado la reserva del libro.");
    		Node source = (Node) event.getSource();
    		alert.initOwner(source.getScene().getWindow()); 
    		alert.showAndWait();
    	}else {
    		System.out.println("Comprobando reservas");
    		Optional<Reserva> reservaOpt = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorUsuarioLibro(usuario,libro);
    		if (reservaOpt.isPresent()) {
//    		List<Reserva> reservas = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorUsuarioLibro(usuario,libro);
//    		if (!reservas.isEmpty()) {
//    			Reserva reserva = reservas.get(0);
    			Reserva reserva = reservaOpt.get();
    			reserva.setActive(false);
        		reservadoLabel.setText(TEXT_DISPONIBLE);
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Informacion");
        		alert.setHeaderText("Actualizacion reserva");
        		alert.setContentText("Se ha devuelto el libro.");
        		Node source = (Node) event.getSource();
        		alert.initOwner(source.getScene().getWindow()); 
        		alert.showAndWait();
    		}
    		else {
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
        
        assert idLabel1 != null : "fx:id=\"idLabel1\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";
        assert tituloLabel1 != null : "fx:id=\"tituloLabel1\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";
        assert autorLabel1 != null : "fx:id=\"autorLabel1\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";
        assert ISBNLabel1 != null : "fx:id=\"ISBNLabel1\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";
        assert reservadoLabel1 != null : "fx:id=\"reservadoLabel1\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";
        assert modificarButton != null : "fx:id=\"modificarButton\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";
        assert borrarButton1 != null : "fx:id=\"borrarButton1\" was not injected: check your FXML file 'DetalleLibroBibliotecario.fxml'.";


    	populateVista();
//    	https://stackoverflow.com/a/6916307/8873596
    	SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    	selectionModel.select(1);
    	System.out.println("Se ha creado la vista");
    }

    /**
     * Cargar datos del libro en la vista
     */
	private void populateVista() {
		if (libro!=null) {
			populateVistaUsuario();
    		populateVistaBibliotecario();
    	}
	}

	private void populateVistaBibliotecario() {
		idLabel1.setText(String.valueOf(libro.getId()));
		tituloLabel1.setText(libro.getTitulo());
		autorLabel1.setText(libro.getAutor());
		ISBNLabel1.setText(libro.getIsbn());
		if (libro.isReservado()) {
			List<Reserva> reservaList = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorLibro(libro);
			Reserva reserva = reservaList.get(0);
			reservadoLabel1.setText("" + reserva.getId());
		}else {
			reservadoLabel1.setText(TEXT_DISPONIBLE);
		}
	}

	private void populateVistaUsuario() {
		idLabel.setText(String.valueOf(libro.getId()));
		tituloLabel.setText(libro.getTitulo());
		autorLabel.setText(libro.getAutor());
		ISBNLabel.setText(libro.getIsbn());
		if (libro.isReservado()) {
			List<Reserva> reservaList = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorLibro(libro);
			Reserva reserva = reservaList.get(0);
			reservadoLabel.setText(reserva.getIdUsuario());
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
	
    // Comprueba que no se deja ningun campo vacio
    boolean comprobarCampos() {
    	if(autorLabel1.getText().isEmpty()||ISBNLabel1.getText().isEmpty()||tituloLabel1.getText().isEmpty())
    		return false;
    	else
    		return true;
    }
    
}


