package vista;

import datosCine.DatosCinema;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.clases.*;
import modelo.enums.TipoPago;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends Application {

    private DatosCinema datos;
    private Taquillero taquillero;
    private List<Funcion> cartelera;

    private final String BG = "#1f2937";
    private final String CARD = "#374151";
    private final String BTN = "#3b82f6";
    private final String BTN_HOVER = "#2563eb";

    @Override
    public void start(Stage stage) {

        datos = new DatosCinema();
        cartelera = datos.obtenerCartelera();
        taquillero = datos.obtenerTaquilleroTrabajando();

        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: " + BG + "; -fx-padding: 15;");

        Label titulo = new Label("Sistema de Cine");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold;");

        Label empleado = new Label("Empleado: " +
                (taquillero != null ? taquillero.getNombre() : "N/A"));
        empleado.setStyle("-fx-text-fill: #d1d5db;");

        VBox lista = new VBox(10);

        for (Funcion f : cartelera) {
            lista.getChildren().add(crearFila(f, stage, root));
        }

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent;");

        root.getChildren().addAll(titulo, empleado, scroll);

        stage.setScene(new Scene(root, 750, 450));
        stage.setTitle("Cine");
        stage.show();
    }

    private HBox crearFila(Funcion f, Stage stage, VBox root) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

        long disponibles = f.getAsientosFuncion()
                .stream()
                .filter(AsientoFuncion::estaDisponible)
                .count();

        Label hora = new Label(f.getFechaHora().format(fmt));
        hora.setStyle("-fx-text-fill: #60a5fa;");

        Label peli = new Label(f.getPelicula().getTitulo());
        peli.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label info = new Label("Disponibles: " + disponibles);
        info.setStyle("-fx-text-fill: #9ca3af;");

        VBox datos = new VBox(3, peli, info);
        HBox.setHgrow(datos, Priority.ALWAYS);

        Button btn = new Button("Comprar");
        btn.setDisable(disponibles == 0);
        btn.setStyle("-fx-background-color: " + BTN + "; -fx-text-fill: white;");

        btn.setOnMouseEntered(e ->
                btn.setStyle("-fx-background-color: " + BTN_HOVER + "; -fx-text-fill: white;")
        );

        btn.setOnMouseExited(e ->
                btn.setStyle("-fx-background-color: " + BTN + "; -fx-text-fill: white;")
        );

        btn.setOnAction(e -> abrirCompra(f, stage, root));

        HBox fila = new HBox(15, hora, datos, btn);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setStyle("-fx-background-color: " + CARD + "; -fx-padding: 10;");

        return fila;
    }

    private void abrirCompra(Funcion f, Stage owner, VBox rootPrincipal) {

        Stage ventana = new Stage();
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.initOwner(owner);

        List<AsientoFuncion> asientosFuncion = f.getAsientosFuncion();
        List<Button> seleccionados = new ArrayList<>();

        int columnas = f.getSala().getTipo().getColumnas();

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        for (int i = 0; i < asientosFuncion.size(); i++) {

            AsientoFuncion af = asientosFuncion.get(i);

            Button btn = new Button(af.getAsiento().getIdentificador());
            btn.setPrefSize(45, 30);

            if (!af.estaDisponible()) {
                btn.setStyle("-fx-background-color: #6b7280;");
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
                    btn.setStyle("-fx-background-color: #10b981; -fx-text-fill: black;");
                }
            });

            grid.add(btn, i % columnas, i / columnas);
        }

        ComboBox<TipoPago> comboPago = new ComboBox<>();
        comboPago.getItems().addAll(TipoPago.values());
        comboPago.setValue(TipoPago.EFECTIVO);

        Button confirmar = new Button("Confirmar");

        confirmar.setOnAction(e -> {

            if (seleccionados.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Selecciona un asiento").showAndWait();
                return;
            }

            List<Boleto> boletos = new ArrayList<>();

            for (Button b : seleccionados) {
                for (AsientoFuncion af : asientosFuncion) {

                    if (af.getAsiento().getIdentificador().equals(b.getText())
                            && af.estaDisponible()) {

                        af.ocupar();

                        Boleto bol = new Boleto(f, af.getAsiento());
                        boletos.add(bol);
                        f.agregarBoleto(bol);
                        break;
                    }
                }
            }

            Factura factura = new Factura(comboPago.getValue(), boletos, taquillero);
            taquillero.registrarVenta(factura);

            ventana.close();

            rootPrincipal.getChildren().set(2, crearScrollCartelera(owner, rootPrincipal));


            Stage facturaStage = new Stage();
            facturaStage.initOwner(owner);

            VBox layout = new VBox(10);
            layout.setStyle("-fx-background-color: " + BG + "; -fx-padding: 15;");

            Label tituloF = new Label("FACTURA");
            tituloF.setStyle("-fx-text-fill: #3b82f6; -fx-font-size: 18; -fx-font-weight: bold;");

            Label peli = new Label("Película: " + f.getPelicula().getTitulo());
            peli.setStyle("-fx-text-fill: white;");

            Label emp = new Label("Empleado: " + taquillero.getNombre());
            emp.setStyle("-fx-text-fill: white;");

            VBox lista = new VBox(5);
            double total = 0;

            for (Boleto b : boletos) {
                double precio = f.getSala().getTipo().getPrecio();

                Label item = new Label(
                        b.getAsiento().getIdentificador() + "  -  $" + precio
                );
                item.setStyle("-fx-text-fill: #d1d5db;");
                lista.getChildren().add(item);

                total += precio;
            }

            Label totalLbl = new Label("TOTAL: $" + total);
            totalLbl.setStyle("-fx-text-fill: #10b981; -fx-font-weight: bold;");

            Label pago = new Label("Pago: " + comboPago.getValue());
            pago.setStyle("-fx-text-fill: white;");

            Button cerrar = new Button("Cerrar");
            cerrar.setOnAction(ev -> facturaStage.close());

            layout.getChildren().addAll(
                    tituloF,
                    peli,
                    emp,
                    new Label("Boletos:"),
                    lista,
                    totalLbl,
                    pago,
                    cerrar
            );

            facturaStage.setScene(new Scene(layout, 350, 400));
            facturaStage.setTitle("Factura");
            facturaStage.show();
        });

        VBox layout = new VBox(10, new Label("Pantalla"), grid, comboPago, confirmar);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: " + BG + "; -fx-padding: 15;");

        ventana.setScene(new Scene(layout, 420, 420));
        ventana.show();
    }

    private ScrollPane crearScrollCartelera(Stage stage, VBox root) {

        VBox lista = new VBox(10);

        for (Funcion f : cartelera) {
            lista.getChildren().add(crearFila(f, stage, root));
        }

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToWidth(true);

        return scroll;
    }
}