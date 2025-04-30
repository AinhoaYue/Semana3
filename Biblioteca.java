import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Publicacion> publicaciones;

    public Biblioteca(){
        this.publicaciones = new ArrayList<>();
    }

    public void agregarPublicacion(Publicacion publicacion){
        this.publicaciones.add(publicacion);
    }

    public void prestarPublicacion(Publicacion publicacion) throws NoDisponibleException {
        if (publicacion instanceof Prestable){
            ((Prestable) publicacion).prestar();
        }
    }

    public void devolverPublicacion(Publicacion publicacion) throws NoPrestadoException {
        if (publicacion instanceof Prestable){
            ((Prestable) publicacion).devolver();
        }
    }

    public void mostrarInformacion (){
        for (Publicacion publicacion: publicaciones){
            publicacion.mostrarInformacion();
        }
    }
}
