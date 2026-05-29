package vista;

import controlador.SistemaControlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SistemaControlador sistema = new SistemaControlador();

        VistaCartelera cartelera = new VistaCartelera(sistema);
        cartelera.mostrar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}