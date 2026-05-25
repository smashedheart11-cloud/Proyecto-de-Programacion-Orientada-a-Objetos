package modelo;

import modelo.enums.Turno;

public class Empleado {

    private int idEmpleado;
    private String nombre;
    private Turno turno;

    public Empleado(int idEmpleado, String nombre, Turno turno){
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.turno = turno;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public Turno getTurno() {
        return turno;
    }

    public void mostrarInfoEmpleado(){
        System.out.println("ID: " + idEmpleado + " Nombre: " + nombre + " Turno: " + turno);
    }
}
