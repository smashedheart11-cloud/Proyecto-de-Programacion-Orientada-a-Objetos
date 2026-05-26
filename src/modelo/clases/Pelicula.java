package modelo.clases;

import modelo.enums.Clasificacion;

public class Pelicula {

    private static int contadorPelicula = 1;
    private String idPelicula;
    private String titulo;
    private String genero;
    private Clasificacion clasificacion;
    private String idioma;

    public Pelicula(String titulo, String genero, Clasificacion clasificacion, String idioma) {
       this.idPelicula = String.format("PEL-%03d", contadorPelicula++);
       this.titulo = titulo;
       this.genero = genero;
       this.clasificacion = clasificacion;
       this.idioma = idioma;
    }

    public String getIdPelicula(){
        return  idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return String.format(
                "Datos de la Película: \n" +
                "ID: %s\n" +
                "Titulo: %s\n" +
                "Genero: %s\n" +
                "Clasificacion: %s\n" +
                "Idioma: %s" +
                idPelicula, titulo, genero, clasificacion, idioma
        );
    }

}
