package main.cinema.modelo.clases;

import java.time.LocalDateTime;
import java.util.List;
import main.cinema.modelo.enums.TipoPago;

public class Factura {

    private static int contadorFactura = 1;
    private String idFactura;
    private LocalDateTime fechaFactura;
    private TipoPago tipoPago;
    private double precioTotal;
    private List<Boleto> boletos;
    private Empleado empleadoVenta;

    public Factura(TipoPago tipoPago,List<Boleto> boletos,  Empleado empleado) {
        this.idFactura = String.format("FAC-%03d", contadorFactura++);
        this.fechaFactura = LocalDateTime.now();
        this.tipoPago = tipoPago;
        this.boletos = boletos;
        this.empleadoVenta = empleado;
        this.precioTotal = calcularTotal();
    }

    public String getIdFactura() {
        return idFactura;
    }

    public LocalDateTime getFechaFactura() {
        return fechaFactura;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
        this.precioTotal = calcularTotal();
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public String getEmpleado() {
        return empleadoVenta.getNombre();
    }

    private double calcularTotal(){
        double total = 0.0;
        if(!boletos.isEmpty()){
            for(Boleto b: boletos){
                total += b.getPrecio();
            }
        }
        return total;
    }
    private String generarDetallesBoletos() {
        StringBuilder detalles = new StringBuilder();
        for (Boleto b : boletos) {
            detalles.append(String.format(" Datos función: %s | Asiento: %s | $%.2f\n",
                    b.getFuncion().getPelicula().getTitulo(),
                    b.getAsiento().getIdentificador(),
                    b.getPrecio()));
        }
        return detalles.toString();
    }

    @Override
    public String toString() {
       String ticket = "--------FACTURA----------\n" +
               "FOLIO: %s\n" +
               "FECHA: %s\n" +
               "ATENDIDO POR %s\n" +
               "PAGO CON: %s\n" +
               "----------------------------------\n" +
               "BOLETOS: \n%s" +
               "-----------------------------------\n" +
               "PRECIO TOTAL: %.2f\n" +
               "________________GRACIAS____________\n";
       return String.format(ticket,
               idFactura,
               fechaFactura.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
               empleadoVenta.getNombre(),
               tipoPago,
               generarDetallesBoletos(),
               precioTotal
       );
    }
}
