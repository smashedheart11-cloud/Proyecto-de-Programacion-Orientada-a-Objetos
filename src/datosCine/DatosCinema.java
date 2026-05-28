package datosCine;

import modelo.clases.*;
import modelo.enums.Clasificacion;
import modelo.enums.TipoSala;
import modelo.enums.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatosCinema {

    private List<Sala> salas;
    private List<Pelicula> peliculas;
    private List<Funcion> funciones;
    private List<Empleado> empleados;

    public DatosCinema() {
        this.salas = new ArrayList<>();
        this.peliculas = new ArrayList<>();
        this.funciones = new ArrayList<>();
        this.empleados = new ArrayList<>();

        cargarDatos();
    }

    public void cargarDatos() {
        empleados.add(new Taquillero("Marco Lemini",Turno.MATUTINO));
        empleados.add(new Taquillero("Ana Martinez", Turno.VESPERTINO));
        empleados.add(new Taquillero("Yerik Herrera", Turno.NOCTURNO));

        Sala sala1 = new Sala(TipoSala.SALA_ESTANDAR);
        Sala sala2 = new Sala(TipoSala.SALA_4D);
        Sala sala3 = new Sala(TipoSala.SALA_3D);
        Sala sala4 = new Sala(TipoSala.IMAX);
        Sala sala5 = new Sala(TipoSala.VIP);

        salas.addAll(List.of(sala1, sala2, sala3, sala4, sala5));

        Pelicula pelicula1 = new Pelicula("Mulholland Drive", "Suspenso/Misterio", Clasificacion.C, "Ingles");
        Pelicula pelicula2 = new Pelicula("Children of Men", "Suspenso//Accion", Clasificacion.C, "Ingles");
        Pelicula pelicula3 = new Pelicula("50 Firt Date", "Comedia/Romance", Clasificacion.B, "Ingles");
        Pelicula pelicula4 = new Pelicula("Project Hail May", "Sci-fi/Aventura", Clasificacion.B, "Ingles");
        Pelicula pelicula5 = new Pelicula("Lake Mungo", "Terror/Misterio", Clasificacion.D, "Ingles");

        peliculas.addAll(List.of(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5));

        LocalDateTime fechaActual = LocalDateTime.now();

        funciones.addAll(List.of(
            new Funcion(fechaActual.withHour(19).withMinute(15), pelicula1, sala5),
            new Funcion(fechaActual.withHour(17).withMinute(30), pelicula2, sala2),
            new Funcion(fechaActual.withHour(15).withMinute(20), pelicula3, sala1),
            new Funcion(fechaActual.withHour(16).withMinute(00), pelicula4, sala3),
            new Funcion(fechaActual.withHour(20).withMinute(45), pelicula5, sala4)
        ));

    }
    public List<Funcion> obtenerCartelera() {
        return funciones;
    }

    public Taquillero obtenerTaquilleroTrabajando() {
        int hora = LocalDateTime.now().getHour();
        Turno turnoActual;
        if (hora >= 6 && hora < 14)       turnoActual = Turno.MATUTINO;
        else if (hora >= 14 && hora < 21) turnoActual = Turno.VESPERTINO;
        else                               turnoActual = Turno.NOCTURNO;

        for (Empleado e : empleados) {
            if (e instanceof Taquillero t && t.getTurno() == turnoActual) {
                return t;
            }
        }
        // Fallback: primer taquillero
        for (Empleado e : empleados) {
            if (e instanceof Taquillero t) return t;
        }
        return null;
    }
}
