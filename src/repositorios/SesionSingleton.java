package repositorios;

import modelos.Usuario;

/**
 * Objeto que se utiliza para saber que usuario esta conectado en cada momento.
 * @author Rodrigo
 *
 */
public class SesionSingleton {
	
	private static SesionSingleton sesionSingleton;
	
	private Usuario usuario;
	
	private SesionSingleton() {
	}

	public static SesionSingleton getSesionSingleton() {
		if (sesionSingleton == null) {
			sesionSingleton = new SesionSingleton();
		}
		return sesionSingleton;
	}
	
	public void actualizarUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario obtenerUsuarioActual() {
		return usuario;
	}
}
