package cine.vista;

import cine.MainCine;
import cine.modelo.Butaca;
import cine.modelo.Entrada;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import cine.utils.AppState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ButacasController {

    @FXML private Label lblTitulo;
    @FXML private GridPane gridButacas;
    @FXML private Label lblInfo;

    private Sala salaActual;
    private String peliculaActual;

    private int filaSeleccionada = -1;
    private int butacaSeleccionada = -1;
    private Button botonSeleccionado = null;

    public void setSala(int numeroSala, String pelicula) {
        peliculaActual = pelicula;
        salaActual = AppState.cine.buscarSalaPorNumero(numeroSala);

        lblTitulo.setText("Sala " + numeroSala + " - " + peliculaActual);

        mostrarButacas();
    }

    private void mostrarButacas() {
        gridButacas.getChildren().clear();

        Butaca[][] butacas = salaActual.getButacas();

        for (int f = 0; f < butacas.length; f++) {
            for (int c = 0; c < butacas[0].length; c++) {

                Butaca butaca = butacas[f][c];
                Button boton = new Button((f + 1) + "-" + (c + 1));
                boton.setPrefWidth(60);

                if (butaca.isOcupada()) {
                    boton.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white;");
                    boton.setDisable(true);
                } else {
                    boton.setStyle("-fx-background-color: #8aff80; -fx-text-fill: black;");
                }

                int filaReal = f + 1;
                int asientoReal = c + 1;

                boton.setOnAction(e -> seleccionarButaca(boton, filaReal, asientoReal));

                gridButacas.add(boton, c, f);
                GridPane.setHalignment(boton, HPos.CENTER);
            }
        }
    }

    private void seleccionarButaca(Button boton, int fila, int asiento) {
        if (botonSeleccionado != null) {
            botonSeleccionado.setStyle("-fx-background-color: #8aff80;");
        }

        botonSeleccionado = boton;
        boton.setStyle("-fx-background-color: #70a1ff; -fx-text-fill: white;");

        filaSeleccionada = fila;
        butacaSeleccionada = asiento;
    }

    @FXML
    public void onConfirmarCompra() {
        if (filaSeleccionada == -1 || butacaSeleccionada == -1) {
            lblInfo.setText("Seleccioná una butaca primero.");
            return;
        }

        Butaca butaca = salaActual.obtenerButaca(filaSeleccionada, butacaSeleccionada);
        butaca.ocupar();

        Entrada entrada = new Entrada(
                AppState.clienteActual,
                salaActual.getNumeroSala(),
                filaSeleccionada,
                butacaSeleccionada
        );

        AppState.cine.agregarEntrada(entrada);
        PersistenciaDatos.guardar(AppState.cine);

        mostrarComprobante(entrada);
    }

    private void mostrarComprobante(Entrada entrada) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/vista/comprobante_view.fxml"));
            Parent root = loader.load();

            ComprobanteController ctrl = loader.getController();
            ctrl.cargarDatos(entrada, peliculaActual, salaActual.getNumeroSala());

            MainCine.primaryStage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onVolver() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cine/vista/pelicula_view.fxml"));
            MainCine.primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


