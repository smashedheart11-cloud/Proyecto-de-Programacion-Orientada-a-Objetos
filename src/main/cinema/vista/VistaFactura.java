package main.cinema.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.cinema.modelo.clases.Factura;

public class VistaFactura {

    private Factura factura;
    private final String BG = "#1f2937";

    public VistaFactura(Factura factura) {
        this.factura = factura;
    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Comprobante de Venta");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: " + BG + "; -fx-padding: 20;");

        Label titulo = new Label("¡Venta Exitosa!");
        titulo.setStyle("-fx-text-fill: #10b981; -fx-font-size: 20; -fx-font-weight: bold;");

        // El objeto Factura ya tiene formateado su .toString()
        Label detalles = new Label(factura.toString());
        detalles.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-font-family: 'Courier New', monospace;");

        Button btnCerrar = new Button("Cerrar y volver a inicio");
        btnCerrar.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white;");
        btnCerrar.setOnAction(e -> stage.close());

        layout.getChildren().addAll(titulo, detalles, btnCerrar);

        stage.setScene(new Scene(layout, 400, 450));
        stage.showAndWait();
    }
}