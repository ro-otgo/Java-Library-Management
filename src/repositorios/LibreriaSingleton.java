package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	 * TODO: Adaptar el metodo para que pueda leer los libros del disco duro.
	 * @return
	 */
	private List<Libro> cargarLibros() {
		List<Libro> data = new ArrayList();
		for (int i=0;i<50;i++) {
			Libro libro = new Libro();
			libro.setIsbn("ISBN:_" + libro.getId());
			libro.setNombre("Nombre: " + libro.getId());
			libro.setReservado(new Random().nextBoolean());
			data.add(libro);
		}
		return data;
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

}
