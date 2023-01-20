package modelos;

import repositorios.ReservaSingleton;

public class Libro {

	private static long generatedId = 1;

	private String titulo;
	private String autor;
	private String isbn;

	private transient boolean reservado;
	private long id;

	public Libro() {
		super();
	}
	
	/**
	 * Constructor libro
	 * 
	 * @param tibuildertulo
	 */
	private Libro(LibroBuilder builder) {
		this.titulo = builder.titulo;
		this.autor = builder.autor;
		this.isbn = builder.isbn;
		this.reservado = builder.reservado;
		this.id = builder.id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setNombre(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isReservado() {
		return ReservaSingleton.getReservaSingleton().libroReservado(this);
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

	public void setId(long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", isbn=" + isbn + ", id=" + id + "]";
	}

	public static long getGeneratedId() {
		return generatedId;
	}

	public static void setGeneratedId(long generatedId) {
		Libro.generatedId = generatedId;
	}

	public static class LibroBuilder {

		private String titulo;
		private String autor;
		private String isbn;
		private boolean reservado = false;
		private long id;

		public LibroBuilder setTitulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public LibroBuilder setAutor(String autor) {
			this.autor = autor;
			return this;

		}

		public LibroBuilder setIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public Libro build() {
			this.id = ++Libro.generatedId;
			return new Libro(this);
		}
	}
}
