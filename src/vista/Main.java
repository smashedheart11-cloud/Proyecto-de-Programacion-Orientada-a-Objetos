package vista;
import modelo.Boleto;
import modelo.Funcion;
import modelo.Pelicula;
import modelo.Sala;

import java.util.Scanner;

import static modelo.TipoSala.SALAESTANDAR;
import static modelo.TipoSala.VIP;

public class Main {
    public static void main (String[] args) {
        Sala s = new Sala(SALAESTANDAR);

        Pelicula p = new Pelicula("Spiderman 2","Español latino");

        Funcion f = new Funcion(p, "10-10-2026", "16:00", s);





    }

}