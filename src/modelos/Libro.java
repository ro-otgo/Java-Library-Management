package modelos;

public class Libro {

	private static long generatedtId = 1;

	private String titulo;
	private boolean reservado;
	private String isbn;
	private final long id;

	/**
	 * Constructor para setear el valor del id
	 */
	public Libro() {
		super();
		id = generatedtId++;
	}

	/**
	 * Constructor libro
	 * 
	 * @param titulo
	 * @param reservado
	 * @param isbn
	 */
	public Libro(String titulo, boolean reservado, String isbn) {
		this();
		this.titulo = titulo;
		this.reservado = reservado;
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setNombre(String titulo) {
		this.titulo = titulo;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", reservado=" + reservado + ", isbn=" + isbn + ", id=" + id + "]";
	}

}
