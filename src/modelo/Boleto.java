package modelo;

public class Boleto {
    private String idBoleto;
    private Funcion funcion;
    private Sala sala;
    private Asiento asiento;
    private static int CONTADOR=1;

    public Boleto (Funcion funcion, Sala sala, Asiento asiento){
        this.idBoleto=String.format("FUN-%03d", CONTADOR++);
        this.funcion=funcion;
        this.sala=sala;
        this.asiento=asiento;
    }
}
