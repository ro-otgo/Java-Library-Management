/**
 * Sample Skeleton for 'LibrosList.fxml' Controller Class
 */
package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXListView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelos.Libro;
import repositorios.LibreriaSingleton;

/**
 * Lista de libros en la aplicacion
 * @author Rodrigo
 *
 */
public class LibrosListController {
	
	public static void mostarLibrosList(Scene scene)  throws IOException{
		// Mostrar vista lista libro
		FXMLLoader loaderListaLibros = new FXMLLoader(LibrosListController.class.getResource("/vistas/LibrosList.fxml"));
		LibrosListController listaLibroController = new LibrosListController(LibreriaSingleton.getLibreria().getLibrosSinBorrar());
		loaderListaLibros.setController(listaLibroController);
		Parent root = loaderListaLibros.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(LibrosListController.NOMBRE_VENTANA);
		stage.setMinHeight(LibrosListController.MIN_HEIGHT);
		stage.setMinWidth(LibrosListController.MIN_WIDTH);
		stage.getIcons().add(new Image("/img/logo.jpg"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(scene.getWindow());
		stage.show();
	}
	
	// Constante tamano: Ancho
	public static final double MIN_WIDTH= 500;
	// Constante tamano: Largo
	public static final double MIN_HEIGHT= 500;
	// Nombre vista
	public static final String NOMBRE_VENTANA = "Listado Libros";
	
	
	public LibrosListController(List<Libro> libros) {
		this.libros = libros;
		items = FXCollections.observableArrayList();
        items.addAll(this.libros);
	}
	
	private ObservableList<Libro> items;

	private List<Libro> libros;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listaView"
    private JFXListView<Libro> listaView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert listaView != null : "fx:id=\"listaView\" was not injected: check your FXML file 'LibrosList.fxml'.";

//        https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
//        https://stackoverflow.com/a/13270833/8873596
//        https://www.turais.de/how-to-custom-listview-cell-in-javafx/
        listaView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Libro>() {
			@Override
			public void changed(ObservableValue<? extends Libro> observable, Libro oldValue, Libro newValue) {
				System.out.println("Clicked-Old: " + oldValue);
				System.out.println("Clicked-New: " + newValue);
				System.out.println("Clicked-Observable: " + observable);
				System.out.println("Clicked-selected: " + listaView.getSelectionModel().getSelectedItem());
			}
        	
        });

        listaView.setOnMouseClicked(
//        		https://stackoverflow.com/a/9726325
        		new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						System.out.println("click");
						System.out.println(event.getClass());
					}
        			
        		});
        listaView.setItems(items);
        listaView.setExpanded(true);
        listaView.setCellFactory(new Callback<ListView<Libro>, ListCell<Libro>> (){

			@Override
			public ListCell<Libro> call(ListView<Libro> param) {
				return new LibroListaCell();
			}
        	
        });
//        https://stackoverflow.com/a/20485770
        Label placeHolder = new Label("No hay ningun libro");
        listaView.setPlaceholder(placeHolder);
    }
}