package repositorios;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import modelos.Usuario;

public class UsuariosSingleton {
	
	private static UsuariosSingleton usuariosRepo;
	
	private  List<Usuario> usuarios;
	
	private UsuariosSingleton() {
		usuarios = loadUsers();
	}
	
	// Devuelve la lista de usuarios guardados en archivo JSON
	private List<Usuario> loadUsers () {
		List<Usuario> usuarios = new ArrayList<Usuario>(); //objeto vacio donde guardar la informacion
		try(Reader reader = new FileReader("listaUsuarios.json")){
			Gson gson = new Gson();
			Type tipoListaUsuarios = new TypeToken<List<Usuario>>() {}.getType();
			usuarios = gson.fromJson(reader, tipoListaUsuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	// anade un nuevo usuario al listado guardado en archivo JSON
	public void escribirUsuarios () {
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		try(FileWriter writer = new FileWriter("listaUsuarios.json")){
			gson.toJson(usuarios, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static UsuariosSingleton getRepoUsuarios() {
		if (usuariosRepo==null) {
			usuariosRepo = new UsuariosSingleton();
		}
		return usuariosRepo;
	}
	
	public Optional<Usuario> findUsuarioById(String usuarioId){
		return usuarios.stream().filter(u->u.getiIdUsuario().equals(usuarioId)).findFirst();
	}
	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void addUsuario(Usuario nuevoUsuario) {
		usuarios.add(nuevoUsuario);
		escribirUsuarios();
	}
}
