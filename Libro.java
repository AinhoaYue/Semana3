public class Libro extends Publicacion implements Prestable {

    private boolean prestado;


    @Override
    public void prestar() throws NoDisponibleException {
        if (!estaPrestado()){
            NoDisponibleException e = new NoDisponibleException();
            throw e;
        }

        prestado  =true;
    }

    @Override
    public void devolver() throws NoPrestadoException {
        if (!estaPrestado()){
            NoPrestadoException e = new NoPrestadoException();

            throw e;
        }

        prestado = false;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }
}

prieba 
    import java.util.*;

public class AutomovilController {
    private ArrayList<Automovil> lista = new ArrayList<>();

    public AutomovilController() {
        lista.add(new Automovil(1, 100, "1.4L", 15000f));
        lista.add(new Automovil(2, 200, "1.6L", 18000f));
        lista.add(new Automovil(3, 100, "2.0L", 20000f));
        lista.add(new Automovil(4, 300, "1.2L", 12000f));
    }

    public void agregarActualizarAuto(int codigo, int marca, String cilindraje, float precio) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == codigo) {
                lista.get(i).setMarca(marca);
                lista.get(i).setCilindraje(cilindraje);
                lista.get(i).setPrecio(precio);
                return;
            }
        }
        lista.add(new Automovil(codigo, marca, cilindraje, precio));
    }

    public ArrayList<Automovil> filtrarYOrdenar(int marca, float precioMin) {
        ArrayList<Automovil> filtrados = new ArrayList<>();
        for (Automovil auto : lista) {
            if (auto.getMarca() == marca && auto.getPrecio() > precioMin) {
                filtrados.add(auto);
            }
        }
        filtrados.sort((a, b) -> Float.compare(b.getPrecio(), a.getPrecio()));
        return filtrados;
    }

    public int contarRecursivo(int marca) {
        return contarRecursivoAux(0, marca);
    }

    private int contarRecursivoAux(int index, int marca) {
        if (index == lista.size()) return 0;
        int suma = (lista.get(index).getMarca() == marca) ? 1 : 0;
        return suma + contarRecursivoAux(index + 1, marca);
    }

    public String mostrarLista() {
        StringBuilder sb = new StringBuilder();
        for (Automovil auto : lista) {
            sb.append(auto.toString());
        }
        return sb.toString();
    }

    public Set<Integer> obtenerMarcas() {
        Set<Integer> marcas = new TreeSet<>();
        for (Automovil auto : lista) {
            marcas.add(auto.getMarca());
        }
        return marcas;
    }
}
