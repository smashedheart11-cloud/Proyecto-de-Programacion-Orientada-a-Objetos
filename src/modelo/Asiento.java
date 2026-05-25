package modelo;

import modelo.enums.EstadoAsiento;

public class Asiento {
    private String idAsiento;
    private int fila;
    private int numero;
    private int columna;
    private EstadoAsiento estado;

    private static int CONTADOR = 1;


    public Asiento(int fila, int columna, String idAsiento){
        this.idAsiento = String.format("ASI-%03d", CONTADOR++);
        this.fila = fila;
        this.numero = numero;
        this.columna = columna;
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    public boolean estaDisponible(){
        return estado == EstadoAsiento.DISPONIBLE;
    }

    public void ocupar(){
        if(!estaDisponible()){
            throw new IllegalStateException("Asiento ocupado");
        }
        estado = EstadoAsiento.OCUPADO;
    }

    public void Liberar(){
        if(!estaDisponible()){
            estado = EstadoAsiento.DISPONIBLE;
        }
    }

}
