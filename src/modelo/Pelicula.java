package modelo;

public class Pelicula {
    private String idPelicula;
    private String titulo;
    private String idioma;
    private static int CONTADOR=1;

    public Pelicula (String titulo, String idioma){
        this.idPelicula=String.format("PEL-%03d", CONTADOR++);
        this.titulo=titulo;
        this.idioma=idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }
}
