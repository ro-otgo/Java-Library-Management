/**
	 * Sample Skeleton for 'ReservasList.fxml' Controller Class
	 */
package controladores.reservas;

import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelos.Reserva;
import modelos.Usuario;
import repositorios.ReservaSingleton;

public class ReservaListController {
	
	public static void mostrarlistaReservasActivasUsuario(Scene scene, Usuario usuario) throws IOException{
		// Mostrar reservas usuario
		List<Reserva> reservasList = ReservaSingleton.getReservaSingleton().buscarReservaActivaPorUsuario(usuario);
		mostrarVista(scene, reservasList);
	}
	 
	public static void mostrarListaReservasActivas(Scene scene) throws IOException {
		// Mostrar vista lista reserva
		List<Reserva> reservasList = ReservaSingleton.getReservaSingleton().getReservasActivas();
		mostrarVista(scene, reservasList);
	}

	public static void mostrarListaReservas(Scene scene) throws IOException {
		// Mostrar vista lista reserva
		List<Reserva> reservasList = ReservaSingleton.getReservaSingleton().getReservas();
		mostrarVista(scene, reservasList);
	}

	private static void mostrarVista(Scene scene, List<Reserva> reservasList) throws IOException {
		FXMLLoader loader = new FXMLLoader(ReservaListController.class.getResource("/vistas/ReservasList.fxml"));
		ReservaListController controller = new ReservaListController(
				reservasList);
		loader.setController(controller);
		Parent root = loader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(ReservaListController.NOMBRE_VENTANA);
		stage.setMinHeight(ReservaListController.MIN_HEIGHT);
		stage.setMinWidth(ReservaListController.MIN_WIDTH);
		stage.getIcons().add(new Image("/img/logo.jpg"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(scene.getWindow());
		stage.show();
	}

	public ReservaListController(List<Reserva> reservas) {
		this.reservas = reservas;
		items = FXCollections.observableArrayList();
        items.addAll(this.reservas);
	}

	// Constante tamano: Ancho
	public static final double MIN_WIDTH = 500;
	// Constante tamano: Largo
	public static final double MIN_HEIGHT = 500;
	// Nombre vista
	public static final String NOMBRE_VENTANA = "Listado Reservas";

	private List<Reserva> reservas = new ArrayList<Reserva>();

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="listaView"
	private JFXListView<Reserva> listaView; // Value injected by FXMLLoader
	
	private ObservableList<Reserva> items;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert listaView != null : "fx:id=\"listaView\" was not injected: check your FXML file 'ReservasList.fxml'.";
		listaView.setItems(items);
		listaView.setExpanded(true);
		listaView.setCellFactory(new Callback<ListView<Reserva>, ListCell<Reserva>>() {

			@Override
			public ListCell<Reserva> call(ListView<Reserva> param) {
				return new ReservaListaCell();
			}

		});
		Label placeHolder = new Label("No hay ninguna reserva");
		listaView.setPlaceholder(placeHolder);
	}
}
