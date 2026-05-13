package modelo;

public class Asiento {
    String numero;
    int fila;
    int columna;
    String sala;
    Boolean ocupado;

    public Asiento(int fila, int columna, String numero){
        this.fila=fila;
        this.columna=columna;
        this.numero=numero;
    }
}
