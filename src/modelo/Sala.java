package modelo;

import modelo.enums.TipoSala;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private String idSala;
    private int filas;
    private int columnas;
    private List<Asiento> asientos;
    private TipoSala tipo;

    private static int CONTADOR=1;

    public Sala(TipoSala tipo, int filas, int columnas){
        this.idSala = String.format("SAL-%03d", CONTADOR++);
        this.tipo = tipo;
        this.filas = filas;
        this.columnas = columnas;
        this.asientos = new ArrayList<>();
        generarAsientos();
    }
    private void generarAsientos() {
        char letra = 'A';

        for (int i = 1; i <= filas; i++) {
            for (int j = 1; j <= columnas; j++) {
                asientos.add(new Asiento(i, j, letra + String.valueOf(j)));
            }
            letra++;
        }
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

}
