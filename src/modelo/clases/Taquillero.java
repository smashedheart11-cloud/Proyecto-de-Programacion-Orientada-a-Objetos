package modelo.clases;

import java.util.ArrayList;
import java.util.List;

import modelo.enums.Turno;

public class Taquillero extends Empleado {

    private List<Factura> ventasRealizadas;
    private Turno turnoTrabajo;

    public Taquillero(String nombre, Turno turnoTrabajo) {
        super(nombre, turnoTrabajo.name());
        this.turnoTrabajo = turnoTrabajo;
        this.ventasRealizadas = new ArrayList<>();
    }

    public Turno getTurnoTrabajo() {
        return turnoTrabajo;
    }

    public void setTurno(Turno turnoTrabajo) {
        this.turnoTrabajo = turnoTrabajo;
        this.turno = turnoTrabajo.;
    }

    public List<Factura> getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void registrarVenta(Factura factura) {
        this.ventasRealizadas.add(factura);
    }


}