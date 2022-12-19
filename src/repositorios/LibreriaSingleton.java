package repositorios;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import modelos.Libro;

/**
 * Patron sigleton para almacenar todos los libros.
 * Esta clase permite almacenar todos los libros en memoria que estan disponibles para la aplicacion.
 * 
 * @author Rodrigo
 *
 */
public class LibreriaSingleton {
	
	// Unica instancia disponible de la libreria
	private static LibreriaSingleton libreria;

	// Listado de libros
	private List<Libro> libros;
	
	/**
	 * Constructor privado para que no pueda ser instanciado por ninguna otra clase.
	 */
	private LibreriaSingleton() {
		libros = cargarLibros();
	}
	
	/**
	 * Metodo para cargar los libros.
	 * NOTE: Este metodo se ha cargado con libros de ejemplo para que puedan ser visualizados en las distintas ventas de la aplciacion.
	 * @return
	 */
	private List<Libro> cargarLibros() {
		List<Libro> libros = new ArrayList(); //objeto vacio donde guardar la informaci�n
		try(Reader reader = new FileReader("listaLibros.json")){
			Gson gson = new Gson();
			Type tipoListaUsuarios = new TypeToken<List<Libro>>() {}.getType();
			libros = gson.fromJson(reader, tipoListaUsuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libros;
	}
	
	/**
	 * Metodo para obtener la libreria, en caso de que no se haya creado aun llamara al constructor y esta a su vez se encargara de cargar los libros.
	 * @return
	 */
	public static LibreriaSingleton getLibreria() {
		if (libreria==null) {
			libreria = new LibreriaSingleton();
		}
		return libreria;
	}

	/**
	 * Se devuelven los libros
	 * @return
	 */
	public List<Libro> getLibros() {
		return libros;
	}
	
	public void addLibro(Libro libro) {
		libros.add(libro);
		escribirLibros();
	}
	
	public void removeLibro(Libro libro) {
		libros.remove(libro);
		escribirLibros();
	}
	
	public void escribirLibros() {
		// guardamos en el Json la lista actualizada
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		try(FileWriter writer = new FileWriter("listaLibros.json")){
			gson.toJson(libros, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
