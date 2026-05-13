package modelo;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String idSala;
    private int filas;
    private int columnas;
    private List<Asiento> asientos;
    private static int CONTADOR=1;
    private TipoSala tipo;

    public Sala(TipoSala tipo){
        this.idSala=String.format("SAL-%03d", CONTADOR++);
        this.tipo=tipo;
        this.asientos=new ArrayList<>();
        definirSala();
    }

    private void definirSala(){
        switch(tipo){
            case SALAESTANDAR:
                filas=10;
                columnas=15;
                generarAsientos();
                break;
            case IMAX:
                filas=20;
                columnas=15;
        }
    }

    private void generarAsientos(){
        char letraFila = 'A';

        for (int i = 1; i <= filas; i++) {
            for (int j = 1; j <= columnas; j++) {
                String codigo = letraFila + String.valueOf(j);
                asientos.add(new Asiento(i, j, codigo));
            }
            letraFila++;
        }
    }

    public void obtenerAsientos(){
        for (Asiento a : asientos){
            System.out.print(a.numero);
            if(a.columna==10){
                System.out.println();
            }
        }
    }


}
