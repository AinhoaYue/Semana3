public  abstract class Publicacion extends Biblioteca{
    private String titulo;
    private String autor;

    public void mostrarInformacion(){
        System.out.println("Titulo" + titulo + "Autor" + autor);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
