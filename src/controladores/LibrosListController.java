/**
 * Sample Skeleton for 'LibrosList.fxml' Controller Class
 */
package controladores;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

import modelos.Libro;

public class LibrosListController {
	
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

	private List<Libro> libros = new ArrayList<Libro>();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listaView"
    private JFXListView<Libro> listaView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert listaView != null : "fx:id=\"listaView\" was not injected: check your FXML file 'LibrosList.fxml'.";


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
        Label placeHolder = new Label("No hay ningun libro");
        listaView.setPlaceholder(placeHolder);

    }
}
