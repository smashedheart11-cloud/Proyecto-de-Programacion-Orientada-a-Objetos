package main.cinema.modelo.enums;

public enum TipoSala {

    SALA_ESTANDAR(60.00, 100, 10),
    IMAX (120.00, 120, 12),
    VIP(200.00, 80, 10),
    SALA_3D(90.00, 150, 15),
    SALA_4D(140.00, 120, 12);

    private final double precio;
    private final int capacidad;
    private final int columnas;

    TipoSala(double precio,  int capacidad, int columnas) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.columnas = columnas;
    }

    public double getPrecio(){
        return precio;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public int getColumnas(){
        return columnas;
    }
}
