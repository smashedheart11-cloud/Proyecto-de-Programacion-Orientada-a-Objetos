package main.cinema.vista;

import main.cinema.controlador.SistemaControlador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.cinema.modelo.clases.AsientoFuncion;
import main.cinema.modelo.clases.Factura;
import main.cinema.modelo.clases.Funcion;
import main.cinema.modelo.enums.TipoPago;

import java.util.ArrayList;
import java.util.List;

public class VistaAsientos {

    private SistemaControlador sistema;
    private Funcion funcion;

    private final String BG = "#1f2937";

    public VistaAsientos(SistemaControlador sistema, Funcion funcion) {
        this.sistema = sistema;
        this.funcion = funcion;
    }

    public void mostrarAndWait() {
        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Selección de Asientos - " + funcion.getPelicula().getTitulo());

        List<Button> seleccionados = new ArrayList<>();
        int columnas = funcion.getSala().getTipo().getColumnas();

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);

        List<AsientoFuncion> asientosFuncion = funcion.getAsientosFuncion();

        for (int i = 0; i < asientosFuncion.size(); i++) {
            AsientoFuncion af = asientosFuncion.get(i);
            Button btn = new Button(af.getAsiento().getIdentificador());
            btn.setPrefSize(50, 40);

            if (!af.estaDisponible()) {
                btn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white;"); // Rojo
                btn.setDisable(true);
            } else {
                btn.setStyle("-fx-background-color: #374151; -fx-text-fill: white;");
            }

            btn.setOnAction(e -> {
                if (seleccionados.contains(btn)) {
                    seleccionados.remove(btn);
                    btn.setStyle("-fx-background-color: #374151; -fx-text-fill: white;");
                } else {
                    seleccionados.add(btn);
                    btn.setStyle("-fx-background-color: #10b981; -fx-text-fill: white;"); // Verde
                }
            });
            grid.add(btn, i % columnas, i / columnas);
        }

        ComboBox<TipoPago> comboPago = new ComboBox<>();
        comboPago.getItems().addAll(TipoPago.values());
        comboPago.setValue(TipoPago.EFECTIVO);

        Button btnConfirmar = new Button("Procesar Pago");
        btnConfirmar.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold;");

        btnConfirmar.setOnAction(e -> {
            if (seleccionados.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Debes seleccionar al menos un asiento.").showAndWait();
                return;
            }

            List<String> idsAsientos = new ArrayList<>();
            for (Button b : seleccionados) {
                idsAsientos.add(b.getText());
            }

            try {
                // Delegamos la lógica al controlador
                Factura factura = sistema.getVentaBoletosControlador().procesarVenta(
                        funcion, idsAsientos, sistema.getTaquilleroActual(), comboPago.getValue()
                );

                ventana.close(); // Cerramos la selección

                // Abrimos el comprobante
                VistaFactura vistaFactura = new VistaFactura(factura);
                vistaFactura.mostrar();

            } catch (IllegalStateException ex) {
                new Alert(Alert.AlertType.ERROR, "Error en la venta: " + ex.getMessage()).showAndWait();
            }
        });

        Label lblPantalla = new Label("============PANTALLA===========");
        lblPantalla.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label lblMetodo = new Label("Metodo de Pago:");
        lblMetodo.setStyle("-fx-text-fill: white;");

        VBox layout = new VBox(15, lblPantalla, grid, lblMetodo, comboPago, btnConfirmar);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: " + BG + "; -fx-padding: 20;");

        ventana.setScene(new Scene(layout, 500, 500));
        ventana.showAndWait(); // Pausa la cartelera principal hasta terminar

    }
}