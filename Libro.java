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
