package cine.vista;

import cine.modelo.Cliente;
import cine.utils.AppState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtContrasena;
    @FXML private Label lblInfo;

    @FXML
    public void onLogin() {
        String email = txtEmail.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        Cliente cliente = AppState.cine.login(email, contrasena);

        if (cliente == null) {
            lblInfo.setText("Email o contraseña incorrectos.");
            return;
        }

        AppState.clienteActual = cliente;
        abrirPantallaPeliculas();
    }

    @FXML
    public void onRegistro() {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        Cliente nuevo = AppState.cine.registrar(nombre, email, contrasena);

        if (nuevo == null) {
            lblInfo.setText("Ese email ya está registrado.");
        } else {
            lblInfo.setText("Registro exitoso. Ahora iniciá sesión.");
        }
    }

    private void abrirPantallaPeliculas() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cine/vista/pelicula_view.fxml"));
            Scene scene = new Scene(root);
            cine.MainCine.primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            lblInfo.setText("Error al abrir películas.");
        }
    }
}

