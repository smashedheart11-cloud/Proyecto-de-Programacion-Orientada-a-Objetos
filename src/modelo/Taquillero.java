package modelo;

import modelo.enums.Turno;

public class Taquillero extends Empleado {

    public Taquillero(int idEmpleado, String nombre, Turno turno) {
        super(idEmpleado, nombre, turno);
    }

    @Override
    public void mostrarInfoEmpleado() {
        System.out.println("Taquillero -> ID: " + getIdEmpleado() + " Nombre: " + getNombre() + " Turno: " + getTurno());
    }
}