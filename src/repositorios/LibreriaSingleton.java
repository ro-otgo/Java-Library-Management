package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelos.Libro;

public class LibreriaSingleton {
	
	private static LibreriaSingleton libreria;

	private List<Libro> libros;
	
	private LibreriaSingleton() {
		
		libros = new ArrayList<Libro>();
		for (int i=0;i<50;i++) {
			Libro libro = new Libro();
			libro.setIsbn("ISBN:_" + i);
			libro.setNombre("Nombre: " + i);
			libro.setReservado(new Random().nextBoolean());
			libros.add(libro);
		}
		
	}
	
	public static LibreriaSingleton getLibreria() {
		if (libreria==null) {
			libreria = new LibreriaSingleton();
		}
		return libreria;
	}

	public List<Libro> getLibros() {
		return libros;
	}

}
