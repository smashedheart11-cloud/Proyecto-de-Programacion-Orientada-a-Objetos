package modelo;

import modelo.enums.TipoPago;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int idFactura;
    private TipoPago tipoPago;
    private double precioTotal;
    private String correoCliente;
    private List<Boleto> boletos;
    private Empleado empleado;

    public Factura(int idFactura, TipoPago tipoPago, double precioTotal, String correoCliente, List<Boleto> boletos){
        this.idFactura = idFactura;
        this.tipoPago = tipoPago;
        this.boletos = new ArrayList<>();
        this.precioTotal = precioTotal;
    }
    public void agregarBoletos(Boleto b){
        boletos.add(b);
    }

    public double CalcularTotal(){
        double total = 0;
        for(Boleto b: boletos){
            total += b.getPrecio();
        }
        return total;
    }


}
