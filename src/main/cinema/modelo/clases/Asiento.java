package main.cinema.modelo.clases;

public class Asiento {

    private static int contadorAsiento = 1;
    private String idAsiento;
    private int fila;
    private int columna;
    private String identificador;

    public Asiento(int fila, int columna, String identificador) {
        this.idAsiento = String.format("ASI-%03d", contadorAsiento++);
        this.fila = fila;
        this.columna = columna;
        this.identificador = identificador;
    }

    public String getIdAsiento() {
        return idAsiento;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    @Override
    public String toString() {
        return String.format(
                "Asiento %s || Fila: %d | Columna: %d | Identificador: %s ||",
                idAsiento, fila, columna, identificador
        );
    }
}
