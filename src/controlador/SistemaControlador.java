package controlador;

import java.util.List;
import datosCine.DatosCinema;
import modelo.clases.Funcion;
import modelo.clases.Taquillero;

public class SistemaControlador {

    private DatosCinema datosCinema;
    private List<Funcion> cartelera;
    private Taquillero taquilleroActual;
    private VentaBoletosControlador ventaBoletosControlador;

    public SistemaControlador() {
        // para cargar los datos
        this.datosCinema = new DatosCinema();
        this.ventaBoletosControlador = new VentaBoletosControlador();

        // y ya aqui se ponen los datos
        this.cartelera = datosCinema.obtenerCartelera();
        this.taquilleroActual = datosCinema.obtenerTaquilleroTrabajando();
    }

    public List<Funcion> getCartelera() {
        return cartelera;
    }

    public Taquillero getTaquilleroActual() {
        return taquilleroActual;
    }

    public VentaBoletosControlador getVentaBoletosControlador() {
        return ventaBoletosControlador;
    }
}