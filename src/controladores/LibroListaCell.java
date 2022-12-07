/**
 * Sample Skeleton for 'LibrosListFila.fxml' Controller Class
 */
package controladores;


import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.Libro;

public class LibroListaCell extends ListCell<Libro>{
	
	private Libro libro;
	
    public LibroListaCell() {
		loadFXML();
	}

    /**
     * Actualizar vista con los datos del libro
     */
	private void updateContent() {
		System.out.println(libro);
		titulo.setText(libro.getTitulo());
		autor.setText(libro.getAutor());
		setText(null);
		setGraphic(cellId);
	}

	/**
	 * Cargar xml con la vista
	 */
	private void loadFXML() {
		try {
			FXMLLoader cellLoader = new FXMLLoader(getClass().getResource("/vistas/LibrosListFila.fxml"));
			cellLoader.setController(this);
			cellLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="cellId"
    private AnchorPane cellId; // Value injected by FXMLLoader

	@FXML // fx:id="titulo"
    private Label titulo; // Value injected by FXMLLoader

    @FXML // fx:id="autor"
    private Label autor; // Value injected by FXMLLoader

    @FXML // fx:id="detallesButton"
    private JFXButton detallesButton; // Value injected by FXMLLoader

    @FXML
    void verDetalles(ActionEvent event) {
    	System.out.println("Se ha pulsado ver detalles");
    	try {
			mostrarVistaDetallesLibro();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cellId != null : "fx:id=\"cellId\" was not injected: check your FXML file 'LibrosListFila.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'LibrosListFila.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'LibrosListFila.fxml'.";
        assert detallesButton != null : "fx:id=\"detallesButton\" was not injected: check your FXML file 'LibrosListFila.fxml'.";

    }

	@Override
	protected void updateItem(Libro item, boolean empty) {
		super.updateItem(item, empty);
		if(empty || item == null) {
			System.out.println(">Contenido vacio");
			clear();
		}else {
			System.out.println(">Contenido no vacio");
			this.libro = item;
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
	
	/**
	 * Mostrar vista detalles del libro seleccionado
	 * @throws IOException
	 */
	private void mostrarVistaDetallesLibro() throws IOException {
		DetalleLibroController.mostrarVistaDetallesLibro(libro, this);
	}


}
