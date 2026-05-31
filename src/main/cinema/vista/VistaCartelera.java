package main.cinema.vista;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import main.cinema.controlador.SistemaControlador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.cinema.modelo.clases.Funcion;
import main.cinema.modelo.clases.Taquillero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class VistaCartelera {

    private SistemaControlador sistema;
    private VBox rootPrincipal;

    private final String BG = "#1f2937";
    private final String CARD = "#374151";
    private final String BTN = "#3b82f6";
    private final String BTN_HOVER = "#2563eb";

    public VistaCartelera(SistemaControlador sistema) {
        this.sistema = sistema;
    }

    public void mostrar(Stage stage) {
        rootPrincipal = new VBox(10);
        rootPrincipal.setStyle("-fx-background-color: " + BG + "; -fx-padding: 15;");

        construirInterfaz();

        stage.setScene(new Scene(rootPrincipal, 750, 450));
        stage.setTitle("Sistema de Taquilla");
        stage.show();
    }

    private void construirInterfaz() {
        rootPrincipal.getChildren().clear();

        Label titulo = new Label("Cartelera de Cine");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold;");

        Taquillero taquillero = sistema.getTaquilleroActual();
        Label empleado = new Label("Taquillero en turno: " + (taquillero != null ? taquillero.getNombre() : "N/A"));
        empleado.setStyle("-fx-text-fill: #d1d5db;");

        VBox lista = new VBox(10);
        for (Funcion f : sistema.getCartelera()) {
            lista.getChildren().add(crearFila(f));
        }

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToWidth(true);
        // el fondo del panel de desplazamiento se limpia así:
        scroll.setStyle("-fx-background:" + BG + "; -fx-background-color: transparent;");

        rootPrincipal.getChildren().addAll(titulo, empleado, scroll);
    }

    private HBox crearFila(Funcion f) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        long disponibles = f.obtenerCantidadAsientosDiponibles();

        Label hora = new Label(f.getFechaHora().format(fmt));
        hora.setStyle("-fx-text-fill: #60a5fa; -fx-font-size: 16; -fx-font-weight: bold;");

        Label peli = new Label(f.getPelicula().getTitulo() + " (" + f.getSala().getTipo().name() + ")");
        peli.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label info = new Label("Asientos disponibles: " + disponibles);
        info.setStyle("-fx-text-fill: #9ca3af;");

        VBox datos = new VBox(3, peli, info);
        HBox.setHgrow(datos, Priority.ALWAYS);

        Button btn = new Button("Seleccionar Asientos");
        btn.setDisable(disponibles == 0);
        btn.setStyle("-fx-background-color: " + BTN + "; -fx-text-fill: white; -fx-cursor: hand;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: " + BTN_HOVER + "; -fx-text-fill: white; -fx-cursor: hand;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + BTN + "; -fx-text-fill: white;"));

        btn.setOnAction(e -> {
            VistaAsientos vistaAsientos = new VistaAsientos(sistema, f);
            vistaAsientos.mostrarAndWait();
            construirInterfaz(); // Refresca los números al regresar
        });

        Image imgPoster = new Image("file:src/main/recursos/" + f.getPelicula().getTitulo() + ".jpg");

        ImageView visorPoster = new ImageView(imgPoster);
        visorPoster.setFitHeight(80);
        visorPoster.setFitWidth(55);
        visorPoster.setPreserveRatio(true);

        HBox fila = new HBox(15, visorPoster, hora, datos, btn);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setStyle("-fx-background-color: " + CARD + "; -fx-padding: 10;");

        return fila;
    }
}