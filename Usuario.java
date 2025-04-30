import java.util.ArrayList;
import java.util.List;

public class Usuario extends Biblioteca{

    private String nombre;
    private String id;

    private List<Publicacion> publicacionsPrestadas;

    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.publicacionsPrestadas = new ArrayList<>();
    }

    public void prestarPublicacion(Biblioteca biblioteca, Publicacion publicacion) throws NoDisponibleException {
        if(publicacion instanceof Prestable){
            biblioteca.prestarPublicacion(publicacion);
            this.publicacionsPrestadas.add(publicacion);
        }
    }


    public void devolverPublicacion(Biblioteca biblioteca, Publicacion publicacion) throws NoPrestadoException {
        if (publicacion instanceof Prestable){
            biblioteca.devolverPublicacion(publicacion);
            this.publicacionsPrestadas.remove(publicacion);
        }
    }

    public void mostrarPublicacionesPrestadas(){
        for (Publicacion publicacion : this.publicacionsPrestadas){
            publicacion.mostrarInformacion();
        }
    }
}
