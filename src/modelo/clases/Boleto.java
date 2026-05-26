package modelo.clases;

public class Boleto {

    private static int contadorBoleto = 1;
    private String idBoleto;
    private Funcion funcion;
    private Asiento asiento;
    private Sala sala;
    private double precio;

    public Boleto (Funcion funcion, Asiento asiento){
        this.idBoleto=String.format("BOL-%03d", contadorBoleto++);
        this.funcion=funcion;
        this.asiento=asiento;
        this.sala = funcion.getSala();
        if (this.sala != null && this.sala.getTipo() != null){
            this.precio = this.sala.getTipo().getPrecio();
        }else{
            this.precio = 0;
        }
    }

    public String getIdBoleto() {
        return idBoleto;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public Sala getSala() {
        return sala;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public double getPrecio(){
        return precio;
    }

    @Override
    public String toString(){
        return String.format(
                "TICKET DE COMPRA DE BOLETO:\n" +
                        "Folio: %s\n" +
                        "Pelicula: %s\n" +
                        "Fecha y Hora de funcion: %s\n" +
                        "Sala: %s\n" +
                        "Asiento: Fila%d - Columna %d\n" +
                        "Precio: $%2f \n" +
                        "Gracias por su compra!!",
                idBoleto,
                funcion.getPelicula().getTitulo(),
                funcion.getFechaHora(),
                sala.getTipo(),
                asiento.getFila(),
                asiento.getColumna(),
                precio
        );
    }
}
