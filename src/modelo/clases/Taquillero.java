package modelo.clases;

import java.util.ArrayList;
import java.util.List;
import modelo.enums.Turno;

public class Taquillero extends Empleado {

    private List<Factura> ventasRealizadas;
    private Turno turnoTrabajo;

    public Taquillero(String nombre, Turno turnoTrabajo) {
        super(nombre, turnoTrabajo);
        this.ventasRealizadas = new ArrayList<>();
    }

    public Turno getTurnoTrabajo() {
        return turnoTrabajo;
    }

    public void setTurno(Turno nuevoTurnoTrabajo) {
        this.turnoTrabajo = nuevoTurnoTrabajo;
    }

    public List<Factura> getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void registrarVenta(Factura factura) {
        this.ventasRealizadas.add(factura);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Ventas realizadas: %s", ventasRealizadas.size());
    }

}