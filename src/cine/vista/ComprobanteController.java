package cine.vista;

import cine.MainCine;
import cine.modelo.Entrada;
import cine.utils.AppState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class ComprobanteController {

    @FXML private Label lblPelicula;
    @FXML private Label lblSala;
    @FXML private Label lblButaca;
    @FXML private Label lblCliente;
    @FXML private Label lblFecha;

    private String peliculaActual;
    private int numeroSala;

    public void cargarDatos(Entrada entrada, String pelicula, int sala) {
        peliculaActual = pelicula;
        numeroSala = sala;

        lblPelicula.setText("Película: " + pelicula);
        lblSala.setText("Sala: " + sala);
        lblButaca.setText("Butaca: Fila(" + entrada.getFila() + ") But(" + entrada.getNumeroButaca() + ")");
        lblCliente.setText("Cliente: " + AppState.clienteActual.getNombre());
        lblFecha.setText("Fecha: " + entrada.getFechaCompra());
    }

    @FXML
    public void onVolver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/sala_view.fxml"));
            Parent root = loader.load();

            SalaController ctrl = loader.getController();
            ctrl.setPeliculaActual(peliculaActual);

            MainCine.primaryStage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

