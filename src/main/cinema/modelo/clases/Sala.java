package main.cinema.modelo.clases;

import java.util.ArrayList;
import java.util.List;
import main.cinema.modelo.enums.TipoSala;

public class Sala {

    private static int contadorSala = 1;
    private String idSala;
    private TipoSala tipo;
    private int capacidad;
    private List<Asiento> asientos;

    public Sala(TipoSala tipo){
        this.idSala = String.format("SAL-%03d", contadorSala++);
        this.tipo = tipo;
        this.capacidad = tipo.getCapacidad();
        this.asientos = new ArrayList<>();
        generarAsientos();
    }

    public String getIdSala() {
        return idSala;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    private void generarAsientos() {
        int totalColumnas = tipo.getColumnas();
        int totalFilas = capacidad /  totalColumnas;
        char letra = 'A';

        for (int i = 1; i <= totalFilas; i++) {
            for (int j = 1; j <= totalColumnas; j++) {
                asientos.add(new Asiento(i, j, letra + String.valueOf(j)));
            }
            letra++;
        }
    }

    @Override
    public String toString(){
        return String.format(
                "Sala %s:\n" +
                        "Tipo: %s\n" +
                        "Precio: %.2f\n" +
                        "Capacidad: %d asientos (%d columnas por fila)\n" +
                idSala, tipo.name(), tipo.getPrecio(), capacidad, tipo.getColumnas()
        );
    }

}
