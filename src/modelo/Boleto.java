package modelo;

public class Boleto {
    private String idBoleto;
    private Funcion funcion;
    private Asiento asiento;
    private double precio;
    private static int CONTADOR=1;

    public Boleto (Funcion funcion, Asiento asiento){
        this.idBoleto=String.format("FUN-%03d", CONTADOR++);
        this.funcion=funcion;
        this.asiento=asiento;
        this.precio = 80;
    }
    public double getPrecio(){
        return precio;
    }

    public void generarBoleto(){

    }

    public void optenerInformacion(){

    }
}
