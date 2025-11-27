package cine.vista;
import cine.utils.AppState;
import cine.MainCine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PeliculaController {

    @FXML private VBox vboxPeliculas;

    @FXML
    public void initialize() {
        agregarPelicula("Jujutsu Kaisen - Ejecución");
        agregarPelicula("Kimetsu no Yaiba - Castillo infinito");
        agregarPelicula("Chainsaw Man - Arco de Reze");
    }

    private void agregarPelicula(String nombre) {
        Button boton = new Button(nombre);
        boton.setPrefWidth(260);
        boton.setOnAction(e -> abrirSalas(nombre));
        vboxPeliculas.getChildren().add(boton);
    }

    private void abrirSalas(String pelicula) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/sala_view.fxml"));
            Parent root = loader.load();

            SalaController controlador = loader.getController();
            controlador.setPeliculaActual(pelicula);

            MainCine.primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCerrarSesion() {
        AppState.clienteActual = null;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cine/vista/login_view.fxml"));
            MainCine.primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


