public interface Prestable {
    public abstract void prestar() throws NoDisponibleException;

    public abstract void devolver() throws NoPrestadoException;

    public abstract boolean estaPrestado();

}
