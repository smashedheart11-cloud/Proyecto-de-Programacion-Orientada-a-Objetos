package controlador;

import modelo.clases.*;
import modelo.enums.TipoPago;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VentaBoletosControlador {

    public void iniciarVenta(List<Funcion> cartelera, Taquillero taquillero, Scanner sc) {
        System.out.println("---CARTELERA ACTUAL---");
        if (cartelera == null && cartelera.isEmpty()) {
            System.out.println("---NO HAY FUNCIONES ACTUALMENTE---");
            return;
        }

        for (int i = 0; i < cartelera.size(); i++) {
            Funcion funcion = cartelera.get(i);
            System.out.println((i + 1) + ". " + funcion.getPelicula().getTitulo() +
                                        "|Sala: " + funcion.getSala().getTipo().name() +
                                        "| Precio: $" +
                                        String.format("%.2f", funcion.getSala().getTipo().getPrecio())
            );
        }

        System.out.println("---SELECCION---");
        int opcionesFuncion = sc.nextInt() - 1;
        sc.nextLine();

        if (opcionesFuncion >= 0 && opcionesFuncion < cartelera.size()) {
            Funcion funcionSeleccionada = cartelera.get(opcionesFuncion);
            realizarPago(funcionSeleccionada, taquillero, sc);
        } else {
            System.out.println("---SELECCION NO VALIDA---");
        }
    }

    private void realizarPago(Funcion funcion, Taquillero taquillero, Scanner sc) {
        List<Boleto> seleccionCompra = new ArrayList<>();

        System.out.println("FUNCION SELECCIONADA: " + funcion.getPelicula().getTitulo().toUpperCase());
        System.out.println("Cantidad Boletos: ");
        int cantidadBoletos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidadBoletos; i++) {
            System.out.println("Ingrese el asiento del boleto: " + (i + 1));
            String asiento = sc.nextLine().toUpperCase();

            if (funcion.ocuparAsiento(asiento)) {
                for (Asiento asientoSeleccionado : funcion.getSala().getAsientos()) {
                    if (asientoSeleccionado.getIdentificador().equals(asiento)) {
                        Boleto nuevoBoleto = new Boleto(funcion, asientoSeleccionado);
                        seleccionCompra.add(nuevoBoleto);

                        funcion.agregarBoleto(nuevoBoleto);

                        System.out.println("Asiento: " + asiento + " agregado a la seleccion de compra.");
                        break;
                    }
                }
            } else {
                System.out.println("Error: asiento no disponible");
                i--;
            }
        }

        Factura factura = new Factura(TipoPago.EFECTIVO, seleccionCompra, taquillero);
        taquillero.registrarVenta(factura);

        System.out.println("---COMPRA REALIZADA---");
        System.out.println(factura.toString());
    }
}
