package modelo;

public class Pelicula {
    private String idPelicula;
    private String titulo;
    private String idioma;
    private String genero;
    private String clasificacion;
    private static int CONTADOR=1;

    public Pelicula (String titulo, String idioma, String genero, String clasificacion){
        this.idPelicula=String.format("PEL-%03d", CONTADOR++);
        this.titulo=titulo;
        this.idioma=idioma;
        this.genero = genero;
        this.clasificacion = clasificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdPelicula(){
        return  idPelicula;
    }

    public void mostrarInformacion(){
        System.out.println("pelicula" + titulo + " (" + clasificacion + ") ");
    }


}
