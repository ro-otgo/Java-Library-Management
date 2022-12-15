package repositorios;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class UsuariosSingleton {
	
	private static UsuariosSingleton usuariosRepo;
	
	private  Map<String, String> usuarios;
	
	private UsuariosSingleton() {
		usuarios = loadUsers();
	}
	
	// Devuelve el Mapa de bibliotecarios guardados en archivo JSON
	private Map<String, String> loadUsers () {
		Map<String, String> usuarios = new HashMap<String, String>(); //objeto vacio donde guardar la informacion
		try(Reader reader = new FileReader("listaBibliotecarios.json")){
			Gson gson = new Gson();
			Type tipoListaUsuarios = new TypeToken<Map<String, String>>() {}.getType();
			usuarios = gson.fromJson(reader, tipoListaUsuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	// anade un nuevo usuario al listado guardado en archivo JSON
	public void escribirUsuarios () {
		// guardamos en la variable usuarios la informacion del json
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		try(FileWriter writer = new FileWriter("listaBibliotecarios.json")){
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

	public Map<String, String> getUsuarios() {
		return usuarios;
	}
	
	public void addUsuario(Map<String, String> nuevoUsuario) {
		usuarios.putAll(nuevoUsuario);
	}
}
