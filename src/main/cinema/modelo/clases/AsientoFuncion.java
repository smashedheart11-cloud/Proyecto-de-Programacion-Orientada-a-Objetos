package main.cinema.modelo.clases;

import main.cinema.modelo.enums.EstadoAsiento;

public class AsientoFuncion {

    private Asiento asiento;
    private EstadoAsiento estado;

    public AsientoFuncion(Asiento asiento) {
        this.asiento = asiento;
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

    public boolean estaDisponible() {
        return estado == EstadoAsiento.DISPONIBLE;
    }

    public void ocupar() {
        if (!estaDisponible()) {
            throw new IllegalStateException("El asiento ya esta ocupado");
        }
        estado = EstadoAsiento.OCUPADO;
    }

    public void desocupar() {
        estado = EstadoAsiento.DISPONIBLE;
    }

    @Override
    public String toString() {
        return String.format(
                "||%s - Estado: %s||",
                asiento.getIdAsiento(), estado
        );
    }
}
