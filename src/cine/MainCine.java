package cine;

import cine.modelo.Cine;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import cine.utils.AppState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCine extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        Cine cineGuardado = PersistenciaDatos.cargar();
        AppState.cine = (cineGuardado != null) ? cineGuardado : new Cine();

        if (AppState.cine.getSalas().isEmpty()) {
            AppState.cine.agregarSala(new Sala(1, "Sala 2D", 5, 8));
            AppState.cine.agregarSala(new Sala(2, "Sala 4D", 5, 8));
        }

        Parent root = FXMLLoader.load(getClass().getResource("/cine/vista/login_view.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Sistema Cine");
        stage.setWidth(600);
        stage.setHeight(450);
        stage.setResizable(true);
        stage.show();
    }

    @Override
    public void stop() {
        PersistenciaDatos.guardar(AppState.cine);
    }

    public static void main(String[] args) {
        launch();
    }
}

