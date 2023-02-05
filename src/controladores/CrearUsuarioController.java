package controladores;

/**
 * Sample Skeleton for 'CrearUsuario.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


import modelos.Usuario;
import repositorios.UsuariosSingleton;


public class CrearUsuarioController {
	
	private static String NOMBRE_VISTA = "Registro usuario";
	
	private Stage stage;
	
	public static void mostrarVistaCrearUsuario(Scene scene, List<Usuario> loadusuarios , Map<String,String> loadbibliotecarios) throws IOException{
		FXMLLoader loader = new FXMLLoader(CrearUsuarioController.class.getResource("/vistas/CrearUsuario.fxml"));
		CrearUsuarioController nuevoUsuarioController = new CrearUsuarioController();
		loader.setController(nuevoUsuarioController);
		nuevoUsuarioController.setUsuarios(loadusuarios);
		nuevoUsuarioController.setBibliotecarios(loadbibliotecarios);
		
		Parent root = loader.load();
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image("/img/logo.jpg"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(scene.getWindow());
		stage.setTitle(NOMBRE_VISTA);
		nuevoUsuarioController.stage = stage; 
		stage.show();
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="nombre"
    private JFXTextField nombre; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private JFXPasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="wrongLogIn"
    private Label wrong; // Value injected by FXMLLoader

    @FXML // fx:id="apellidos"
    private JFXTextField apellidos; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private JFXTextField email; // Value injected by FXMLLoader

    @FXML // fx:id="idUsuario"
    private JFXTextField idUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="rePassword"
    private JFXPasswordField rePassword; // Value injected by FXMLLoader

    @FXML // fx:id="crearUsuarioButton"
    private JFXButton crearUsuarioButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelarButton"
    private JFXButton cancelarButton; // Value injected by FXMLLoader
    
    private List<Usuario> usuarios;

    private Map<String, String> bibliotecarios;
    
    @FXML
    void cancelar(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void crearUsuario(ActionEvent event) {
    	
		Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	
        // Campo vacio
    	if(nombre.getText().isEmpty() || apellidos.getText().isEmpty() || email.getText().isEmpty() 
    		|| idUsuario.getText().isEmpty() || password.getText().isEmpty() || rePassword.getText().isEmpty())
    	{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("Porfavor rellene todos los campos.");
			alert.showAndWait();
    	}
    	
    	else if(longitudId(idUsuario.getText()))
    	{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("El nombre de usuario debe tener 8 caracteres");
			alert.showAndWait();
    	}
    	
    	else if(validarUsuario(idUsuario.getText()) || validarBibliotecario(idUsuario.getText()))
    	{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("El nombre de usuario ya existe, introduzca uno distino");
			alert.showAndWait();
    	}
    	
    	else if(validarPassword(password.getText(), rePassword.getText())){
    		
        	Usuario usuario = new Usuario();
        	usuario.setNombre(nombre.getText());
        	usuario.setApellidos(apellidos.getText());
        	usuario.setEmail(email.getText());
        	usuario.setPsw(rePassword.getText());
        	usuario.setIdUsuario(idUsuario.getText());
        	UsuariosSingleton.getRepoUsuarios().addUsuario(usuario);
        	stage.close();
    	}
    	else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Se ha producido un error");
			alert.setContentText("La contraseña debe tener 8 caracteres");
			alert.showAndWait();
    	}
    	}
   	


    // Verifica que el id tenga 8 caracteres de longitud:
    private boolean longitudId(String username) {
    	if (username.length()==8)
    		return false;
    	else 
    		return true;
    }
    
    // Verifica si el id de usuario existe  ----------------a lo mejor habría que poner el metodo en usuariossingleton
	private boolean validarUsuario(String username) {
    	for(Usuario i : usuarios) {
    		if(username.equals(i.getiIdUsuario())) 
    			return true;
    	}
    	return false;
    }
	
	// Verifica si el id del usuario existe como bibliotecarios;
	private boolean validarBibliotecario (String username) {
		if(bibliotecarios.containsKey(idUsuario.getText()))
			return true;
		else
			return false;
	}
	
	// valida que la contraseña tenga 8 caractéres y se repita
	private boolean validarPassword (String password, String rePassword) {
		if (password.length()==8 && password.equals(rePassword))
			return true;
		else
			return false;		
	}
	
	public void setUsuarios(List<Usuario> loadUsers) {
		this.usuarios = loadUsers;
	}
	
	public void setBibliotecarios(Map<String, String> loadbibliotecarios) {
		this.bibliotecarios = loadbibliotecarios;
	}
	
	
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert wrong != null : "fx:id=\"wrongLogIn\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert apellidos != null : "fx:id=\"apellidos\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert idUsuario != null : "fx:id=\"idUsuario\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert rePassword != null : "fx:id=\"rePassword\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert crearUsuarioButton != null : "fx:id=\"crearUsuarioButton\" was not injected: check your FXML file 'CrearUsuario.fxml'.";
        assert cancelarButton != null : "fx:id=\"cancelarButton\" was not injected: check your FXML file 'CrearUsuario.fxml'.";

    }
}
