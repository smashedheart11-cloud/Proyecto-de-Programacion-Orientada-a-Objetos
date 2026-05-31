package main.cinema.modelo.clases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private static int contadorFuncion = 1;
    private String idFuncion;
    private LocalDateTime fechaHora;
    private Pelicula pelicula;
    private Sala sala;
    private List<Boleto> boletosVendidos;
    private List<AsientoFuncion> asientosFuncion;

    public Funcion(LocalDateTime fechaHora, Pelicula pelicula, Sala sala) {
        this.idFuncion = String.format("FUN-%03d", contadorFuncion++);
        this.fechaHora = fechaHora;
        this.pelicula = pelicula;
        this.sala=sala;
        this.boletosVendidos = new ArrayList<>();
        this.asientosFuncion = new ArrayList<>();
        if (sala != null && sala.getAsientos() != null) {
            for (Asiento a : sala.getAsientos()) {
                this.asientosFuncion.add(new AsientoFuncion(a));
            }
        }
    }

    public String getIdFuncion() {
        return idFuncion;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<AsientoFuncion> getAsientosFuncion() {
        return asientosFuncion;
    }

    public void setAsientosFuncion(List<AsientoFuncion> asientosFuncion) {
        this.asientosFuncion = asientosFuncion;
    }

    public List<Boleto> getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(List<Boleto> boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }

    public void agregarBoleto(Boleto boleto) {
        this.boletosVendidos.add(boleto);
    }

    // esta es nueva
    public Asiento ocuparAsiento(String identificadorAsiento) {
        for (AsientoFuncion af : asientosFuncion) {
            if (af.getAsiento().getIdentificador().equals(identificadorAsiento)) {

                if (af.estaDisponible()) {
                    af.ocupar();
                    return af.getAsiento(); // devuelve un objeto asiento
                } else {
                    return null; // ya estaba ocupado
                }
            }
        }
        return null; //el asientp no existe
    }

    public boolean verificarDisponibilidad(String idAsiento) {
        for (AsientoFuncion asientoFuncion : asientosFuncion){
            if (asientoFuncion.getAsiento().getIdentificador().equals(idAsiento)){
                return asientoFuncion.estaDisponible();
            }
        }
        return false;
    }

    public long obtenerCantidadAsientosDiponibles() {
        return this.asientosFuncion.stream().filter(AsientoFuncion::estaDisponible).count();
    }

    @Override
    public String toString() {
        return String.format(
                "Funcion:\n" +
                "Fecha y Hora: %s\n" +
                "Sala: %s || Pelicula: %s\n" +
                "Boletos vendidos: %d",
                idFuncion, fechaHora, pelicula.getTitulo(), sala.getTipo(), boletosVendidos.size()
        );
    }
}
