package modelos;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String psw;
	private String email;
	private String idUsuario;
	
	public Usuario() {
		
	}

	public Usuario(String nombre, String apellidos, String psw, String email, String idUsuario) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.psw = psw;
		this.email = email;
		this.idUsuario = idUsuario;
	}

	public String getnombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPsw() {
		return psw;
	}

	public void setPsw (String psw) {
		this.psw = psw;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getiIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario (String idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", psw=" + psw + ", email=" + email
				+ ", idUsuario=" + idUsuario + "]";
	}

}
