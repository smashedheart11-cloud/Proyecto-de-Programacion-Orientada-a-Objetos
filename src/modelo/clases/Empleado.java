package modelo.clases;

import modelo.enums.Turno;

public abstract class Empleado {

    private static int contadorEmpleado = 1;
    protected String idEmpleado;
    protected String nombre;
    protected Turno turno;

    public Empleado(String nombre, Turno turno) {
        this.idEmpleado = String.format("EMP-%03d", contadorEmpleado++);
        this.nombre = nombre;
        this.turno = turno;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public Turno getTurno() {
        return turno;
    }

    @Override
    public String toString(){
        return String.format( "|ID: %s| %s - Turno: %s", idEmpleado, nombre, turno);
    }
}
