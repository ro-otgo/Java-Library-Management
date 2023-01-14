package repositorios;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import config.AppConfiguration;
import modelos.Libro;
import modelos.Reserva;
import modelos.Usuario;

/**
 * Repositorio para almacenar las reservas.
 * @author Rodrigo
 *
 */
public class ReservaSingleton {

	// Unica instancia disponible
	private static ReservaSingleton reservaSingleton;

	// Listado de reservas
	private List<Reserva> reservas;
	
	private AppConfiguration appConfiguracion;

	/**
	 * Constructor privado para que no pueda ser instanciado por ninguna otra clase.
	 */
	private ReservaSingleton() {
		reservas = cargarReservas();
		appConfiguracion = AppConfiguration.getConfiguration();
	}

	/**
	 * Metodo para cargar las reservas.
	 * @return
	 */
	private List<Reserva> cargarReservas() {
		List<Reserva> reservas = new ArrayList(); // objeto vacio donde guardar la informaci�n
		try (Reader reader = new FileReader("reservas.json")) {
			Gson gson = new Gson();
			Type tipoReservas = new TypeToken<List<Reserva>>() {
			}.getType();
			reservas = gson.fromJson(reader, tipoReservas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reservas;
	}

	/**
	 * Metodo para obtener la libreria, en caso de que no se haya creado aun llamara
	 * al constructor y esta a su vez se encargara de cargar los libros.
	 * 
	 * @return
	 */
	public static ReservaSingleton getReservaSingleton() {
		if (reservaSingleton == null) {
			reservaSingleton = new ReservaSingleton();
		}
		return reservaSingleton;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void crearReserva(Libro libro, Usuario usuario) {
		Reserva reserva = new Reserva(usuario.getiIdUsuario(), libro.getId(),appConfiguracion.getTiempoReserva());
		reservas.add(reserva);
		escribirReservas();
		libro.setReservado(true);
	}

	public void eliminarReserva(Long idReserva) {
		ListIterator<Reserva> listIterator = reservas.listIterator();
		while(listIterator.hasNext()) {
			Reserva reserva = listIterator.next();
			if(reserva.getId() == idReserva) {
				listIterator.remove();
				break;
			}
		}
		escribirReservas();
	}
	
	public boolean usuarioTieneReservaActivaLibro(Usuario usuario, Libro libro) {
		List<Reserva> reservasLibrosUsuario = reservas.stream().filter(r->usuario.getiIdUsuario().equals(r.getIdUsuario()) && libro.getId() == r.getIdLibro()).collect(Collectors.toList());
		for (Reserva reserva: reservasLibrosUsuario) {
			if (reserva.isActive()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Reserva> getReservasActivas(){
		return reservas.stream().filter(Reserva::isActive).collect(Collectors.toList());
	}
	
	public List<Reserva> buscarReservaActivaPorLibro(Libro libro) {
		return reservas.stream().filter(r -> libro.getId() == r.getIdLibro() && r.isActive()).collect(Collectors.toList());
	}
	
	public List<Reserva> buscarReservaPorLibro(Libro libro) {
		return reservas.stream().filter(r -> libro.getId() == r.getIdLibro()).collect(Collectors.toList());
	}
	
	public List<Reserva> buscarReservaActivaPorUsuario(Usuario usuario) {
		return reservas.stream().filter(r -> usuario.getiIdUsuario().equals(r.getIdUsuario()) && r.isActive()).collect(Collectors.toList());
	}
	
	public List<Reserva> buscarReservaPorUsuario(Usuario usuario) {
		return reservas.stream().filter(r -> usuario.getiIdUsuario().equals(r.getIdUsuario())).collect(Collectors.toList());
	}
	
	public List<Reserva> buscarReservaActivaPorUsuarioLibro(Usuario usuario, Libro libro) {
		return reservas.stream().filter(r -> usuario.getiIdUsuario().equals(r.getIdUsuario()) && r.isActive() && libro.getId() == r.getIdLibro()).collect(Collectors.toList());
	}
	
	public boolean libroReservado(Libro libro) {
		return reservas.stream().anyMatch(r->libro.getId()==r.getIdLibro() && r.isActive());
	}

	public void escribirReservas() {
		// guardamos en el Json la lista actualizada
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter("reservas.json")) {
			gson.toJson(reservas, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
