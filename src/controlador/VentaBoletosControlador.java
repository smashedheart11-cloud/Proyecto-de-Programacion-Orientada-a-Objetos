package controlador;

import modelo.clases.*;
import modelo.enums.TipoPago;
import java.util.ArrayList;
import java.util.List;

public class VentaBoletosControlador {

    // este es el met0do que las ventanas van a invocar cuando el cajero seleccione pagar
    public Factura procesarVenta(Funcion funcion, List<String> identificadoresAsientos, Taquillero taquillero, TipoPago tipoPago) {
        //recibe la funcion, una lista con los asientos elegidos, el taquillero que atiende y el tipo de pago

        List<Boleto> seleccionCompra = new ArrayList<>();
        //esta lista es para mandarle todos los boletos a la factura

        //para cada asiento
        for (String idAsiento : identificadoresAsientos) {

            //el controlador solo da la orden y en funcion se ocupa el asiento
            Asiento asientoFisico = funcion.ocuparAsiento(idAsiento);

            if (asientoFisico != null) {
                //si nos devolvió el asiento, generamos el boleto
                Boleto nuevoBoleto = new Boleto(funcion, asientoFisico);
                seleccionCompra.add(nuevoBoleto); //aqui se añade a la lista
                funcion.agregarBoleto(nuevoBoleto); //para la lista de boletos vendidos
            } else {
                // si nos devolvió null, lanzamos el error para la ventana
                throw new IllegalStateException("El asiento " + idAsiento + " no está disponible.");
            }
        }

        Factura factura = new Factura(tipoPago, seleccionCompra, taquillero);
        taquillero.registrarVenta(factura);
        return factura;
    }
}