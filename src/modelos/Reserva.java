package modelos;

import java.time.LocalDate;

/**
 * Reservas de los libros que se han realizado.
 * @author Rodrigo
 *
 */
public class Reserva {
	
	private static Long generatedID = 1L;
	private Long id;
	private String idUsuario;
	private Long idLibro;
	private boolean isActive;
	
	private LocalDate fechaInicioReserva;
	private LocalDate fechaFinReserva;
	
	public Reserva() {
	}

	public Reserva(String idUsuario, Long idLibro, int tiempoReserva) {
		this.id = ++generatedID;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.fechaInicioReserva = LocalDate.now();
		this.fechaFinReserva = this.fechaInicioReserva.plusDays(tiempoReserva);
		this.isActive = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public LocalDate getFechaInicioReserva() {
		return fechaInicioReserva;
	}

	public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
		this.fechaInicioReserva = fechaInicioReserva;
	}

	public LocalDate getFechaFinReserva() {
		return fechaFinReserva;
	}

	public void setFechaFinReserva(LocalDate fechaFinReserva) {
		this.fechaFinReserva = fechaFinReserva;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	public static Long getGeneratedID() {
		return generatedID;
	}

	public static void setGeneratedID(Long generatedID) {
		Reserva.generatedID = generatedID;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", isActive=" + isActive
				+ ", fechaInicioReserva=" + fechaInicioReserva + ", fechaFinReserva=" + fechaFinReserva + "]";
	}

	
}
