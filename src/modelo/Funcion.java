package modelo;

public class Funcion {
    private String idFuncion;
    private Pelicula pelicula;
    private String fecha;
    private String hora;
    private Sala sala;
    private static int CONTADOR=1;

    public Funcion(Pelicula pelicula, String fecha, String hora, Sala sala){
        this.idFuncion=String.format("FUN-%03d", CONTADOR++);
        this.pelicula=pelicula;
        this.fecha=fecha;
        this.hora=hora;
        this.sala=sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Sala getSala() {
        return sala;
    }
}
