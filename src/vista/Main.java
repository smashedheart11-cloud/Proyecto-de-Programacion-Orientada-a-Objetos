package vista;

import modelo.clases.*;
import modelo.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Crear película
        Pelicula peli = new Pelicula("Avengers", "Español", "Acción", "B");
        peli.mostrarInformacion();

        // Crear sala
        Sala sala = new Sala(TipoSala.IMAX, 5, 5);

        // Obtener asientos
        List<Asiento> asientos = sala.getAsientos();

        // Crear función
        Funcion funcion = new Funcion(peli, "2026-06-01", "18:00", sala);

        // Crear empleado
        Taquillero taquillero = new Taquillero(1, "Luis", Turno.VESPERTINO);
        taquillero.mostrarInfoEmpleado();

        //  Seleccionar asiento
        Asiento asientoSeleccionado = asientos.get(0);

        System.out.println("Asiento disponible: " + asientoSeleccionado.estaDisponible());

        // Ocupar asiento
        asientoSeleccionado.ocupar();

        System.out.println("Asiento disponible después de ocupar: " + asientoSeleccionado.estaDisponible());

        // Crear boleto
        Boleto boleto = new Boleto(funcion, asientoSeleccionado);

        // Crear factura
        List<Boleto> listaBoletos = new ArrayList<>();
        Factura factura = new Factura(1, TipoPago.EFECTIVO, 0, "cliente@email.com", listaBoletos);

        factura.agregarBoletos(boleto);

        // Calcular total
        double total = factura.calcularTotal();

        System.out.println("Total factura: " + total);

        // liberar asiento
        asientoSeleccionado.liberar();

        System.out.println("Asiento disponible después de liberar: " + asientoSeleccionado.estaDisponible());

    }
}