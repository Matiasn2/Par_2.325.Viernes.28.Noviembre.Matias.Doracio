package cine.vista;
import cine.utils.AppState;
import cine.MainCine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SalaController {

    @FXML private VBox vboxSalas;
    @FXML private Label lblInfo;

    private String peliculaActual;

    public void setPeliculaActual(String pelicula) {
        this.peliculaActual = pelicula;
        lblInfo.setText("Salas disponibles para: " + pelicula);
        cargarSalas();
    }

    private void cargarSalas() {
        vboxSalas.getChildren().clear();
        agregarSala(1, "2D");
        agregarSala(2, "4D");
    }

    private void agregarSala(int numero, String tipo) {
        Button boton = new Button("Sala " + numero + " - " + tipo);
        boton.setPrefWidth(220);
        boton.setOnAction(e -> abrirButacas(numero));
        vboxSalas.getChildren().add(boton);
    }

    private void abrirButacas(int numeroSala) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/butacas_view.fxml"));
            Parent root = loader.load();

            ButacasController controlador = loader.getController();
            controlador.setSala(numeroSala, peliculaActual);

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



