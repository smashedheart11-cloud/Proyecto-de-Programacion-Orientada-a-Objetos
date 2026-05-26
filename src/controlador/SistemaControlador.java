package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.*;
import datosCine.DatosCinema;
import modelo.clases.Funcion;
import modelo.clases.Taquillero;
import modelo.enums.*;

public class SistemaControlador {

    private DatosCinema datosCinema;
    private List<Funcion> cartelera;
    private Taquillero taquilleroActual;
    private VentaBoletosControlador ventaBoletosControlador;

    public SistemaControlador() {
        this.datosCinema = new DatosCinema();
        this.ventaBoletosControlador = new VentaBoletosControlador();
        this.cartelera = datosCinema.obtenerCartelera();
        this.taquilleroActual = datosCinema.obtenerTaquilleroTrabajando();
        inicializarDatos();
    }

    private void inicializarDatos() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        System.out.println("HOLA: ");
        if (taquilleroActual != null) {
            System.out.println("Taquillero trabajando: " + taquilleroActual.getNombre());
        } else {
            System.out.println("No hay aluien atendiendo taquilla.\n");
            return;
        }
        while (!salir) {
            System.out.println("CARTELERA:\n");
            System.out.print("1|| Vender Boletos para las funciones:\n");
            System.out.println("2|| Salir.\n");
            System.out.println("Seleccione la operacion a realizar:\n");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    ventaBoletosControlador.iniciarVenta(cartelera, taquilleroActual, sc);
                    break;
                    case 2:
                        salir = true;
                        break;
                        default -> throw new IllegalStateException("Opcion no valida: " + opcion);
            }
        }
    }

    public static void main(String[] args){
        SistemaControlador sistema = new SistemaControlador();
        sistema.inicializarDatos();
    }
}
